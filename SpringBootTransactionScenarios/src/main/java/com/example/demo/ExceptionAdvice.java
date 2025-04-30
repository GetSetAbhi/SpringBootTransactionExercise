package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.OrderCreationException;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(exception = OrderCreationException.class)
	public ResponseEntity<String> getResponseForOrderCreationError(OrderCreationException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
