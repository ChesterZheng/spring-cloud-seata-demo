package com.test;

import com.test.domain.Order;

public interface IOrderService {

	public boolean insertOrder(Long storeId, Order order) throws Exception;

}
