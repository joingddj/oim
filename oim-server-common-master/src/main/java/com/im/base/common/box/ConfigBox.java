package com.im.base.common.box;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author: XiaHui
 * @date: 2017年8月11日 下午5:19:19
 */
public class ConfigBox {

	private static Map<String, String> configMap = new ConcurrentSkipListMap<String, String>();

	public static String get(String key) {
		return configMap.get(key);
	}

	public static String put(String key, String value) {
		return configMap.put(key, value);
	}
}
