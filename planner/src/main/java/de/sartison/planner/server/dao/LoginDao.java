package de.sartison.planner.server.dao;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import de.sartison.planner.model.User;
import de.sartison.planner.server.util.Utils;

public class LoginDao {
	private final static Logger LOGGER = Logger.getLogger(LoginDao.class);

	private final static ReentrantLock lock = new ReentrantLock();
	private final static Map<String, String> memory = new HashMap<>();

	private SessionFactory sessionFactory;

	public LoginDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String getSalt(String user) throws NoSuchAlgorithmException {
		LOGGER.info(String.format("Creating salt for user '%s'", user));

		String random = UUID.randomUUID().toString();
		String salt = Utils.sha256(random);

		lock.lock();

		try {
			memory.put(user, salt);
		} finally {
			lock.unlock();
		}

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.schedule(getTask(user), 5, TimeUnit.SECONDS);

		return salt;
	}

	public User authenticate(String user, String pwdHash) throws NoSuchAlgorithmException {
		LOGGER.info(String.format("Authenticating user '%s'", user));

		User userAuthenticated = null;
		String salt = null;

		lock.lock();

		try {
			salt = memory.get(user);
		} finally {
			lock.unlock();
		}

		if (salt != null) {
			User userObj = getUser(user);
			if (userObj != null) {
				String hash = Utils.sha256(salt + userObj.getPwd());
				if (pwdHash.equals(hash)) {
					userAuthenticated = userObj;
					LOGGER.info(String.format("User '%s' authenticated", user));
				}
			}
		} else {
			LOGGER.warn(String.format("Salt not found for user '%s'. Maybe time was expired!", user));
		}

		return userAuthenticated;
	}

	private User getUser(String user) {
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("name"), user));
			Query<User> qU = session.createQuery(query);

			User userObj = qU.uniqueResult();

			LOGGER.info(String.format("User '%s' found by name '%s'", userObj, user));
			
			return userObj;
		} catch (Exception e) {
			throw e;
		}
	}

	private Runnable getTask(String user) {
		return new Runnable() {
			@Override
			public void run() {
				LOGGER.warn(String.format("Salt for user '%s' expired", user));
				try {
					memory.remove(user);
					LOGGER.warn(String.format("Salt for user '%s' removed", user));
				} finally {
					lock.unlock();
				}
			}
		};
	}
}