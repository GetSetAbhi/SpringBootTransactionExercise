package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerOrderPayload;
import com.example.demo.service.CustomerOrderService;

@RestController
@RequestMapping(path = "/order")
public class ApiController {
	
	@Autowired
	private CustomerOrderService customerOrderService;

	@PostMapping(path = "/create")
	public ResponseEntity<String> createOrderCustomer(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWithOrder(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	// REQUIRES_NEW
	@PostMapping(path = "/customer/create")
	public ResponseEntity<String> createOnlyCustomer(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWithOrderButDoNotRollBackForCustomer(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/customer/create2")
	public ResponseEntity<String> createOnlyCustomerAndOrder(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWhenNoParentTransaction(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	// MANDATORY
	@PostMapping(path = "/customer/create3")
	public ResponseEntity<String> createCustomerWhenCustomerIsMandatory(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWhenCustomerIsMandatory(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	// MANDATORY
	@PostMapping(path = "/customer/create4")
	public ResponseEntity<String> createCustomerWhenCustomerIsMandatoryCatchException(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWhenCustomerIsMandatoryCatchException(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	
	//NOT_SUPPORTED
	@PostMapping(path = "/customer/create5")
	public ResponseEntity<String> createCustomerWhenCustomerIsNOTSupported(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createCustomerWhenCustomerIsNOTSupported(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
	//NOT_SUPPORTED
	@PostMapping(path = "/customer/create6")
	public ResponseEntity<String> createWhenNoAnnotationOnChildServices(@RequestBody CustomerOrderPayload payload) {
		customerOrderService.createWhenNoAnnotationOnChildServices(payload.getCustomerName(), payload.getOrderItem());
		return new ResponseEntity<String>("Resources Created", HttpStatus.CREATED);
	}
	
}
