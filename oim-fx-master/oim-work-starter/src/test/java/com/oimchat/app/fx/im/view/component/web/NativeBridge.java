
package com.oimchat.app.fx.im.view.component.web;

/**
 * Description <br>
 * Date 2021-02-27 13:25:18<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class NativeBridge {

	public void out(Object o) {
		System.out.println(o);
	}

	public void download(Object fileInfo) {
		System.out.println(fileInfo);
	}

	public void showImage(Object imageInfo) {
		System.out.println(imageInfo);
	}

	public void resend(Object data) {
		System.out.println(data);
	}
}
