package de.sartison.planner.server.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PlannerExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		Response response;

		if (e instanceof WebApplicationException) {
			WebApplicationException er = (WebApplicationException) e;
			response = er.getResponse();
		} else {
			StringBuilder responseText = new StringBuilder();
			responseText.append(e.getClass().getName() + ": ");
			responseText.append(e.getMessage());

			response = Response.serverError().entity(responseText.toString()).type(MediaType.TEXT_PLAIN).build();
		}

		return response;
	}
}
