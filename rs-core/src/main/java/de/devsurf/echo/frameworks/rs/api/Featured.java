package de.devsurf.echo.frameworks.rs.api;

public interface Featured {
	String id();
	String description();
	boolean isEnabledFor(InjectableContext context);
}
