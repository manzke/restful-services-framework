package de.devsurf.echo.frameworks.rs.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import de.devsurf.echo.frameworks.rs.exceptions.api.ServiceApiException;

public class ApiExceptionMapper implements ExceptionMapper<ServiceApiException>{

	public Response toResponse(ServiceApiException exception) {
		// TODO Auto-generated method stub
		return null;
	}

}
