package com.example.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IOrderService;
import com.example.domain.Order;
import com.example.mysql.config.CustomDynamicDataSource;
import com.example.mysql.dao.IOrderDao;

@Service("orderService")
@CustomDynamicDataSource
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

	@Override
	public void initialize(Long storeId) throws Exception {
		this.orderDao.initialize(1L);
	}

	@Override
	public boolean insertOrder(Long storeId, Order order) throws Exception {
		Integer result = this.orderDao.insertOrder(storeId, order);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
