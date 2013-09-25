package de.devsurf.echo.frameworks.rs.api;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Superclass of all classes that have a certain type.
 */
public interface Typed {
	@JsonProperty("type")
	public Type type();
}
