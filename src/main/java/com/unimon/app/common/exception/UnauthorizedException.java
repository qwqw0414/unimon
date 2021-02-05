package com.unimon.app.common.exception;

public class UnauthorizedException extends RuntimeException{
	private static final long serialVersionUID = 9041787114996641572L;

	public UnauthorizedException() {
	}
	
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}
	
	public UnauthorizedException(String message) {
		super(message);
	}
	
	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
