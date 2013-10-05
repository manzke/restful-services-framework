package de.devsurf.echo.frameworks.rs.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;


public interface Converter<From, To> {
	To convertTo(From source);
	
	@Target({ElementType.FIELD, ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Qualifier
	public static @interface InfoConverter {
		
	}
}
