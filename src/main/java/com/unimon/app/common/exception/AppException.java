package com.unimon.app.common.exception;

public class AppException extends RuntimeException{

	private static final long serialVersionUID = 8132651945524176805L;

	public AppException() {
	}
	
	public AppException(Throwable cause) {
		super(cause);
	}
	
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
