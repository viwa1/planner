package de.sartison.planner.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.TextCallback;

import de.sartison.planner.model.User;

@Path("../rest")
public interface PlannerRestService extends RestService {
	
	@Path("login/{email}")
	@GET
	public void getSalt(@PathParam("email") String email, TextCallback callback);

	@Path("login")
	@POST
	public void authenticate(User userToBeAthenticate, MethodCallback<User> calback);
}
