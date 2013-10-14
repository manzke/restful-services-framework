package de.devsurf.echo.frameworks.rs.api;

public interface Description {
	String id();

	String description();

	String version();

	public static class DefaultDescription {
		private String id;
		private Version version;
		private String description;

		public DefaultDescription(String id, Version version, String description) {
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
}
