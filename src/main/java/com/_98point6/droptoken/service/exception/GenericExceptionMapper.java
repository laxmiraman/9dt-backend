package com._98point6.droptoken.service.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                ErrorMessages.MALFORMED_REQUEST.name()
        );        
        return Response.status(Response.Status.BAD_REQUEST).
                entity(errorMessage).
                build();
    }
    
}