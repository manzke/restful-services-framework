package de.devsurf.echo.frameworks.rs.examples.welcome;

import javax.inject.Provider;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ProviderExample {
	public static interface Planet {
		String name();
	}
	public static class Earth implements Planet {
		@Override
		public String name() {
			return getClass().getSimpleName();
		}
	}
	public static class PlanetEarthProvider implements Provider<Planet> {
		@Override
		public Planet get() {
			return new Earth();
		}
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(Planet.class).toProvider(PlanetEarthProvider.class);
			}
		});
		System.out.println(injector.getInstance(Planet.class).name());
		System.out.println(injector.getProvider(Planet.class).get().name());
		System.out.println(injector.getProvider(Planet.class));
	}
}
