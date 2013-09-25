package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.reflect.Constructor;

import javax.inject.Provider;

import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.ScopedBindingBuilder;

import de.devsurf.common.lang.formatter.ToStringMessage;
import de.devsurf.echo.frameworks.rs.api.Log;
import de.devsurf.echo.frameworks.rs.system.api.Framework;
import de.devsurf.echo.frameworks.rs.system.api.GenericBinder;
import de.devsurf.echo.frameworks.rs.system.api.Installable;
import de.devsurf.echo.frameworks.rs.system.api.ScopedBinder;
import de.devsurf.echo.frameworks.rs.system.api.TypedBinder;

public class GuicyfiedGenericBinder implements GenericBinder {

	@Log
	private Logger logger;

	@Override
	public <Type> TypedBinder<Type> bindClass(final Class<Type> interfaceClass) {
		return new GuicyfiedTypedBinder<Type>(logger, interfaceClass);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TypedBinder<?> bindType(final java.lang.reflect.Type type) {
		return new GuicyfiedTypedBinder(logger, TypeLiteral.get(type));
	}

	public static class GuicyfiedTypedBinder<Type> extends AbstractModule implements
			TypedBinder<Type>, ScopedBinder {
		private Logger logger;
		private Class<Type> interfaceClass;
		private Class<? extends Type> implementationClass;
		private Class<? extends Provider<? extends Type>> providerClass;
		private Constructor<? extends Type> constructor;
		private TypeLiteral<Type> literal;

		private boolean singleton;

		private GuicyfiedTypedBinder(Logger logger, Class<Type> interfaceClass) {
			super();
			this.logger = logger;
			this.interfaceClass = interfaceClass;
		}
		
		private GuicyfiedTypedBinder(Logger logger, TypeLiteral<Type> literal) {
			super();
			this.logger = logger;
			this.literal = literal;
		}

		@Override
		public GuicyfiedTypedBinder<Type> to(Class<? extends Type> implementationClass) {
			this.implementationClass = implementationClass;
			return this;
		}

		@Override
		public ScopedBinder toConstructor(Constructor<? extends Type> constructor) {
			this.constructor = constructor;
			return this;
		}

		@Override
		public ScopedBinder toProvider(Class<? extends Provider<? extends Type>> providerClass) {
			this.providerClass = providerClass;
			return this;
		}

		@Override
		public Installable asSingleton() {
			singleton = true;
			return this;
		}

		@Override
		public void install(Framework framework) {
			framework.install(this);
		}

		@Override
		protected void configure() {
			ScopedBindingBuilder builder;

			if (interfaceClass != null && implementationClass != null) {
				builder = bind(interfaceClass).to(implementationClass);
			}
			else if (interfaceClass != null && constructor != null) {
				builder = bind(interfaceClass).toConstructor(constructor);
			}
			else if (interfaceClass != null && providerClass != null) {
				builder = bind(interfaceClass).toProvider(providerClass);
			}
			else if(literal != null) {
				builder = bind(literal).to(implementationClass);
			}
			else {
				builder = bind(interfaceClass);
			}

			if (singleton) {
				builder.in(Scopes.SINGLETON);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(ToStringMessage.format(GuicyfiedGenericBinder.class)
						.setParentMessage("Binding").addParameter("type", interfaceClass)
						.addParameter("type impl", implementationClass)
						.addParameter("type constructor", constructor)
						.addParameter("type provider", providerClass)
						.addParameter("type literal", literal)
						.addParameter("in singleton scope", singleton).build());
			}
		}
	}
}