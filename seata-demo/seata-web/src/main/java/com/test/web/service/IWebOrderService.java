package com.test.web.service;

import java.util.Map;

public interface IWebOrderService {

	public long insertOrder(Long storeId) throws Exception;

	public long createOrder(Long storeId) throws Exception;

	public Map<String, Long> createOrders() throws Exception;

}
