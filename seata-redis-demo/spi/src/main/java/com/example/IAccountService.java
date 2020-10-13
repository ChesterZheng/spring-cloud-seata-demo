package com.example;

import com.example.domain.Account;

public interface IAccountService {

	public boolean insertAccount(Long storeId, Account account) throws Exception;

}
