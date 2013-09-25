package de.devsurf.echo.frameworks.rs.service.exceptions;

public class CorruptConfigurationException extends RuntimeException {
	private static final long serialVersionUID = -301120336922554317L;

	public CorruptConfigurationException() {
	}

	public CorruptConfigurationException(String message) {
		super(message);
	}

	public CorruptConfigurationException(Throwable cause) {
		super(cause);
	}

	public CorruptConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CorruptConfigurationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
