package com.unimon.app.common.exception;

public class ForbiddenException extends RuntimeException{
	private static final long serialVersionUID = -3045108975519694715L;

	public ForbiddenException() {
	}
	
	public ForbiddenException(Throwable cause) {
		super(cause);
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}
}
