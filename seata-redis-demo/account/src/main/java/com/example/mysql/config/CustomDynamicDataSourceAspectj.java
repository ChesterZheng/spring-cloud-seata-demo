package com.example.mysql.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 动态切换数据库数据源自定义切面
 * 
 * @Author Chester_Zheng
 * @Date 2019年5月23日下午2:24:38
 * @Tags
 */
@Aspect
@Order(1)
@Component
public class CustomDynamicDataSourceAspectj {

	private Logger logger = LoggerFactory.getLogger(CustomDynamicDataSourceAspectj.class);

	/**
	 * 默认数据源
	 */
	public static final String MASTER = "master";

	public static final String SEPERATOR1 = "seperator1";

	public static final String SEPERATOR2 = "seperator2";

	@Pointcut("execution (* com.test.mysql.service..*..*(..))")
	public void declareJoinPointExpression() {
	}

	@SuppressWarnings("rawtypes")
	@Before("declareJoinPointExpression()")
	public void beforeSwitchDataSource(JoinPoint joinPoint) {
		// 获得当前访问的class
		Class<?> className = joinPoint.getTarget().getClass();
		// 获得访问的方法名
		String methodName = joinPoint.getSignature().getName();
		// 得到方法的参数的类型
		Class[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
		try {
			// 得到访问的对象
			Class<? extends Object> cls = joinPoint.getTarget().getClass();
			// 判断是否存在@CustomDynamicDataSource注解
			if (cls.isAnnotationPresent(CustomDynamicDataSource.class)) {
				CustomDynamicDataSource customDynamicDataSource = cls.getAnnotation(CustomDynamicDataSource.class);
				this.setDateSource(customDynamicDataSource, joinPoint);
			}
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);
			// 判断是否存在@CustomDynamicDataSource注解
			if (method.isAnnotationPresent(CustomDynamicDataSource.class)) {
				CustomDynamicDataSource customDynamicDataSource = method.getAnnotation(CustomDynamicDataSource.class);
				this.setDateSource(customDynamicDataSource, joinPoint);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("切换数据源发送异常", e);
		}
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 设置数据源
	 * @Date 2019年5月23日下午4:37:56
	 * @param dataSource
	 * @param point
	 * @ReturnType void
	 */
	private void setDateSource(CustomDynamicDataSource dataSource, JoinPoint point) {
		String dataSourceType = dataSource.value();
		if (dataSourceType == null || "".equals(dataSourceType) || "null".equalsIgnoreCase(dataSourceType)) {
			setDataSoruceByMethodParam(point);
		} else {
			setDataSoruceByValue(dataSourceType);
		}
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 通过方法的第一个字符串参数，动态设置数据源（非公用，只为订单库使用）
	 * @Date 2020年2月27日下午12:20:35
	 * @param joinPoint
	 * @ReturnType void
	 */
	private void setDataSoruceByMethodParam(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if (args == null || args.length < 1) {
			throw new DynamicDataSourceException("动态切换数据源时, 方法没有参数, 无法切换");
		}
		if (!(args[0] instanceof Long)) {
			throw new DynamicDataSourceException("动态切换数据源时, 方法第一个参数不是用户主键ID或者不是Long类型, 无法动态切换");
		}
		if (args[0] == null) {
			throw new DynamicDataSourceException("动态切换数据源时，不允许用户主键为空");
		}
		String storeId = String.valueOf(args[0]);
		this.logger.debug("动态切换数据源：" + storeId);
		if ("".equals(storeId) || "".equals(storeId) || "null".equalsIgnoreCase(storeId)) {
			throw new DynamicDataSourceException("动态切换数据源时用户ID不存在");
		}
		if (new Integer(storeId) % 2 == 0) {
			DataSourceContextHolder.setDB(SEPERATOR1);
		} else if (new Integer(storeId) % 2 != 0) {
			DataSourceContextHolder.setDB(SEPERATOR2);
		} else {
			throw new DynamicDataSourceException("错误的数据源切换, 用户主键id = " + storeId);
		}
	}

	/**
	 * 
	 * @Author Chester_Zheng
	 * @Description 指定设置数据源
	 * @Date 2019年5月23日下午4:37:49
	 * @param dataSourceType
	 * @ReturnType void
	 */
	private void setDataSoruceByValue(String dataSourceType) {
		this.logger.debug("切换到指定数据源 = " + dataSourceType);
		switch (dataSourceType) {
		case MASTER:
			DataSourceContextHolder.setDB(MASTER);
			break;
		case SEPERATOR1:
			DataSourceContextHolder.setDB(SEPERATOR1);
			break;
		case SEPERATOR2:
			DataSourceContextHolder.setDB(SEPERATOR2);
			break;
		default:
			DataSourceContextHolder.setDB(MASTER);
			break;
		}
	}

	@After("declareJoinPointExpression()")
	public void afterSwitchDataSource(JoinPoint point) {
		DataSourceContextHolder.clearDB();
	}

}
