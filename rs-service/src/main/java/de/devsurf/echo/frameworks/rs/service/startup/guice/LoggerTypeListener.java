package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import de.devsurf.echo.frameworks.rs.api.Log;

/**
 * Defines/Represents/Provides bla bla
 * 
 * @author M.Scheepers
 */
class LoggerTypeListener implements TypeListener {

	@Override
	public <T> void hear(TypeLiteral<T> type, TypeEncounter<T> encounter) {
		for (Class<?> c = type.getRawType(); c != Object.class; c = c.getSuperclass()) {
			for (Field field : c.getDeclaredFields()) {
				if (field.getType() == Logger.class 
						&& field.isAnnotationPresent(Log.class)) {
					encounter.register(new LoggerMembersInjector<T>(field));
				}
			}
		}
	}

}
