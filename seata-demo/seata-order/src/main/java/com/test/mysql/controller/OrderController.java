package com.test.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.IOrderService;
import com.test.domain.Order;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping("/insertOrder")
	public boolean insertOrder(@RequestParam("storeId") Long storeId, @RequestBody Order order) throws Exception {
		return this.orderService.insertOrder(storeId, order);
	}

}
