package com.inovision.commander.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
    private String error;
    private int status;

    public ExceptionResponse(Date date, String message, String details, int code, String httpReason) {
        this.timestamp = date;
        this.status = code;
        this.message = message;
        this.details = details;
        this.error = httpReason;
    }
}
