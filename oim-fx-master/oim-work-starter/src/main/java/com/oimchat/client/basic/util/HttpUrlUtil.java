package com.oimchat.client.basic.util;

/**
 * @author: XiaHui
 * @date: 2017年7月25日 上午9:15:49
 */
public class HttpUrlUtil {
	/**
	 * 正则表达式说明 (http://)?? 表示一次或者多次 (\\w)+ 表示单词字符：[a-zA-Z_0-9] 出现至少一次 (\\.\\w+)+ 表示
	 * .单词字符出现至少一次 ([/\\w\\.\\?=%&-:@#$]*+)*+ 表示 /单词字符.?=%&-:@#$出现0到多次的形式的字符串出现0到多次
	 */
	// (?is)(?<!')(http://[/\\.\\w]+)
	// static String http = "(http://)??(\\w)+(\\.\\w+)+([/\\w\\.\\?=%&-:@#$]*+)*+";
	static String http = "(http[s]?://)??(\\w)+(\\.\\w+)+([/\\w\\.\\?=%&-:@#$]*+)*+";
	static String https = "(https://)??(\\w)+(\\.\\w+)+([/\\w\\.\\?=%&-:@#$]*+)*+";

	/**
	 * 将字符串中符合url格式的字符串选出，全部添加超链接的a标签
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceUrl(String text, String value) {
		String temp = null;
		if (null != text) {
			// text = text.replace("http://", "");
			text = text.replace("&amp;", "&");
			temp = text.replaceAll("(" + http + ")", value);
		}
		return temp;
	}

	public static String replaceUrlToLink(String text) {
		// return replaceUrl(text, "<a href=\"http://$1\">http://$1</a>");
		return replaceUrl(text, "<a href=\"$1\">$1</a>");
	}

	public static String replaceUrlToLink(String text, String attribute) {
		return replaceUrl(text, "<a " + attribute + " href=\"$1\">$1</a>");
	}

	public static String replaceUrlToDisableLink(String text, String attribute) {
		return replaceUrl(text, "<a " + attribute + " href=\"javascript:void(0);\">$1</a>");
	}
}
