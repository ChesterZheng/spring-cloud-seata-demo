package com.example.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Inventory;
import com.example.web.feign.fallback.InventoryFeignFallbackFactory;

@FeignClient(name = "inventory", fallbackFactory = InventoryFeignFallbackFactory.class)
public interface IInventoryFeign {

	@RequestMapping(value = "/decrInventorySuccess", method = RequestMethod.POST)
	public boolean decrInventorySuccess(@RequestParam("storeId") Long storeId, @RequestBody Inventory inventory)
			throws Exception;

	@RequestMapping(value = "/decrInventoryFail", method = RequestMethod.POST)
	public boolean decrInventoryFail(@RequestParam("storeId") Long storeId, @RequestBody Inventory inventory)
			throws Exception;

}
