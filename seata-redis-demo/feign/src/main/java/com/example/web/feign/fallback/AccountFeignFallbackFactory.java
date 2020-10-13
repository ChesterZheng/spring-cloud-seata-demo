package com.example.web.feign.fallback;

import org.springframework.stereotype.Component;

import com.example.domain.Account;
import com.example.web.feign.IAccountFeign;

import feign.hystrix.FallbackFactory;
import io.seata.core.context.RootContext;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountFeignFallbackFactory implements FallbackFactory<IAccountFeign> {

	@Override
	public IAccountFeign create(Throwable cause) {
		AccountFeignFallbackFactory.log.error("Fallback-Attention:", cause);
		return new IAccountFeign() {

			@Override
			public boolean insertAccount(Long storeId, Account account) throws Exception {
				System.out.println("### 创建账务信息失败 ###");
				GlobalTransactionContext.reload(RootContext.getXID()).rollback();
				return false;
			}

		};
	}

}
