package de.devsurf.echo.frameworks.rs.service.startup.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import de.devsurf.common.lang.di.TypeLiteralBuilder;
import de.devsurf.echo.frameworks.rs.di.api.GenericBinder;
import de.devsurf.echo.frameworks.rs.di.api.ResourceBinder;

public class GuicyfiedSystem extends AbstractModule {
	
	private Logger logger = LoggerFactory.getLogger(GuicyfiedSystem.class); 
	
	@Override
	public void configure() {
		if (logger.isDebugEnabled()) {
			logger.debug("configure()");
		}
		bindListener(Matchers.any(), new GuicyfiedLoggerTypeListener());
		bind(ResourceBinder.class).to(GuicyfiedResourceBinder.class);
//		bind(ContextBinder.class).to(GuicyfiedContextBinder.class);
		bind(GenericBinder.class).to(GuicyfiedGenericBinder.class);
		bind(TypeLiteralBuilder.class).to(GuicyfiedLiterals.class);
	}
}