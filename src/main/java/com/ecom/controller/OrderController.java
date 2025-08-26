package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.entity.OrderEntity;
import com.ecom.orderservice.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
public class OrderController {

	//http://localhost:2000/api/orders
	
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<String> placeOrder(@RequestBody OrderEntity order) {
		orderService.saveOrder(order);
		return ResponseEntity.ok("Order placed successfully");
	}
	
	// In OrderController.java

	@GetMapping
	public ResponseEntity<List<OrderEntity>> getAllOrders() {
	    List<OrderEntity> orders = orderService.getAllOrders();
	    return ResponseEntity.ok(orders);
	}

}
