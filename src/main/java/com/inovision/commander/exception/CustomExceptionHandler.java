package com.inovision.commander.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotfoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotfoundException(NotfoundException nfe, WebRequest request) {
        return generateResponse(nfe, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotAllowed.class)
    public final ResponseEntity<ExceptionResponse> handleOperationNotAllowed(OperationNotAllowed one, WebRequest request) {
        return generateResponse(one, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException be, WebRequest request) {
        return generateResponse(be, request, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ExceptionResponse> generateResponse(RuntimeException re, WebRequest req, HttpStatus status) {
        ExceptionResponse msg = new ExceptionResponse(new Date(), re.getMessage(),
                req.getDescription(false), status.value(),
                status.getReasonPhrase());
        return new ResponseEntity<>(msg, status);
    }
}
