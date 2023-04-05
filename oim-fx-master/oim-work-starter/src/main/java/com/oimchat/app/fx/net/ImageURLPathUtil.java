package com.oimchat.app.fx.net;

import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * @author: XiaHui
 * @date: 2018-01-27 16:38:48
 */
public class ImageURLPathUtil {
	/**
	 * 将图片在html的img标签中的本地图片src转成文件路径
	 * 
	 * @author XiaHui
	 * @date 2017-11-10 10:44:24
	 * @param path
	 * @return
	 */
	public static String fileImageSourceToPath(String path) {
		String url = null;
		if (null != path) {
			if (path.startsWith("file:")) {
				int l = path.length();
				url = (l >= 5) ? path.substring(5, l) : path;
				url = prefixRemove(url);
			} else {
				url = path;
			}
		}
		return url;
	}

	/**
	 * 本地图片文件路径转html<img> 标签src
	 * 
	 * @author: XiaHui
	 * @param path
	 * @return
	 * @createDate: 2017-12-25 15:48:00
	 * @update: XiaHui
	 * @updateDate: 2017-12-25 15:48:00
	 */
	public static String pathToFileImageSource(String path) {
		String temp = null;
		if (StringUtil.isNotBlank(path)) {
			if (path.startsWith("http")) {
				temp = path;
			} else if (path.startsWith("file:")) {
				temp = "location://" + fileImageSourceToPath(path);
			} else if (path.startsWith("jar:file:")) {
				temp = path;
			} else {
				temp = "location://" + fileImageSourceToPath(path);
			}
		}
		return temp;
	}

	public static String prefixRemove(String path) {
		String url = null;
		if (null != path) {
			url = path.replace("\\", "/");
			char[] chars = url.toCharArray();
			int index = -1;
			for (int i = 0; i < chars.length; i++) {
				if ('/' != chars[i]) {
					break;
				}
				index++;
			}
			int length = url.length();
			if (index > -1 && index < length) {
				url = url.substring(index + 1, length);
			}
		}
		return url;
	}

	public static void main(String[] arg) {
		String u = "file:///D:/img/1.jpg";
		System.out.println(fileImageSourceToPath(u));
		System.out.println(pathToFileImageSource(u));

		System.out.println("----");
		u = "file:\\C:\\Users/XiaHui/.oim/data/10000/images/head/user/caa18c6e0e9d29d168fe40f028c9f74f";
		System.out.println(fileImageSourceToPath(u));
		System.out.println(pathToFileImageSource(u));

		System.out.println("----");
		u = "jar:file:/E:/Workspaces/starter.jar!/img/1.jpg";
		System.out.println(fileImageSourceToPath(u));
		System.out.println(pathToFileImageSource(u));

		System.out.println("----");
	}
}
