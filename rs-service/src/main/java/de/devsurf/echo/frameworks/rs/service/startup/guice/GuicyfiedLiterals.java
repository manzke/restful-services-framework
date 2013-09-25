package de.devsurf.echo.frameworks.rs.service.startup.guice;

import java.lang.reflect.Type;

import com.google.inject.util.Types;

import de.devsurf.common.lang.build.Builder;
import de.devsurf.echo.frameworks.rs.system.api.TypeLiteralBuilder;

public class GuicyfiedLiterals implements TypeLiteralBuilder {	
	@Override
	public GuicyfiedTypeLiteralBuilder fromRawType(Class<?> rawType) {
		return new GuicyfiedTypeLiteralBuilder(rawType);
	}
	
	public class GuicyfiedTypeLiteralBuilder implements RawTypeLiteralBuilder, Builder<Type> {
		private Type rawType;
		private Type[] typeArguments;
		
		private GuicyfiedTypeLiteralBuilder(Class<?> rawType) {
			this.rawType = rawType;
		}
		
		@Override
		public GuicyfiedTypeLiteralBuilder withType(Type... typeArguments) {
			this.typeArguments = typeArguments;
			return this;
		}
		
		@Override
		public Type build() {
			return Types.newParameterizedType(rawType, typeArguments);
		}
	}
}
