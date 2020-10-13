package com.example.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Order;
import com.example.web.feign.fallback.OrderFeignFallbackFactory;

@FeignClient(name = "order", fallbackFactory = OrderFeignFallbackFactory.class)
public interface IOrderFeign {

	@GetMapping("/initialize")
	public void initialize(@RequestParam("storeId") Long storeId) throws Exception;

	@PostMapping("/insertOrder")
	public boolean insertOrder(@RequestParam("storeId") Long storeId, @RequestBody Order order) throws Exception;

}
