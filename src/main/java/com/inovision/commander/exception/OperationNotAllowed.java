package com.inovision.commander.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.FORBIDDEN)
public class OperationNotAllowed extends RuntimeException {

	private static final long serialVersionUID = 9079351040605024313L;

	public OperationNotAllowed(String message) {
		super(message);
	}
}
