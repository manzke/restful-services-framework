package de.devsurf.echo.frameworks.rs.service.filter;

import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 * The class ReplacePostMethodFilter replaces the HTTP method of the request if the request 
 * method is POST and a '_method'-query-param is present. The value of the '_method'-query-param 
 * is then set as the request method. 
 */
public class ReplacePostMethodFilter implements ContainerRequestFilter {

//	private final static Logger LOGGER = Logger.getLogger(ReplacePostMethodFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		if (!requestContext.getMethod().equalsIgnoreCase("POST"))
			return;

		MultivaluedMap<String, String> queryParameters = requestContext.getUriInfo().getQueryParameters();
		if (!queryParameters.containsKey("_method")) {
			return;
		}

		String method = queryParameters.getFirst("_method").trim();
		if (!method.isEmpty()) {
			requestContext.setMethod(method);
//			LOGGER.info("Replaced request POST method with ".concat(method));
		}
		return;
	}
}
