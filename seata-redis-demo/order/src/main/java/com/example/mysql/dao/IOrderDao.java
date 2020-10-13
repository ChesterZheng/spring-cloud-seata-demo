package com.example.mysql.dao;

import org.apache.ibatis.annotations.Param;

import com.example.domain.Order;

public interface IOrderDao {

	public void initialize(@Param("storeId") Long storeId) throws Exception;

	public Integer insertOrder(@Param("storeId") Long storeId, @Param("order") Order order) throws Exception;

}
