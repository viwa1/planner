package de.sartison.planner.server.rest;

import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.sartison.planner.server.rest.resource.LoginResource;

@Path("")
public class PlannerRestService {
	private final static Logger LOGGER = Logger.getLogger(PlannerRestService.class);

	private LoginResource loginResource;
	private SessionFactory sessionFactory;

	public PlannerRestService() {
		try {
			LOGGER.info("Building SessionFactory...");
			sessionFactory = new Configuration().configure().buildSessionFactory();
			LOGGER.info("SessionFactory build");

			LOGGER.info("Loadeding resources...");
			loginResource = new LoginResource(sessionFactory);
			LOGGER.info("Resources loaded");
		} catch (Exception exc) {
			LOGGER.error("Error occured", exc);

			try {
				finalize();
			} catch (Throwable th) {
				LOGGER.error("Error on closing SessionFactory", th);
				throw new RuntimeException(th);
			}
		}
	}

	@Path("login")
	public LoginResource getResourcLoginResource() {
		return loginResource;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if ((sessionFactory != null) && !sessionFactory.isClosed()) {
			LOGGER.info("Closing SessionFactory connection...");
			sessionFactory.close();
			LOGGER.info("SessionFactory connection closed!");
		}
	}
}