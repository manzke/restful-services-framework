package de.devsurf.echo.frameworks.rs.service.startup;

import org.glassfish.hk2.api.Factory;

import com.google.common.base.Preconditions;

import de.devsurf.echo.frameworks.rs.api.Featured;
import de.devsurf.echo.frameworks.rs.capabilities.api.CapabilitiesManager;
import de.devsurf.echo.frameworks.rs.capabilities.api.User;
import de.devsurf.echo.frameworks.rs.capabilities.api.CapabilitiesManager.AuthorizationChecker;
import de.devsurf.echo.frameworks.rs.capabilities.api.CapabilitiesManager.CapabilitiesEnabler;

public class Capabilities implements Factory<CapabilitiesManager> {
	public static class DefaultCapabilitiesManager implements
			CapabilitiesManager, AuthorizationChecker, CapabilitiesEnabler {
		private User user;
		
		public AuthorizationChecker can(User user) {
			this.user = user;
			return this;
		};

		@Override
		public CapabilitiesEnabler allow(User user) {
			this.user = user;
			return this;
		}
		
		@Override
		public boolean use(Class<? extends Featured> feature) {
			Preconditions.checkNotNull(user, "No user was specified for feature enabling.");
			Preconditions.checkNotNull(feature, "No feature was specified for user ["+user+"]");
			return true;
		}
		
		@Override
		public void toUse(Class<? extends Featured> feature) {
			Preconditions.checkNotNull(user, "No user was specified for feature enabling.");
			Preconditions.checkNotNull(feature, "No feature was specified for user ["+user+"]");
		}
	}
	
	@Override
	public CapabilitiesManager provide() {
		return new DefaultCapabilitiesManager();
	}
	
	@Override
	public void dispose(CapabilitiesManager instance) {
		
	}
}
