package de.devsurf.echo.frameworks.rs.service.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Adds the Access-Control-Allow-Origin - Header to all outgoing responses.
 */
public class CrossDomainHeaderFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
	}
}
