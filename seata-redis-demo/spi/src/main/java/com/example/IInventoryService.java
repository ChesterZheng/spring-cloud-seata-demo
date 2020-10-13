package com.example;

import com.example.domain.Inventory;

public interface IInventoryService {

	public boolean decrInventorySuccess(Long storeId, Inventory inventory) throws Exception;
	
	public boolean decrInventoryFail(Long storeId, Inventory inventory) throws Exception;

}
