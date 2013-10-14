package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Sample was taken from google and modified to be more flexible.
 * http://code.google.com/p/google-guice/wiki/CustomInjections
 */
public class GuicyfiedLoggerTypeListener implements TypeListener {
	@Override
	public <LoggerType> void hear(TypeLiteral<LoggerType> type, TypeEncounter<LoggerType> encounter) {
		for (Class<?> c = type.getRawType(); c != Object.class; c = c
				.getSuperclass()) {
			for (Field field : c.getDeclaredFields()) {
				if (field.getType() == Logger.class
						&& field.isAnnotationPresent(de.devsurf.echo.frameworks.rs.api.InjectLogger.class)) {
					de.devsurf.echo.frameworks.rs.api.InjectLogger loggerAnnotation = field.getAnnotation(de.devsurf.echo.frameworks.rs.api.InjectLogger.class);
					GuicyfiedLoggerInjector<LoggerType> loggerInjector;
					if(Strings.isNullOrEmpty(loggerAnnotation.value())) {
						loggerInjector = new GuicyfiedLoggerInjector<>(field);
					} else {
						loggerInjector = new GuicyfiedLoggerInjector<>(field, loggerAnnotation.value());
					}
					encounter
							.register(loggerInjector);
				}
			}
		}
	}
	
	public static class GuicyfiedLoggerInjector<LoggerType> implements MembersInjector<LoggerType> {
		private final Field field;
		private final Logger logger;

		private GuicyfiedLoggerInjector(Field field) {
			this(field, field.getDeclaringClass().getName());
		}
		
		private GuicyfiedLoggerInjector(Field field, String name) {
			this.field = field;
			this.logger = LoggerFactory.getLogger(name);
			field.setAccessible(true);
		}

		@Override
		public void injectMembers(LoggerType instance) {
			try {
				field.set(instance, logger);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}