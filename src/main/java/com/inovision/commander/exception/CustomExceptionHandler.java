package com.inovision.commander.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler { // extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotfoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public final ExceptionResponse handleNotfoundException(NotfoundException nfe, WebRequest request) {
        return new ExceptionResponse(new Date(), nfe.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase());
        // return generateResponse(nfe, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotAllowed.class)
    public final ResponseEntity<ExceptionResponse> handleOperationNotAllowed(OperationNotAllowed one, WebRequest request) {
        return generateResponse(one, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException be, WebRequest request) {
        return generateResponse(be, request, HttpStatus.FORBIDDEN);
    }

    //This is probably used during outbound HTTP connection and get error
    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<ExceptionResponse> handleClientException(HttpClientErrorException hee, WebRequest request) {
        return generateResponse(hee, request, hee.getStatusCode());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ExceptionResponse> handleJsonMappingException(JsonMappingException e, WebRequest request){
        return generateResponse(e, request, HttpStatus.BAD_REQUEST);
    }

    //This is probably used if input data has errorsIllegalArgumentException
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public final ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
//        ExceptionResponse msg = new ExceptionResponse(new Date(), e.getMessage(),
//                "", HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase());
//        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
//    }

    // TODO: Generate same for MethodArgumentNotValidException and

    private ResponseEntity<ExceptionResponse> generateResponse(Throwable re, WebRequest req, HttpStatus status) {
        log.error("Error ********* {}", re.getMessage());
        ExceptionResponse msg = new ExceptionResponse(new Date(), re.getMessage(),
                req.getDescription(false), status.value(),
                status.getReasonPhrase());
        return new ResponseEntity<>(msg, status);
    }
}
