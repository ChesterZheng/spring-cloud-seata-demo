package com.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.test")
public class StressTestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(StressTestApplication.class);
		application.run(args);
		Thread.sleep(1000 * 30);
//		if ("1".equals(args[0])) {
//			stressSingleProductTest();
//		} else {
			stressMultiProductsTest();
//		}
	}

	public static void stressSingleProductTest() throws Exception {
		System.out.println("开始单件商品压测。。。");
		String url = "http://localhost:7080/createOrder?storeId=1";
		HttpGet httpGet = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		for (int i = 0; i < 1000000; i++) {
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			response = httpClient.execute(httpGet);
			System.out.println("耗时 = " + EntityUtils.toString(response.getEntity(), "utf-8"));
		}
	}

	public static void stressMultiProductsTest() throws Exception {
		System.out.println("开始多件商品压测。。。");
		String url = "http://localhost:7080/createOrders";
		HttpGet httpGet = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		for (int i = 0; i < 1000000; i++) {
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			response = httpClient.execute(httpGet);
			System.out.println("耗时 = " + EntityUtils.toString(response.getEntity(), "utf-8"));
		}
	}

}
