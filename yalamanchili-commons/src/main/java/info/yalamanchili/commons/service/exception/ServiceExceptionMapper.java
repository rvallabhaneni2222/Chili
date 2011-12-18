package info.yalamanchili.commons.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements
		ExceptionMapper<ServiceException> {

	public Response toResponse(ServiceException exception) {
		ResponseBuilder builder = Response.status(exception.getStatusCode());

		if (exception.getErrors() != null
				&& exception.getErrors().getErrors().size() > 0) {
			builder.entity(exception.getErrors());
			builder.type(exception.getMediaType());
		}

		return builder.build();
	}
}