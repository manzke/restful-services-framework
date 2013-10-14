package de.devsurf.echo.frameworks.rs.service.status;

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

public enum CustomStatus implements StatusType {

	TOO_MANY_REQUESTS(429, "Too Many Requests");

	private final int code;
	private final String reason;
	private Family family;

	CustomStatus(final int statusCode, final String reasonPhrase) {
		this.code = statusCode;
		this.reason = reasonPhrase;
		switch (code / 100) {
		case 1:
			this.family = Family.INFORMATIONAL;
			break;
		case 2:
			this.family = Family.SUCCESSFUL;
			break;
		case 3:
			this.family = Family.REDIRECTION;
			break;
		case 4:
			this.family = Family.CLIENT_ERROR;
			break;
		case 5:
			this.family = Family.SERVER_ERROR;
			break;
		default:
			this.family = Family.OTHER;
			break;
		}
	}

	/**
	 * Get the class of status code
	 * @return the class of status code
	 */
	public Family getFamily() {
		return family;
	}

	/**
	 * Get the associated status code
	 * @return the status code
	 */
	public int getStatusCode() {
		return code;
	}

	/**
	 * Get the reason phrase
	 * @return the reason phrase
	 */
	public String getReasonPhrase() {
		return toString();
	}

	/**
	 * Get the reason phrase
	 * @return the reason phrase
	 */
	@Override
	public String toString() {
		return reason;
	}
}
