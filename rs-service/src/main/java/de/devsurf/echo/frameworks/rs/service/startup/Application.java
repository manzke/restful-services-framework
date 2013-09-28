package de.devsurf.echo.frameworks.rs.service.startup;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import de.devsurf.echo.frameworks.rs.api.InjectableContext;
import de.devsurf.echo.frameworks.rs.service.resources.ServerEndpoint;
import de.devsurf.echo.frameworks.rs.system.api.CapabilitiesManager;

public abstract class Application extends ResourceConfig {
	public Application() {
		super(JacksonFeature.class, MultiPartFeature.class);
		packages(ServerEndpoint.class.getPackage().getName());
		registerInstances(new AbstractBinder() {
			@Override
			protected void configure() {
				bindFactory(ContextProvider.class).to(InjectableContext.class)
						.in(RequestScoped.class);
				bindFactory(Capabilities.class).to(CapabilitiesManager.class)
						.in(RequestScoped.class);
			}
		});
	}
}
