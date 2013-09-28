package de.devsurf.echo.frameworks.rs.api;

public interface Converter<From, To> {
	To convertTo(From source);
}
