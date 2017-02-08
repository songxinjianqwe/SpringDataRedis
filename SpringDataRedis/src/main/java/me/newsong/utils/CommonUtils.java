package me.newsong.utils;

import java.util.UUID;

public class CommonUtils {
	/**
	 * 生成一个16位的随机字符串，不重复
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
