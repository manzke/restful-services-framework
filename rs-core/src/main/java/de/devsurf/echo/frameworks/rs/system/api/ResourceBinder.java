package de.devsurf.echo.frameworks.rs.system.api;

import de.devsurf.echo.frameworks.rs.api.Publishable;


public interface ResourceBinder {
	<Resource extends Publishable> Publisher<Resource> publish(Class<Resource> c);
	
	public static interface Publisher<Resource extends Publishable> {
		ScopedBinder to(String name);
	}
}
