package com.test.mysql.dao;

import org.apache.ibatis.annotations.Param;

import com.test.domain.Account;

public interface IAccountDao {

	public Integer insertAccount(@Param("storeId") Long storeId, @Param("account") Account account) throws Exception;

}
