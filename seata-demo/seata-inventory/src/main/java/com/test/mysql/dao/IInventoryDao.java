package com.test.mysql.dao;

import org.apache.ibatis.annotations.Param;

import com.test.domain.Inventory;

public interface IInventoryDao {

	public Integer decrInventory(@Param("storeId") Long storeId, @Param("inventory") Inventory inventory)
			throws Exception;

}
