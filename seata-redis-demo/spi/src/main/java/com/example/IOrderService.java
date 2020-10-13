package com.example;

import com.example.domain.Order;

public interface IOrderService {

	public void initialize(Long storeId) throws Exception;

	public boolean insertOrder(Long storeId, Order order) throws Exception;

}
