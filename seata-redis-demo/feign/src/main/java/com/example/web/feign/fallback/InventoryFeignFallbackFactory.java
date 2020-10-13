package com.example.web.feign.fallback;

import org.springframework.stereotype.Component;

import com.example.domain.Inventory;
import com.example.web.feign.IInventoryFeign;

import feign.hystrix.FallbackFactory;
import io.seata.core.context.RootContext;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InventoryFeignFallbackFactory implements FallbackFactory<IInventoryFeign> {

	@Override
	public IInventoryFeign create(Throwable cause) {
		InventoryFeignFallbackFactory.log.error("Fallback-Attention:", cause);
		return new IInventoryFeign() {

			@Override
			public boolean decrInventorySuccess(Long storeId, Inventory inventory) throws Exception {
				System.out.println("### 核减库存失败 ###");
				GlobalTransactionContext.reload(RootContext.getXID()).rollback();
				return false;
			}

			@Override
			public boolean decrInventoryFail(Long storeId, Inventory inventory) throws Exception {
				System.out.println("### 核减库存失败 ###");
				GlobalTransactionContext.reload(RootContext.getXID()).rollback();
				return false;
			}

		};
	}

}
