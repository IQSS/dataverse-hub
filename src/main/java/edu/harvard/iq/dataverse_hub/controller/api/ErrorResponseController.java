package edu.harvard.iq.dataverse_hub.controller.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import edu.harvard.iq.dataverse_hub.beans.ServerMessageResponse;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ErrorResponseController {
    
    @ExceptionHandler(Exception.class)
    public ServerMessageResponse errorResponse(Exception e) {
        return new ServerMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ServerMessageResponse noResourceFoundExceptionResponse(Exception e) {
        return new ServerMessageResponse(HttpStatus.NOT_FOUND, e);
    }
    
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ServerMessageResponse badRequestExceptionResponse(Exception e) {
        return new ServerMessageResponse(HttpStatus.BAD_REQUEST, e);
    }

}
