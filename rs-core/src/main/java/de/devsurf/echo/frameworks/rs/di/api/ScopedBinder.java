package de.devsurf.echo.frameworks.rs.di.api;

public interface ScopedBinder extends Installable {
	Installable asSingleton();
}
