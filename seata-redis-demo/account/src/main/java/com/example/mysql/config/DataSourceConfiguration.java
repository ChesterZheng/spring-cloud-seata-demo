package com.example.mysql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource(value = "classpath:jdbc.properties")
public class DataSourceConfiguration {

	@Value("${master.driverClassName}")
	private String masterDriverClassName;

	@Value("${master.url}")
	private String masterJdbcUrl;

	@Value("${master.username}")
	private String masterUsername;

	@Value("${master.password}")
	private String masterPassword;

	@Value("${seperator1.driverClassName}")
	private String seperator1DriverClassName;

	@Value("${seperator1.url}")
	private String seperator1JdbcUrl;

	@Value("${seperator1.username}")
	private String seperator1Username;

	@Value("${seperator1.password}")
	private String seperator1Password;

	@Value("${seperator2.driverClassName}")
	private String seperator2DriverClassName;

	@Value("${seperator2.url}")
	private String seperator2JdbcUrl;

	@Value("${seperator2.username}")
	private String seperator2Username;

	@Value("${seperator2.password}")
	private String seperator2Password;

	// ----- 连接池属性配置 ----- //
	@Value("${general.pool.initialSize}")
	private Integer initialSize;

	@Value("${general.pool.minIdle}")
	private Integer minIdle;

	@Value("${general.pool.maxActive}")
	private Integer maxActive;

	@Value("${general.pool.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${general.pool.timeBetweenEvictionRunsMillis}")
	private Integer timeBetweenEvictionRunsMillis;

	@Value("${general.pool.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;

	@Value("${general.pool.validationQuery}")
	private String validationQuery;

	@Value("${general.pool.testWhileIdle}")
	private Boolean testWhileIdle;

	@Value("${general.pool.testOnBorrow}")
	private Boolean testOnBorrow;

	@Value("${general.pool.testOnReturn}")
	private Boolean testOnReturn;
	// ----- 连接池属性配置 ----- //

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 主库
	 * @Date 2019年5月23日下午1:54:21
	 * @return
	 * @ReturnType DruidDataSource
	 */
	@Bean(name = "master", destroyMethod = "close")
	public DruidDataSource master() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(this.masterDriverClassName);
		// 连接信息
		druidDataSource.setUrl(this.masterJdbcUrl);
		druidDataSource.setUsername(this.masterUsername);
		druidDataSource.setPassword(this.masterPassword);
		// 连接池配置
		return this.setDataSourcePoolConfig(druidDataSource);
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 分库1
	 * @Date 2019年5月23日下午1:54:32
	 * @return
	 * @ReturnType DruidDataSource
	 */
	@Bean(name = "seperator1", destroyMethod = "close")
	public DruidDataSource seperator1() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(this.seperator1DriverClassName);
		// 连接信息
		druidDataSource.setUrl(this.seperator1JdbcUrl);
		druidDataSource.setUsername(this.seperator1Username);
		druidDataSource.setPassword(this.seperator1Password);
		// 连接池配置
		return this.setDataSourcePoolConfig(druidDataSource);
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 分库2
	 * @Date 2019年5月23日下午1:54:41
	 * @return
	 * @ReturnType DruidDataSource
	 */
	@Bean(name = "seperator2", destroyMethod = "close")
	public DruidDataSource seperator2() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(this.seperator2DriverClassName);
		// 连接信息
		druidDataSource.setUrl(this.seperator2JdbcUrl);
		druidDataSource.setUsername(this.seperator2Username);
		druidDataSource.setPassword(this.seperator2Password);
		// 连接池配置
		return this.setDataSourcePoolConfig(druidDataSource);
	}

	@Primary
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		// 默认数据源
		dynamicDataSource.setDefaultTargetDataSource(this.master());
		// 配置多数据源
		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
		dataSourceMap.put("seperator1", this.seperator1());
		dataSourceMap.put("seperator2", this.seperator2());
		dynamicDataSource.setTargetDataSources(dataSourceMap);
		return dynamicDataSource;
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 连接池配置
	 * @Date 2019年5月23日下午6:04:29
	 * @param druidDataSource
	 * @return
	 * @ReturnType DruidDataSource
	 */
	private DruidDataSource setDataSourcePoolConfig(DruidDataSource druidDataSource) {
		druidDataSource.setInitialSize(this.initialSize);
		druidDataSource.setMinIdle(this.minIdle);
		druidDataSource.setMaxActive(this.maxActive);
		druidDataSource.setMaxWait(this.maxWaitMillis);
		druidDataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
		druidDataSource.setValidationQuery(this.validationQuery);
		druidDataSource.setTestWhileIdle(this.testWhileIdle);
		druidDataSource.setTestOnBorrow(this.testOnBorrow);
		druidDataSource.setTestOnReturn(this.testOnReturn);
		return druidDataSource;
	}

}
