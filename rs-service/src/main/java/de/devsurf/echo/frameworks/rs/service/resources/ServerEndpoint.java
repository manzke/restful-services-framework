package de.devsurf.echo.frameworks.rs.service.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import de.devsurf.echo.frameworks.rs.api.Featured;
import de.devsurf.echo.frameworks.rs.api.InjectableContext;
import de.devsurf.echo.frameworks.rs.api.Publishable;
import de.devsurf.echo.frameworks.rs.api.Publishable.AbstractEndpoint;

@Path("/")
public class ServerEndpoint extends AbstractEndpoint {
	
	public ServerEndpoint() {
		//FIXME let it inject through HK2 Factory or Binder
		logger = LoggerFactory.getLogger(ServerEndpoint.class);
	}

	@Inject
	private Injector injector;

	@Override
	public String description() {
		return "Main entry to the API of the new service";
	}

	@Path("api/{version}/{path}")
	public Publishable find(@PathParam("path") String path, @PathParam("version") String version,
			@Context InjectableContext context) {
		Preconditions.checkArgument(path != null && path.length() > 0, "Service path for subresource is not available.");
		Preconditions.checkNotNull(context, "Sevice Context is not available.");

		Binding<Publishable> binding = injector.getExistingBinding(Key.get(Publishable.class, Names.named(path)));

		if (binding != null) {
			Publishable endpoint = binding.getProvider().get();
			if (endpoint instanceof Featured) {
				Featured feature = (Featured) endpoint;
				if (!feature.isEnabledFor(context)) {
					throw new WebApplicationException(Status.FORBIDDEN);
				}
			}

			return rc.initResource(endpoint);
		}

		throw new WebApplicationException(Status.NOT_FOUND);
	}
}
