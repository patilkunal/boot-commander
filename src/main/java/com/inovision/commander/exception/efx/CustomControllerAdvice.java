package com.inovision.commander.exception.efx;

import com.inovision.commander.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@Slf4j
//@RestControllerAdvice
public class CustomControllerAdvice {

    private static final String JSON_PARSE_ERROR = "JSON Parse Error";
    private static final String INVALID_ATTRIBUTE = "INVALID_ATTRIBUTE";
    private static final String INVALID_VALUE = "INVALID_VALUE";
    private static final String FORMAT_STRING = ":" + " is not in correct format.";
    private static final String VALIDATION_ERROR = "Validation Error";
    private static final String VALIDATION_ERROR_PROJECT_SCOPE = "Project";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ValidationErrorMessages> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.info("Exception handling for HttpMessageNotReadableException");
        return new ResponseEntity<>(getValidationErrorMessages(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<ValidationErrorMessages> handleValidationException(MethodArgumentNotValidException ex) {
        log.info("Exception handling for Everify validation exception before sending to government");
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorMessage> errorMessages = new ArrayList();
        fieldErrors.stream().forEach(e -> errorMessages.add(new ErrorMessage(e)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ValidationErrorMessages(HttpStatus.BAD_REQUEST,
                        VALIDATION_ERROR,VALIDATION_ERROR_PROJECT_SCOPE, errorMessages));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionResponse> handelIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "", 400, "Bad request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    private ValidationErrorMessages getValidationErrorMessages(HttpMessageNotReadableException ex) {
        ValidationErrorMessages validationErrorMessages = new ValidationErrorMessages();
        validationErrorMessages.setErrorMessageList(getErrorMessages(ex));
        validationErrorMessages.setStatus(HttpStatus.BAD_REQUEST);
        validationErrorMessages.setMessage(VALIDATION_ERROR);
        validationErrorMessages.setSource(VALIDATION_ERROR_PROJECT_SCOPE);
        return validationErrorMessages;
    }

    private List<ErrorMessage> getErrorMessages(HttpMessageNotReadableException ex) {
        List<ErrorMessage> errorList = new ArrayList<>();
        ErrorMessage errorMessage = new ErrorMessage();
        if (ex.getCause() instanceof JsonParseException) {
            errorMessage.setErrorCode(JSON_PARSE_ERROR);
            errorMessage.setMessage(((JsonParseException) ex.getCause()).getOriginalMessage());
            errorMessage.setAttributeName(INVALID_ATTRIBUTE);
            errorMessage.setValue(INVALID_VALUE);
        } else if (ex.getCause() instanceof MismatchedInputException) {
            errorMessage.setMessage(((MismatchedInputException) ex.getCause()).getPath().get(0).getFieldName() + FORMAT_STRING);
            errorMessage.setErrorCode(JSON_PARSE_ERROR);
            errorMessage.setAttributeName(((MismatchedInputException) ex.getCause()).getPath().get(0).getFieldName());
            errorMessage.setValue(((MismatchedInputException) ex.getCause()).getPath().get(0).getFieldName());
        }
        errorList.add(errorMessage);
        return errorList;
    }
}
