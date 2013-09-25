package de.devsurf.echo.frameworks.rs.system.api;

import de.devsurf.echo.frameworks.rs.api.Featured;
import de.devsurf.echo.frameworks.rs.api.User;


public interface CapabilitiesManager {
	CapabilitiesEnabler allow(User user);
//	CapabilitiesEnabler allow(Group group);
//	CapabilitiesEnabler allow(Tenant tenant);
	AuthorizationChecker can(User user);
	
	public static interface CapabilitiesEnabler{
		void toUse(Class<? extends Featured> feature);	
	}
	
	public static interface AuthorizationChecker{
		boolean use(Class<? extends Featured> feature);	
	}
}
