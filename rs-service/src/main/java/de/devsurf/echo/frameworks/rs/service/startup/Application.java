package de.devsurf.echo.frameworks.rs.service.startup;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

import de.devsurf.echo.frameworks.rs.api.InjectableContext;
import de.devsurf.echo.frameworks.rs.capabilities.api.CapabilitiesManager;
import de.devsurf.echo.frameworks.rs.service.resources.ServerEndpoint;

public abstract class Application extends ResourceConfig {
	public Application() {
		super(JacksonFeature.class);
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
		registerInstances(new ApplicationLifecycle());
	}

	public void shutdown() {
	}

	public class ApplicationLifecycle implements ContainerLifecycleListener {

		@Override
		public void onStartup(Container container) {

		}

		@Override
		public void onReload(Container container) {

		}

		@Override
		public void onShutdown(Container container) {
			Application.this.shutdown();
		}
	}
}
