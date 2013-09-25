package de.devsurf.echo.frameworks.rs.api;

public interface Converter<From, To> {
	To convert(From source);
}
