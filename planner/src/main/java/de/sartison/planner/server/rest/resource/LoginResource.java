package de.sartison.planner.server.rest.resource;

import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import de.sartison.planner.model.User;
import de.sartison.planner.server.dao.LoginDao;

public class LoginResource {
	private final static Logger LOGGER = Logger.getLogger(LoginResource.class);

	private LoginDao loginDao;

	public LoginResource(SessionFactory sessionFactory) {
		loginDao = new LoginDao(sessionFactory);
	}

	@PermitAll
	@Path("{email}")
	@GET
	public String getSalt(@PathParam("email") String email) throws Exception {
		try {
			return loginDao.getSalt(email);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error on getSalt", e);
			throw e;
		}
	}

	@PermitAll
	@Path("")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User authenticate(User userToBeAthenticate) throws Exception {
		try {
			User userFound = loginDao.authenticate(userToBeAthenticate.getEmail(), userToBeAthenticate.getPwd());

			if (userFound == null) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}

			userFound.setPwd(null);
			return userFound;
		} catch (Exception e) {
			LOGGER.error("Error on authentication", e);
			throw e;
		}
	}
}