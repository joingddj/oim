
package com.oimchat.client.basic.util;

/**
 * Description <br>
 * Date 2021-03-04 20:55:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HttpUrlUtilTest {

	public static void main(String[] arg) {
		String text = "你打开这个地址http://www.onlyoim.com/index.html?userId=10001&name=账号t   图片https://www.onlyoim.com/1.jpg";
		// System.out.println(replaceUrlToLink(text));
		// text = "http://www.onlyoim.com/index.html?userId=10001&name=账号";
		// System.out.println(replaceUrlToLink(text));
		String b = "打开www.baidu.com和http://www.c.c?ss=da&nd=拿到  然后http://www.c.c?ss=da&nd= https://www.c.c:800/d.do?ss=da&nd=";

		// text = text.replace("http://", "");

		System.out.println(HttpUrlUtil.replaceUrlToLink(text));

		String http = "(http[s]?://)??(\\w)+(\\.\\w+)+([/\\w\\.\\?=%&-:@#$]*+)*+";
		text = b.replaceAll("(" + http + ")", "<a href=\"$1\">$1</a>");
		System.out.println(text);
		// System.out.println(open("www.baidu.com?u=1"));
	}

}
