package de.devsurf.echo.frameworks.rs.di.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;

import javax.inject.Provider;

public interface TypedBinder<Type> extends ScopedBinder {
	ScopedBinder to(Class<? extends Type> implementationClass);
	
	ScopedBinder to(Type implementation);
	
	ScopedBinder toConstructor(Constructor<? extends Type> constructor);
	
	ScopedBinder toProvider(Class<? extends Provider<? extends Type>> providerClass);
	
	public abstract class TypedLiteral<T> {

	    /**
	     * Store the actual type (direct subclass of TypeLiteral).
	     */
	    private transient java.lang.reflect.Type type;

	    /**
	     * Store the actual raw parameter type.
	     */
	    private transient Class<T> rawType;

	    protected TypedLiteral() {
	    }

	    /**
	     * @return the actual type represented by this object
	     */
	    public final java.lang.reflect.Type getType() {
	        if (type == null) {
	            // Get the class that directly extends TypeLiteral<?>
	            Class<?> typeLiteralSubclass = getTypeLiteralSubclass(this.getClass());
	            if (typeLiteralSubclass == null) {
	                throw new RuntimeException(getClass() + " is not a subclass of TypeLiteral<T>");
	            }

	            // Get the type parameter of TypeLiteral<T> (aka the T value)
	            type = getTypeParameter(typeLiteralSubclass);
	            if (type == null) {
	                throw new RuntimeException(getClass() + " does not specify the type parameter T of TypeLiteral<T>");
	            }
	        }
	        return type;
	    }

	    public final java.lang.reflect.Type[] getParameterTypes() {
	        type = getType();
	        if (type instanceof ParameterizedType) {
	            return ((ParameterizedType) type).getActualTypeArguments();
	        } else {
	            return new java.lang.reflect.Type[0];
	        }

	    }

	    /**
	     * @return the raw type represented by this object
	     */
	    @SuppressWarnings("unchecked")
	    public final Class<T> getRawType() {

	        if (rawType == null) {

	            // Get the actual type
	        	java.lang.reflect.Type t = getType();
	            return (Class<T>) getRawType(t);
	        }

	        return rawType;
	    }

	    public static Class<?> getRawType(java.lang.reflect.Type type) {
	        if (type instanceof Class) {

	            return (Class<?>) type;

	        } else if (type instanceof ParameterizedType) {

	            ParameterizedType parameterizedType = (ParameterizedType) type;
	            return (Class<?>) parameterizedType.getRawType();

	        } else if (type instanceof GenericArrayType) {

	            return Object[].class;

	        } else if (type instanceof WildcardType) {
	            return null;
	        } else {
	            throw new RuntimeException("Illegal type");
	        }
	    }

	    /**
	     * Return the direct child class that extends TypeLiteral<T>
	     * @param clazz processed class
	     */
	    private static Class<?> getTypeLiteralSubclass(Class<?> clazz) {

	        // Start with super class
	        Class<?> superClass = clazz.getSuperclass();

	        if (superClass.equals(TypedLiteral.class)) {
	            // Super class is TypeLiteral, return the current class
	            return clazz;
	        } else if (superClass.equals(Object.class)) {
	            // Hmm, strange case, we don not extends TypeLiteral !
	            return null;
	        } else {
	            // Continue processing, one level deeper
	            return (getTypeLiteralSubclass(superClass));
	        }
	    }

	    /**
	     * Return the value of the type parameter of TypeLiteral<T>.
	     * @param typeLiteralSubclass subClass of TypeLiteral<T> to analyze
	     * @return the parametrized type of TypeLiteral<T> (aka T)
	     */
	    private static java.lang.reflect.Type getTypeParameter(Class<?> typeLiteralSubclass) {

	        // Access the typeLiteral<T> super class using generics
	    	java.lang.reflect.Type type = typeLiteralSubclass.getGenericSuperclass();
	        if (type instanceof ParameterizedType) {
	            // TypeLiteral is indeed parametrized
	            ParameterizedType parameterizedType = (ParameterizedType) type;
	            if (parameterizedType.getActualTypeArguments().length == 1) {
	                // Return the value of the type parameter (aka T)
	                return parameterizedType.getActualTypeArguments()[0];
	            }
	        }
	        return null;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj instanceof TypedLiteral<?>) {

	            // Compare inner type for equality
	            TypedLiteral<?> that = (TypedLiteral<?>) obj;
	            return this.getType().equals(that.getType());
	        }
	        return false;
	    }

	    @Override
	    public int hashCode() {
	        return getType().hashCode();
	    }

	    @Override
	    public String toString() {
	        return getType().toString();
	    }
	}
}
