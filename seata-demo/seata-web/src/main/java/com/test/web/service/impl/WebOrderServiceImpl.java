package com.test.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.Account;
import com.test.domain.Inventory;
import com.test.domain.Order;
import com.test.web.feign.IAccountFeign;
import com.test.web.feign.IInventoryFeign;
import com.test.web.feign.IOrderFeign;
import com.test.web.service.IWebOrderService;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;

@Service("webOrderService")
public class WebOrderServiceImpl implements IWebOrderService {

	@Autowired
	private IOrderFeign orderFeign;

	@Autowired
	private IInventoryFeign inventoryFeign;

	@Autowired
	private IAccountFeign accountFeign;

	@Override
	public long insertOrder(Long storeId) throws Exception {
		long startTime = System.currentTimeMillis();
		Date date = new Date();
		Order order = new Order();
		String orderId = String.valueOf(System.currentTimeMillis());
		order.setOrderId(orderId);
		order.setProductId("1");
		order.setProductName("商品1");
		order.setCreatedAt(date);
		boolean insertOrder = this.orderFeign.insertOrder(storeId, order);
		if (insertOrder) {
			System.out.println("创建订单信息成功");
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/**
	 * 压测1件商品
	 */
	@GlobalTransactional(timeoutMills = 5000, rollbackFor = Exception.class)
	@Override
	public long createOrder(Long storeId) throws Exception {
		long startTime = System.currentTimeMillis();
		Date date = new Date();
		Order order = new Order();
		String orderId = String.valueOf(System.currentTimeMillis());
		order.setOrderId(orderId);
		order.setProductId("1");
		order.setProductName("商品1");
		order.setCreatedAt(date);
		boolean insertOrder = this.orderFeign.insertOrder(storeId, order);
		if (insertOrder) {
			System.out.println("创建订单信息成功");
		}

		Inventory inventory = new Inventory();
		inventory.setProductId("1");
		inventory.setAmount(1L);
		inventory.setUpdatedAt(date);
		boolean decrInventory = this.inventoryFeign.decrInventoryFail(storeId, inventory);
		if (decrInventory) {
			System.out.println("核减库存信息成功");
		}

		Account account = new Account();
		account.setOrderId(orderId);
		account.setProfit(100);
		account.setCreatedAt(date);
		boolean insertAccout = this.accountFeign.insertAccount(storeId, account);
		if (insertAccout) {
			System.out.println("创建账务信息成功");
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/**
	 * 压测100件商品
	 */
	@GlobalTransactional(timeoutMills = 5000, rollbackFor = Exception.class)
	@Override
	public Map<String, Long> createOrders() throws Exception {
		Map<String, Long> resultMap = new HashMap<String, Long>();
		long startTime = System.currentTimeMillis();
		Long storeId = null;
		if (startTime % 5 == 0) {
			storeId = 1L;
		} else {
			storeId = 2L;
		}
		Date date = new Date();
		Order order = new Order();
		String orderId = String.valueOf(System.currentTimeMillis());
		order.setOrderId(orderId);
		String randomProductId = getRandomProductId();
		order.setProductId(randomProductId);
		order.setProductName("商品" + randomProductId);
		order.setCreatedAt(date);
		boolean insertOrder = this.orderFeign.insertOrder(storeId, order);
		if (insertOrder) {
			System.out.println("创建订单信息成功");
		}

		Inventory inventory = new Inventory();
		inventory.setProductId(randomProductId);
		inventory.setAmount(1L);
		inventory.setUpdatedAt(date);
		boolean decrInventory = false;
		if (startTime % 2 == 0) {
			decrInventory = this.inventoryFeign.decrInventorySuccess(storeId, inventory);
		} else {
			decrInventory = this.inventoryFeign.decrInventoryFail(storeId, inventory);
		}
		if (decrInventory) {
			System.out.println("核减库存信息成功");
		} else {
			GlobalTransactionContext.reload(RootContext.getXID()).rollback();
		}

		Account account = new Account();
		account.setOrderId(orderId);
		account.setProfit(100);
		account.setCreatedAt(date);
		boolean insertAccout = this.accountFeign.insertAccount(storeId, account);
		if (insertAccout) {
			System.out.println("创建账务信息成功");
		}
		long endTime = System.currentTimeMillis();
		resultMap.put(randomProductId, Long.valueOf(endTime - startTime));
		return resultMap;
	}

	private String getRandomProductId() {
		return String.valueOf((int) (Math.random() * 100));
	}

}
