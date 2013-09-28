package de.devsurf.echo.frameworks.rs.api;

public interface TwoWayConverter<From, To> extends Converter<From, To> {
	From convertFrom(To source);
}
