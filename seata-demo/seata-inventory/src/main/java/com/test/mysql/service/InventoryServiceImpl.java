package com.test.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.IInventoryService;
import com.test.domain.Inventory;
import com.test.mysql.config.CustomDynamicDataSource;
import com.test.mysql.dao.IInventoryDao;

@Service("inventoryService")
@CustomDynamicDataSource
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private IInventoryDao inventoryDao;

	@Override
	public boolean decrInventorySuccess(Long storeId, Inventory inventory) throws Exception {
		Integer result = this.inventoryDao.decrInventory(storeId, inventory);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean decrInventoryFail(Long storeId, Inventory inventory) throws Exception {
		return false;
	}

}
