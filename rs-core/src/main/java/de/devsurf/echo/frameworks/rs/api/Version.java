package de.devsurf.echo.frameworks.rs.api;


public interface Version extends Typed {
	
	final static Type VERSION_TYPE = new Type() {
		@Override
		public String value() {
			return "version";
		}
	};

	int getMajor();

	int getMinor();

	int getPatchLevel();
	
	boolean isUnknown();

}