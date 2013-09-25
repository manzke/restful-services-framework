package de.devsurf.echo.frameworks.rs.api;

import org.codehaus.jackson.annotate.JsonValue;


/**
 * 
 */
public interface Type {
	@JsonValue
	public String value();
}
