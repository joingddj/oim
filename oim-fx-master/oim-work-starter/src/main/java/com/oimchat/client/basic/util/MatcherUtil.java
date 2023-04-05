
package com.oimchat.client.basic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description 
 * <br>
 * Date 2021-03-29 11:19:50<br>
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MatcherUtil {
	
	public static boolean match(String regex, String text) {
		text = (null == text) ? "" : text;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
}
