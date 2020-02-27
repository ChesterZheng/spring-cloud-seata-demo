package com.test.web.feign.fallback;

import org.springframework.stereotype.Component;

import com.test.domain.Order;
import com.test.web.feign.IOrderFeign;

import feign.hystrix.FallbackFactory;
import io.seata.core.context.RootContext;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderFeignFallbackFactory implements FallbackFactory<IOrderFeign> {

	@Override
	public IOrderFeign create(Throwable cause) {
		OrderFeignFallbackFactory.log.error("Fallback-Attention:", cause);
		return new IOrderFeign() {

			@Override
			public boolean insertOrder(Long storeId, Order order) throws Exception {
				System.out.println("### 创建订单失败 ###");
				GlobalTransactionContext.reload(RootContext.getXID()).rollback();
				return false;
			}

		};
	}

}
