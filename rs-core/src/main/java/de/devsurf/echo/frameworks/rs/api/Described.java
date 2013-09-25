package de.devsurf.echo.frameworks.rs.api;

public class Described {
	private String id;
	private Version version;
	private String description;
	
	public Described(String id, Version version, String description) {
		super();
		this.id = id;
		this.version = version;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public Version getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}
}
