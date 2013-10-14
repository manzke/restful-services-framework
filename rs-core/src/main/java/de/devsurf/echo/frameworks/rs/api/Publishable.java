package de.devsurf.echo.frameworks.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

public interface Publishable {

	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public abstract class AbstractEndpoint implements Publishable, Featured,
			Versioned {
		
		@Logger
		protected Logger logger;
		/**
		 * The UriInfo object of the underlying request. Is injected by the
		 * Jersey framework.
		 */
		@Context
		protected UriInfo uriInfo;

		/**
		 * The HttpServletRequest object of the underlying request. Is injected
		 * by the Jersey framework.
		 */
		@Context
		protected HttpServletRequest request;

		@Context
		protected ResourceContext rc;

		@Override
		public boolean isEnabledFor(InjectableContext context) {
			return true;
		}

		@Override
		public String id() {
			return getClass().getName();
		}

		@Override
		@OPTIONS
		@Consumes("*/*")
		@Produces("application/json")
		public Version version() {
			return Versions.unknownVersion();
		}

		// TODO Enable reflection for resources so we are able to analyze what
		// subresources/features are available

		/**
		 * Shows a welcome message as String with all possible endpoints.
		 * 
		 * @return a welcome message as String with all possible endpoints.
		 */
		@GET
		@Consumes(MediaType.WILDCARD)
		@Produces(MediaType.APPLICATION_JSON)
		public Response get() {
			if(logger != null){
				if (logger.isDebugEnabled()) {
					logger.debug("get()");
				}
			} else {
				System.out.println(String.format("No logger found for resource: %s. executing get()", getClass()));
			}
			return Response.ok(new Described(id(), version(), description()))
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		}
	}

}
