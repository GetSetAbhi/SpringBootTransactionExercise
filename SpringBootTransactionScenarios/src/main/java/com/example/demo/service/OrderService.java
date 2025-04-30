package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void createOrder(String itemName, Customer customer) {
		Order order = new Order();
        order.setItem(itemName);
        order.setCustomer(customer);
        orderRepository.save(order);
	}
}
