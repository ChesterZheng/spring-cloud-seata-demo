package com.example.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example")
@ComponentScan(basePackages = "com.example")
@MapperScan(basePackages = "com.example.mysql.dao")
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MysqlAccountApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MysqlAccountApplication.class);
		application.run(args);
	}

}
