package com.oimchat.client.general.common.util;

import java.util.HashMap;
import java.util.Map;

import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * @author XiaHui
 * @date 2017-11-10 10:53:22
 */
public class HtmlContentUtil {

	public static String getTag(String tag, Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(tag);

		if (null != map) {
			for (Map.Entry<String, String> e : map.entrySet()) {
				String key = e.getKey();
				String value = e.getValue();
				if (StringUtil.isNotBlank(key)) {
					value = (null == value) ? "" : value;
					sb.append(" ");
					sb.append(key);
					sb.append("=\"");
					sb.append(value);
					sb.append("\"");
				}
			}
		}
		sb.append(" />");
		return sb.toString();
	}

	public static String getImageTag(String id, String name, String value, String source, String alt, Map<String, String> map) {

		String tag = "img";
		if (null == map) {
			map = new HashMap<>();
		}

		if (null != id && !"".equals(id)) {
			map.put("id", id);
		}
		if (null != name && !"".equals(name)) {
			map.put("name", name);
		}
		if (null != value && !"".equals(value)) {
			map.put("value", value);
		}
		if (null != source && !"".equals(source)) {
			map.put("src", source);
		}

		if (null != alt && !"".equals(alt)) {
			map.put("alt", alt);
		}
		return getTag(tag, map);
	}

	public static String getImageTag(String id, String name, String value, String source, String alt) {
		String tag = "img";
		Map<String, String> map = new HashMap<>();

		if (null != id && !"".equals(id)) {
			map.put("id", id);
		}
		if (null != name && !"".equals(name)) {
			map.put("name", name);
		}
		if (null != value && !"".equals(value)) {
			map.put("value", value);
		}
		if (null != source && !"".equals(source)) {
			map.put("src", source);
		}
		if (null != alt && !"".equals(alt)) {
			map.put("alt", alt);
		}
		return getTag(tag, map);
	}
}
