package com.example.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IAccountService;
import com.example.domain.Account;
import com.example.mysql.config.CustomDynamicDataSource;
import com.example.mysql.dao.IAccountDao;

@Service("accountService")
@CustomDynamicDataSource
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	@Override
	public boolean insertAccount(Long storeId, Account account) throws Exception {
		Integer result = this.accountDao.insertAccount(storeId, account);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
