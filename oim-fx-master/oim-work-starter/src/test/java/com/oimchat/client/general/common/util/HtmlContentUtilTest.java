
package com.oimchat.client.general.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Description <br>
 * Date 2021-03-24 19:23:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HtmlContentUtilTest {

	public static void main(String[] args) {
		System.out.println(HtmlContentUtil.getTag("div", null));

		Map<String, String> map = new HashMap<>();
		map.put("name", "face");
		map.put("value", "{\"name\":\"123\"}");
		System.out.println(HtmlContentUtil.getTag("img", map));
	}
}
