package com.test.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.IAccountService;
import com.test.domain.Account;
import com.test.mysql.config.CustomDynamicDataSource;
import com.test.mysql.dao.IAccountDao;

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
