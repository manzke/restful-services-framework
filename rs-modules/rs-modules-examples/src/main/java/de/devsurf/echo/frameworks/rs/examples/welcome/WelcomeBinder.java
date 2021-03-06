package de.devsurf.echo.frameworks.rs.examples.welcome;

import javax.inject.Inject;

import de.devsurf.echo.frameworks.rs.di.api.Framework;
import de.devsurf.echo.frameworks.rs.di.api.InstallableModule;
import de.devsurf.echo.frameworks.rs.di.api.ResourceBinder;

public class WelcomeBinder implements InstallableModule {
	@Inject
	private ResourceBinder binder;
	
	@Override
	public void install(Framework framework) {
		binder.publish(WelcomeEndpoint.class).to("welcome").install(framework);
	}
}
