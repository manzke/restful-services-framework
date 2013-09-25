package de.devsurf.echo.frameworks.rs.service.internal;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.devsurf.echo.frameworks.rs.api.Publishable.AbstractEndpoint;

@Path("version")
public class VersionEndpoint extends AbstractEndpoint {
	@Override
	@GET
	@Consumes("*/*")
	@Produces("application/json")
	public Response get() {
		return Response.ok(version()).build();
	}
	
	@Override
	public String description() {
		return "resource to retrieve the version of the service";
	}
}
