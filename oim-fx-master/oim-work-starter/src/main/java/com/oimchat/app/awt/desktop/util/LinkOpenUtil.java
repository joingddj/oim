
package com.oimchat.app.awt.desktop.util;

import java.awt.Desktop;
import java.lang.reflect.Method;

/**
 * Description <br>
 * Date 2021-03-04 20:53:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LinkOpenUtil {

	public static boolean open(String url) {
		boolean mark = true;
		try {
			java.net.URI uri = new java.net.URI(url);
			java.awt.Desktop d = java.awt.Desktop.getDesktop();
			if (Desktop.isDesktopSupported() && d.isSupported(java.awt.Desktop.Action.BROWSE)) {
				java.awt.Desktop.getDesktop().browse(uri);
			} else {
				browse(url);
			}
		} catch (Exception e) {
			mark = false;
		}
		return mark;
	}

	private static void browse(String url) throws Exception {
		// 获取操作系统的名字
		String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Mac OS")) {// 苹果的打开方式
			Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
			Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
			openURL.invoke(null, new Object[] { url });
		} else if (osName.startsWith("Windows")) {// windows的打开方式。
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} else {// Unix or Linux的打开方式
			String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
			String browser = null;
			for (int count = 0; count < browsers.length && browser == null; count++)
				// 执行代码，在brower有值后跳出，
				// 这里是如果进程创建成功了，==0是表示正常结束。
				if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
					browser = browsers[count];
				}
			if (browser == null) {
				throw new Exception("Could not find web browser");
			} else {// 这个值在上面已经成功的得到了一个进程。
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		}
	}
}
