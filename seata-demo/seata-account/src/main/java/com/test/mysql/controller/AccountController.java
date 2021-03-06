package com.test.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.IAccountService;
import com.test.domain.Account;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@PostMapping("/insertAccount")
	public boolean insertAccount(@RequestParam("storeId") Long storeId, @RequestBody Account account) throws Exception {
		return this.accountService.insertAccount(storeId, account);
	}

}
