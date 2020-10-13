package com.example.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.IOrderService;
import com.example.domain.Order;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping("/initialize")
	public void initialize(@RequestParam("storeId") Long storeId) throws Exception {
		this.orderService.initialize(storeId);
	}

	@PostMapping("/insertOrder")
	public boolean insertOrder(@RequestParam("storeId") Long storeId, @RequestBody Order order) throws Exception {
		return this.orderService.insertOrder(storeId, order);
	}

}
