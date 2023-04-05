
package com.oimchat.client.basic.util;

/**
 * Description <br>
 * Date 2021-03-05 14:36:38<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class StringHtmlUtil {

	public static String htmlEscape(String text) {
		String value = null;
		if (null != text && !"".equals(text)) {
			value = text.replace("&", "&amp;");
			// 替换跳格
			value = value.replace("\t", "&nbsp;&nbsp;");
			value = value.replace("<", "&lt;");
			value = value.replace(">", "&gt;");
			value = value.replace(" ", "&nbsp;");
			value = value.replace("\'", "&#39;");
			value = value.replace("\"", "&quot;");
			// value = value.replace("\n", "<br>");
		} else {
			value = text;
		}
		return value;
	}
}
