package de.devsurf.echo.frameworks.rs.system.api;

public interface ContextBinder extends Installable {
	ContextBinder bindContext(Class<? extends ContextualObject> contextualTypeClass);
	
	public static interface ContextualObject {
		
	}
}
