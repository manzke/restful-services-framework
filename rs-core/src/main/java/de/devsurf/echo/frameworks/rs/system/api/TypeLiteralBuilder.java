package de.devsurf.echo.frameworks.rs.system.api;

import java.lang.reflect.Type;

import de.devsurf.common.lang.build.Builder;

public interface TypeLiteralBuilder {

	public RawTypeLiteralBuilder fromRawType(Class<?> rawType);

	public static interface RawTypeLiteralBuilder {		
		public Builder<Type> withType(Type... typeArguments);
	}
}