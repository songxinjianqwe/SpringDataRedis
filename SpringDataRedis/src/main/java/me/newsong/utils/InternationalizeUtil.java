package me.newsong.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
/**
 * 用于国际化
 * 注意这是一个静态导入的工具类
 * @author Sinjin Song
 *
 */
@Component
public class InternationalizeUtil {
	@Autowired
	private ResourceBundleMessageSource ms;
	private static InternationalizeUtil util;
	
	@PostConstruct
	public void init() {
		util = this;
		util.ms = this.ms;
	}
	
	public static String getMessage(String message,Locale locale){
		return util.ms.getMessage(message, null, locale);
	}
}
