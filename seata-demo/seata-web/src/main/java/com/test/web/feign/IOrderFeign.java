package com.test.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.Order;
import com.test.web.feign.fallback.OrderFeignFallbackFactory;

@FeignClient(name = "seata-order", fallbackFactory = OrderFeignFallbackFactory.class)
public interface IOrderFeign {

	@RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
	public boolean insertOrder(@RequestParam("storeId") Long storeId, @RequestBody Order order) throws Exception;

}
