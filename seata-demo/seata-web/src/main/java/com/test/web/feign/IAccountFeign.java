package com.test.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.Account;
import com.test.web.feign.fallback.AccountFeignFallbackFactory;

@FeignClient(name = "seata-account", fallbackFactory = AccountFeignFallbackFactory.class)
public interface IAccountFeign {

	@RequestMapping(value = "/insertAccount", method = RequestMethod.POST)
	public boolean insertAccount(@RequestParam("storeId") Long storeId, @RequestBody Account account) throws Exception;

}
