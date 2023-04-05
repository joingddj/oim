
package com.oimchat.app.fx.net;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * Description <br>
 * Date 2021-04-16 10:26:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageURLStreamHandlerFactory implements URLStreamHandlerFactory {

	public URLStreamHandler createURLStreamHandler(String protocol) {
		if (protocol.equals("location")) {
			return new ImageURLHandler();
		}
		return null;
	}

}
