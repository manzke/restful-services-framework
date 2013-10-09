package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import de.devsurf.echo.frameworks.rs.service.startup.Application;
import de.devsurf.echo.frameworks.rs.service.startup.Configuration;

public abstract class GuicyfiedApplication extends Application {
	protected Injector injector;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public GuicyfiedApplication(ServiceLocator serviceLocator) {
		super();
		Stopwatch stopwatch = Stopwatch.createStarted();
		Injector startupInjector = Guice.createInjector(new Configuration(),
				new GuicyfiedSystem());

		Injector runtimeInjector = startupInjector.createChildInjector(startupInjector
				.getInstance(GuicyfiedRuntime.class));
		
		Map<Key<?>, Binding<?>> allBindings = runtimeInjector.getAllBindings();
		for(Entry<Key<?>, Binding<?>> binding : allBindings.entrySet()) {
			System.out.println(binding.getKey() + " / " + binding.getValue());
		}

		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		GuiceIntoHK2Bridge guiceBridge = serviceLocator
				.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(runtimeInjector);
		
		injector = runtimeInjector;
		long milliseconds = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
		logger.info("Startup time took "+milliseconds+" ms.");
	}
}
