package com.test.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.IOrderService;
import com.test.domain.Order;
import com.test.mysql.config.CustomDynamicDataSource;
import com.test.mysql.config.CustomDynamicDataSourceAspectj;
import com.test.mysql.dao.IOrderDao;

@Service("orderService")
@CustomDynamicDataSource(CustomDynamicDataSourceAspectj.MASTER)
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

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
