package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Customer;
import com.example.demo.exceptions.OrderCreationException;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    /**
     * By Default the propagation is propagation.REQUIRED
     * 
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void createCustomerWithOrder(String customerName, String itemName) {
        
    	Customer customer = customerService.createCustomer(customerName);

        orderService.createOrder(itemName, customer);

        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
    }
    
    /**
     * In This case customer will be created because it
     * creation happens inside another transaction
     * due to requires_new propagation.
     * 
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void createCustomerWithOrderButDoNotRollBackForCustomer(String customerName, String itemName) {
        
    	Customer customer = customerService.createCustomerButDoNotRollBack(customerName);

        orderService.createOrder(itemName, customer);

        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
    }
    
    /**
     * In This case customer and order will be created because it
     * they both happen inside separate transactions and not parent transaction was present.
     * 
     * **/
    public void createCustomerWhenNoParentTransaction(String customerName, String itemName) {
        
    	Customer customer = customerService.createCustomerREQUIRED(customerName);

        orderService.createOrder(itemName, customer);
        
        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
    }
    
    /**
     * MANDATORY
     * In This case customer and order will not be created because it
     * customer creation is annotated with mandatory which means, a 
     * transaction should already exist
     * 
     * **/
    public void createCustomerWhenCustomerIsMandatory(String customerName, String itemName) {
        
    	Customer customer = customerService.createCustomerMANDATORY(customerName);

        orderService.createOrder(itemName, customer);
        
        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
    }
    
    /**
     * In This case only order is created and even though
     * customer creation throws an exception, it's swallowed
     * so it doesn't affect the parent method.
     * If I rethrow the exception inside catch, then order creation also fails
     * customer creation is annotated with mandatory which means, a 
     * transaction should already exist
     * 
     * **/
    public void createCustomerWhenCustomerIsMandatoryCatchException(String customerName, String itemName) {
        
    	Customer customer = null;
    	try {
    		customer = customerService.createCustomerMANDATORY(customerName);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		//throw e;
		}

        orderService.createOrder(itemName, customer);
        
        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
    }

    
    /**
     * In This case customer is created because
     * it doesn't happen inside the transaction of parent method
     * as parent method transaction is suspended due to NOT_SUPPORTED
     * annotation on customer creation.
     * Order is not created because it is part of same transaction as parent.
     * 
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
	public void createCustomerWhenCustomerIsNOTSupported(String customerName, String itemName) {
		Customer customer = customerService.createCustomerNOTSUPPORTED(customerName);
		orderService.createOrder(itemName, customer);
        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
	}
    
    /**
     * In This case customer is not created.
     * Customer creation method automatically becomes part of
     * parent transaction as no transaction annotation
     * exists on customer creation method.
     * 
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
	public void createWhenNoAnnotationOnChildServices(String customerName, String itemName) {
		Customer customer = customerService.createCustomerNoAnnotation(customerName);
        if ("fail".equalsIgnoreCase(itemName)) {
            throw new OrderCreationException("Forcing rollback for testing");
        }
	}
}

