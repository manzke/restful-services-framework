package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.util.Iterator;
import java.util.ServiceLoader;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;

import de.devsurf.echo.frameworks.rs.api.Publishable;
import de.devsurf.echo.frameworks.rs.di.api.Framework;
import de.devsurf.echo.frameworks.rs.di.api.Installable;
import de.devsurf.echo.frameworks.rs.di.api.InstallableModule;
import de.devsurf.echo.frameworks.rs.service.internal.VersionEndpoint;

public class GuicyfiedRuntime extends AbstractModule {
	@Inject
	private Injector injector;
	
	@Override
	public void configure() {
		bind(Publishable.class).annotatedWith(Names.named("version")).to(
				VersionEndpoint.class);

		ServiceLoader<InstallableModule> loader = ServiceLoader.load(InstallableModule.class);
		Iterator<InstallableModule> modules = loader.iterator();
		System.out.println("Found some modules? " + modules.hasNext());
		for (InstallableModule module : loader) {
			injector.injectMembers(module);
			module.install(new Framework() {
				@Override
				public void install(Installable installable) {
					GuicyfiedRuntime.this.install((Module)installable);//TODO we have a hard reference to guice here
				}
			});
		}
	}
}
