package de.devsurf.echo.frameworks.rs.service.startup;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.container.ContainerRequestContext;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.process.internal.RequestScoped;

import de.devsurf.echo.frameworks.rs.api.InjectableContext;

public class ContextProvider implements Factory<InjectableContext> {
    @Inject
    private Provider<ContainerRequestContext> provider;

	@Override
	@RequestScoped
	public InjectableContext provide() {
		ContainerRequestContext context = provider.get();
		return new InjectableContext() {
		};
	}
	
	@Override
	public void dispose(InjectableContext instance) {
		
	}
}
