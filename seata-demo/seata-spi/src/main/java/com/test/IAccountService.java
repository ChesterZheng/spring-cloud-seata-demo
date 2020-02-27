package com.test;

import com.test.domain.Account;

public interface IAccountService {

	public boolean insertAccount(Long storeId, Account account) throws Exception;

}
