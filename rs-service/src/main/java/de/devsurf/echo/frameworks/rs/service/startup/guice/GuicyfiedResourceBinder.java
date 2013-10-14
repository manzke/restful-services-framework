package de.devsurf.echo.frameworks.rs.service.startup.guice;

import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.name.Names;

import de.devsurf.common.lang.di.InjectLogger;
import de.devsurf.common.lang.formatter.ToStringMessage;
import de.devsurf.echo.frameworks.rs.api.Publishable;
import de.devsurf.echo.frameworks.rs.di.api.Framework;
import de.devsurf.echo.frameworks.rs.di.api.ResourceBinder;
import de.devsurf.echo.frameworks.rs.di.api.ScopedBinder;

public class GuicyfiedResourceBinder implements ResourceBinder {

	@InjectLogger
	private Logger logger;

	@Override
	public <Resource extends Publishable> Publisher<Resource> publish(Class<Resource> resourceClass) {
		return new GuicyfiedPublisher<Resource>(logger, resourceClass);
	}

	public static class GuicyfiedPublisher<Resource extends Publishable> extends AbstractModule
			implements Publisher<Resource>, ScopedBinder {
		private Logger logger;
		private Class<Resource> clazz;
		private String name;
		private boolean singleton;

		private GuicyfiedPublisher(Logger logger, Class<Resource> clazz) {
			super();
			this.clazz = clazz;
			this.logger = logger;
		}

		@Override
		public GuicyfiedPublisher<Resource> to(String name) {
			this.name = name;
			return this;
		}

		@Override
		public GuicyfiedPublisher<Resource> asSingleton() {
			this.singleton = true;
			return this;
		}

		@Override
		public void install(Framework framework) {
			framework.install(this);
		}

		@Override
		protected void configure() {
			ScopedBindingBuilder builder = bind(Publishable.class).annotatedWith(Names.named(name))
					.to(clazz);
			if (singleton) {
				builder.in(Scopes.SINGLETON);
			} else {
				builder.in(Scopes.NO_SCOPE);
			}
			logger.debug(ToStringMessage.format(GuicyfiedResourceBinder.class)
					.setParentMessage("Registering resource").addParameter("class", clazz)
					.addParameter("path", name).addParameter("singleton", singleton).build());
		}
	}
}