package com.example.mysql.config;

public class DynamicDataSourceException extends RuntimeException {

	/**
	 * @Author Chester_Zheng
	 * @Date 2019年5月23日下午4:39:33
	 */
	private static final long serialVersionUID = 1L;

	public DynamicDataSourceException(String s) {
		super(s);
	}

	public DynamicDataSourceException(String s, Throwable e) {
		super(s, e);
	}

	public DynamicDataSourceException(Throwable e) {
		super(e);
	}
}