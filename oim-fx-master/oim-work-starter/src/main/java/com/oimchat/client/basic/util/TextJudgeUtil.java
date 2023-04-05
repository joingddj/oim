
package com.oimchat.client.basic.util;

/**
 * Description <br>
 * Date 2021-03-29 11:18:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TextJudgeUtil {

	public static boolean hasHtml(String text) {
		return MatcherUtil.match("<[^>]+>", text);
	}
}
