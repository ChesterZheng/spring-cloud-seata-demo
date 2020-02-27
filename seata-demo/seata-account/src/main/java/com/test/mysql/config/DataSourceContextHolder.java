package com.test.mysql.config;

public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	// 设置数据源名
	public static void setDB(String dbType) {
		contextHolder.set(dbType);
	}
	
	public static void setDB(Long uid) {
		if(uid % 2 ==0) {
			DataSourceContextHolder.setDB(CustomDynamicDataSourceAspectj.SEPERATOR1);
		}else {
			DataSourceContextHolder.setDB(CustomDynamicDataSourceAspectj.SEPERATOR2);
		}
	}

	// 获取数据源名
	public static String getDB() {
		return (contextHolder.get());
	}

	// 清除数据源名
	public static void clearDB() {
		contextHolder.remove();
	}

}
