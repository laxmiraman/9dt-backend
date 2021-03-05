package com._98point6.droptoken.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalMoveExceptionMapper implements ExceptionMapper<InvalidInputException> {

    @Override
    public Response toResponse(InvalidInputException exception) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                ErrorMessages.ILLEGAL_MOVE.name()
        );
        
        return Response.status(Response.Status.BAD_REQUEST).
                entity(errorMessage).
                build();
    }
    
}