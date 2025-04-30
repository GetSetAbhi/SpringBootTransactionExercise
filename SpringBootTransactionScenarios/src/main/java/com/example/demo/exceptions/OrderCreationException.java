package com.example.demo.exceptions;

public class OrderCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866351593257633570L;

	public OrderCreationException(String message) {
		super(message);
	}
}
