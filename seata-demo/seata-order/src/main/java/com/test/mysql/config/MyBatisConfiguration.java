package com.test.mysql.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.filter.stat.StatFilter;

@Configuration
@PropertySource(value = "classpath:jdbc.properties")
public class MyBatisConfiguration {

	@Resource
	private DataSource dataSource;

	@Value("${general.slowSqlMillis}")
	private Integer slowSqlMillis;

	@Value("${general.logSlowSql}")
	private Boolean logSlowSql;

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = null;
		try {
			sqlSessionFactoryBean = new SqlSessionFactoryBean();
			// 类型别名
			// sqlSessionFactoryBean.setTypeAliasesPackage(this.typeAliasesPackage);
			sqlSessionFactoryBean.setDataSource(this.dataSource);
			sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources("classpath:com/test/mysql/dao/**/*.xml"));
			// 全局配置
			// sqlSessionFactoryBean.setConfigLocation(new
			// DefaultResourceLoader().getResource(this.configLocation));
			// 添加插件
			// sqlSessionFactoryBean.setPlugins(new Interceptor[] {});
			return sqlSessionFactoryBean.getObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Bean
	public StatFilter statFilter() {
		StatFilter statFilter = new StatFilter();
		statFilter.setSlowSqlMillis(slowSqlMillis);
		statFilter.setLogSlowSql(logSlowSql);
		return statFilter;
	}

}
