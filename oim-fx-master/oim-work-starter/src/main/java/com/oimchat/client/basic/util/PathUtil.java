
package com.oimchat.client.basic.util;

/**
 * Description <br>
 * Date 2021-03-12 10:47:50<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PathUtil {

	public static String merge(String path1, String path2) {
		String path = null;
		if (null != path1 && null != path2) {
			String merge = "/";
			boolean end1 = path1.endsWith(merge);
			boolean start2 = path2.startsWith(merge);

			if (end1 && start2) {
				path = path1 + path2.substring(1);
			} else if (end1 || start2) {
				path = path1 + path2;
			} else {
				path = path1 + merge + path2;
			}
		} else if (null != path1 || null != path2) {
			path = (null == path1 ? "" : path1) + (null == path2 ? "" : path2);
		}
		return path;
	}
}
