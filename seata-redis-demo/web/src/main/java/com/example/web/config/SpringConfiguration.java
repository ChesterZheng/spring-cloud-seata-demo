package com.example.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class SpringConfiguration extends WebMvcConfigurationSupport {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(this.fastJsonHttpMessageConverters());
	}

	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
		// 1. 需要定义一个converter转换消息的对象
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		// 2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, // 结果是否格式化,默认为false
				SerializerFeature.DisableCircularReferenceDetect, // 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
				SerializerFeature.WriteDateUseDateFormat, // JSON.DEFFAULT_DATE_FORMAT =
															// “yyyy-MM-dd”;JSON.toJSONString(obj,
															// SerializerFeature.WriteDateUseDateFormat);
				SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
//				SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
				SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
//				SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
				SerializerFeature.WriteMapNullValue // MAP为null输出空
		);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		// 3. 在converter中添加配置信息
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		return fastJsonHttpMessageConverter;
	}

}
