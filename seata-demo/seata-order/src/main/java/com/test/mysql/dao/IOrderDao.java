package com.test.mysql.dao;

import org.apache.ibatis.annotations.Param;

import com.test.domain.Order;

public interface IOrderDao {

	public Integer insertOrder(@Param("storeId") Long storeId, @Param("order") Order order) throws Exception;

}
