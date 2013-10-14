package de.devsurf.echo.frameworks.rs.di.api;

import java.lang.annotation.Annotation;

public interface AnnotatedBinder<Type> extends TypedBinder<Type> {
	TypedBinder<Type> annotatedWith(Class<? extends Annotation> annotationClass);
	
	TypedBinder<Type> annotatedWith(Annotation annotation);
	
	TypedBinder<Type> named(String name);
}
