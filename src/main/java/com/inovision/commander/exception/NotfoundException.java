package com.inovision.commander.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//When no reason is set, it is set from the Exception message
@ResponseStatus(code=HttpStatus.NOT_FOUND) //, reason="Requested resource does not exist")
public class NotfoundException extends Exception {

	private static final long serialVersionUID = 6424847318391944950L;

	public NotfoundException(String message) {
		super(message);
	}
	
	public NotfoundException(String message, Throwable t) {
		super(message, t);
	}
	
	public NotfoundException(String message, Exception e) {
		super(message, e);
	}
	
}
