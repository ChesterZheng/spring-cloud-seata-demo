package com.test.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;

@EnableEurekaClient
@EnableFeignClients(basePackages = "com.test")
@ComponentScan(basePackages = "com.test")
@MapperScan(basePackages = "com.test.mysql.dao")
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MysqlAccountApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MysqlAccountApplication.class);
		application.run(args);
	}

}
