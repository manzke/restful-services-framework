package de.devsurf.echo.frameworks.rs.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.Logger;

/**
 * Enables {@link Logger} injection.
 * 
 *    To enable Logging in a guice managed object define
 *    
 *    @Log Logger logger;
 *
 * @author M.Scheepers
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
