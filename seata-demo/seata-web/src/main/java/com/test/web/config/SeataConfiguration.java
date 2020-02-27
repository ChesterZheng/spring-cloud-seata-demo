package com.test.web.config;

import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;

@Configuration
public class SeataConfiguration implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		String xid = RootContext.getXID();
		if (xid != null && !"".equals(xid) && !"null".equalsIgnoreCase(xid)) {
			System.out.println("Feign获取到的分布式事务xid = " + xid);
		}
		template.header("seata-xid", xid);
	}

}
