package de.devsurf.echo.frameworks.rs.system.api;


public interface GenericBinder {
	<Type> TypedBinder<Type> bindClass(Class<Type> implementationClass);

	<Type> TypedBinder<Type> bindType(java.lang.reflect.Type literal);
}
