
package com.oimchat.client.basic.util;

/**
 * Description <br>
 * Date 2021-03-12 10:51:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PathUtilTest {

	public static void main(String[] args) {
		System.out.println(PathUtil.merge(null, null));
		System.out.println(PathUtil.merge(null, "get.user.list"));
		System.out.println(PathUtil.merge("http://www.oim.com", null));
		System.out.println(PathUtil.merge("http://www.oim.com", "get.user.list"));
		System.out.println(PathUtil.merge("http://www.oim.com/", "get.user.list"));
		System.out.println(PathUtil.merge("http://www.oim.com", "/get.user.list"));
		System.out.println(PathUtil.merge("http://www.oim.com/", "/get.user.list"));
	}
}
