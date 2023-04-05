
package com.oimchat.app.swing.util;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Description <br>
 * Date 2021-03-12 17:41:34<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageIconLoadUtil {

	public static ImageIcon getByClassPath(String calssPath) {
		ImageIcon icon = new ImageIcon(ImageIconLoadUtil.class.getResource(calssPath));
		return icon;
	}
	
	public static ImageIcon getByUrl(URL url) {
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
}
