package com.user.exception;

import java.util.function.Supplier;

public class CustomeException extends RuntimeException {
	
	
	public CustomeException() {
		super("Not Found..");
	}
	
	public CustomeException(String message) {
		super(message);
	}
}
