package de.devsurf.echo.frameworks.rs.di.api;

public interface ContextBinder extends Installable {
	ContextBinder bindContext(Class<? extends ContextualObject> contextualTypeClass);
	
	public static interface ContextualObject {
		
	}
}
