package com.test.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.web.service.IWebOrderService;

@RestController
public class WebOrderController {

	@Autowired
	private IWebOrderService webOrderService;
	
	@RequestMapping("/insertOrder")
	public String insertOrder(@RequestParam("storeId") Long storeId) throws Exception {
		long spent = this.webOrderService.insertOrder(storeId);
		return JSON.toJSONString(spent);
	}

	@RequestMapping("/createOrder")
	public String createOrder(@RequestParam("storeId") Long storeId) throws Exception {
		long spent = this.webOrderService.createOrder(storeId);
		return JSON.toJSONString(spent);
	}

	@RequestMapping("/createOrders")
	public String createOrders() throws Exception {
		Map<String, Long> resultMap = this.webOrderService.createOrders();
		return JSON.toJSONString(resultMap);
	}

}
