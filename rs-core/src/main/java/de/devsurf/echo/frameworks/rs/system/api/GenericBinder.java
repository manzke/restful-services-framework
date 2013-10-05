package de.devsurf.echo.frameworks.rs.system.api;


public interface GenericBinder {
	<Type> AnnotatedBinder<Type> bindClass(Class<Type> implementationClass);

	<Type> AnnotatedBinder<Type> bindType(java.lang.reflect.Type literal);
	
//	<Type> AnnotatedBinder<Type> bindConstant();
}
