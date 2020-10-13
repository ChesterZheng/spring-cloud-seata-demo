package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.service.IWebOrderService;

@RestController
public class InitializeController {

	@Autowired
	private IWebOrderService orderService;

	@GetMapping("/initialize")
	public void initialize() throws Exception {
		this.orderService.initialize();
	}

}
