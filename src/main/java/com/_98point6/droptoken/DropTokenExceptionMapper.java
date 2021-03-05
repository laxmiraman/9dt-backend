package com._98point6.droptoken;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com._98point6.droptoken.service.exception.ErrorMessage;
import com._98point6.droptoken.service.exception.ErrorMessages;
import com._98point6.droptoken.service.exception.IllegalMoveException;
import com._98point6.droptoken.service.exception.InvalidInputException;

public class DropTokenExceptionMapper implements ExceptionMapper<RuntimeException> {
	// private static final Logger logger =
	// LoggerFactory.getLogger(DropTokenExceptionMapper.class);
	public Response toResponse(RuntimeException e) {
		ErrorMessage errorMessage = null;
		int errorCode = 0;
		if (e instanceof IllegalMoveException) {
			errorCode = 400;
			errorMessage = new ErrorMessage(e.getMessage(), ErrorMessages.ILLEGAL_MOVE.name());
		} else if (e instanceof InvalidInputException) {
			errorCode = 400;
			errorMessage = new ErrorMessage(e.getMessage(), ErrorMessages.INVALID_INPUT.name());
		} else {
			errorCode = 500;
			errorMessage = new ErrorMessage(e.getMessage(), ErrorMessages.INTERNAL_SERVER_ERROR.name());
		}

		return Response.status(errorCode).entity(errorMessage).build();
	}
}
