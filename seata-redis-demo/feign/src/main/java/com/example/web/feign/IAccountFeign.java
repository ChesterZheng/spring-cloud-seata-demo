package com.example.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Account;
import com.example.web.feign.fallback.AccountFeignFallbackFactory;

@FeignClient(name = "account", fallbackFactory = AccountFeignFallbackFactory.class)
public interface IAccountFeign {

	@RequestMapping(value = "/insertAccount", method = RequestMethod.POST)
	public boolean insertAccount(@RequestParam("storeId") Long storeId, @RequestBody Account account) throws Exception;

}
