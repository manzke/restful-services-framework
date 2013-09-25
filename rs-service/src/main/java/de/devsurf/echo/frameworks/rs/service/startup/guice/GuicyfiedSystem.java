package de.devsurf.echo.frameworks.rs.service.startup.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import de.devsurf.echo.frameworks.rs.system.api.GenericBinder;
import de.devsurf.echo.frameworks.rs.system.api.ResourceBinder;
import de.devsurf.echo.frameworks.rs.system.api.TypeLiteralBuilder;

public class GuicyfiedSystem extends AbstractModule {
	
	private Logger logger = LoggerFactory.getLogger(GuicyfiedSystem.class); 
	
	@Override
	public void configure() {
		if (logger.isDebugEnabled()) {
			logger.debug("configure()");
		}
		bindListener(Matchers.any(), new LoggerTypeListener());
		bind(ResourceBinder.class).to(GuicyfiedResourceBinder.class);
//		bind(ContextBinder.class).to(GuicyfiedContextBinder.class);
		bind(GenericBinder.class).to(GuicyfiedGenericBinder.class);
		bind(TypeLiteralBuilder.class).to(GuicyfiedLiterals.class);
	}
}