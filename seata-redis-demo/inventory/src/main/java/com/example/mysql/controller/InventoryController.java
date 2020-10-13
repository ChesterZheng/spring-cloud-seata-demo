package com.example.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.IInventoryService;
import com.example.domain.Inventory;

@RestController
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;

	@PostMapping("/decrInventorySuccess")
	public boolean decrInventorySuccess(@RequestParam("storeId") Long storeId, @RequestBody Inventory inventory)
			throws Exception {
		return this.inventoryService.decrInventorySuccess(storeId, inventory);
	}

	@PostMapping("/decrInventoryFail")
	public boolean decrInventoryFail(@RequestParam("storeId") Long storeId, @RequestBody Inventory inventory)
			throws Exception {
		return this.inventoryService.decrInventoryFail(storeId, inventory);
	}

}
