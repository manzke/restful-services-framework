package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;

import de.devsurf.echo.frameworks.rs.api.Log;

/**
 * Provides the ability to inject logger instances to classes.
 * 
 * @see Log
 *
 * @author M.Scheepers
 */
class LoggerMembersInjector<T> implements MembersInjector<T> {
	private final Field field;
	private final Logger logger;

	LoggerMembersInjector(Field field) {
		this.field = field;
		this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
		field.setAccessible(true);
	}

	@Override
	public void injectMembers(T instance) {
		try {
			field.set(instance, logger);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
