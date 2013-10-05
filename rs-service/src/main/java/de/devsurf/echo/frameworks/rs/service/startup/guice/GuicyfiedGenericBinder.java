package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import javax.inject.Provider;

import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;

import de.devsurf.common.lang.formatter.ExceptionMessage;
import de.devsurf.common.lang.formatter.ToStringMessage;
import de.devsurf.echo.frameworks.rs.api.Log;
import de.devsurf.echo.frameworks.rs.system.api.AnnotatedBinder;
import de.devsurf.echo.frameworks.rs.system.api.Framework;
import de.devsurf.echo.frameworks.rs.system.api.GenericBinder;
import de.devsurf.echo.frameworks.rs.system.api.Installable;
import de.devsurf.echo.frameworks.rs.system.api.ScopedBinder;
import de.devsurf.echo.frameworks.rs.system.api.TypedBinder;

public class GuicyfiedGenericBinder implements GenericBinder {

	@Log
	private Logger logger;

	@Override
	public <Type> AnnotatedBinder<Type> bindClass(
			final Class<Type> interfaceClass) {
		return new GuicyfiedBinder<Type>(logger, interfaceClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public AnnotatedBinder<?> bindType(final java.lang.reflect.Type type) {
		return new GuicyfiedBinder(logger, TypeLiteral.get(type));
	}

	public static class GuicyfiedBinder<Type> extends AbstractModule implements
			AnnotatedBinder<Type>, TypedBinder<Type>, ScopedBinder {
		private Logger logger;
		private Class<Type> interfaceClass;
		private Class<? extends Type> implementationClass;
		private Class<? extends Provider<? extends Type>> providerClass;
		private Constructor<? extends Type> constructor;
		private TypeLiteral<Type> literal;
		private Annotation annotation;
		private Class<? extends Annotation> annotationClass;

		private boolean singleton;

		private GuicyfiedBinder(Logger logger, Class<Type> interfaceClass) {
			super();
			this.logger = logger;
			this.interfaceClass = interfaceClass;
		}

		private GuicyfiedBinder(Logger logger, TypeLiteral<Type> literal) {
			super();
			this.logger = logger;
			this.literal = literal;
		}

		@Override
		public TypedBinder<Type> annotatedWith(Annotation annotation) {
			this.annotation = annotation;
			return this;
		}

		@Override
		public TypedBinder<Type> annotatedWith(
				Class<? extends Annotation> annotationClass) {
			this.annotationClass = annotationClass;
			return this;
		}

		@Override
		public GuicyfiedBinder<Type> to(
				Class<? extends Type> implementationClass) {
			this.implementationClass = implementationClass;
			return this;
		}

		@Override
		public ScopedBinder toConstructor(
				Constructor<? extends Type> constructor) {
			this.constructor = constructor;
			return this;
		}

		@Override
		public ScopedBinder toProvider(
				Class<? extends Provider<? extends Type>> providerClass) {
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
			AnnotatedBindingBuilder<Type> builder;
			LinkedBindingBuilder<Type> linked = null;
			ScopedBindingBuilder scoped = null;

			if (interfaceClass != null) {
				builder = bind(interfaceClass);
			} else if (literal != null) {
				builder = bind(literal);
			} else {
				throw new IllegalArgumentException(
						ExceptionMessage
								.format("At least one thing interfaceClass or literal must be set.")
								.build());
			}
			if (annotation != null) {
				linked = builder.annotatedWith(annotation);
			}
			if (annotationClass != null) {
				linked = builder.annotatedWith(annotationClass);
			}

			if (implementationClass != null) {
				scoped = (linked != null ? linked : builder)
						.to(implementationClass);
			} else if (constructor != null) {
				scoped = (linked != null ? linked : builder)
						.toConstructor(constructor);
			} else if (providerClass != null) {
				scoped = (linked != null ? linked : builder)
						.toProvider(providerClass);
			}

			if (singleton) {
				(scoped != null ? scoped : builder).in(Scopes.SINGLETON);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(ToStringMessage
						.format(GuicyfiedGenericBinder.class)
						.setParentMessage("Binding")
						.addParameter("type", interfaceClass)
						.addParameter("type impl", implementationClass)
						.addParameter("type constructor", constructor)
						.addParameter("type provider", providerClass)
						.addParameter("type literal", literal)
						.addParameter("annotation", annotation)
						.addParameter("class annotation", annotationClass)
						.addParameter("in singleton scope", singleton).build());
			}
		}
	}
}