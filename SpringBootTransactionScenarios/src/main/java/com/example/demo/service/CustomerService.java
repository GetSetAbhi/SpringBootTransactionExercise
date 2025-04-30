package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer createCustomer(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer createCustomerButDoNotRollBack(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Customer createCustomerREQUIRED(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public Customer createCustomerMANDATORY(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Customer createCustomerNOTSUPPORTED(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}
	
	public Customer createCustomerNoAnnotation(String customerName) {
		Customer customer = new Customer();
        customer.setName(customerName);
        return customerRepository.save(customer);
	}
}
