
package com.oimchat.app.fx.im.view.component.chat;

import com.oimchat.app.fx.base.component.chat.MessageReadNativeBridge;

/**
 * Description <br>
 * Date 2021-03-02 12:10:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageReadNativeBridgeImpl implements MessageReadNativeBridge {

	@Override
	public void download(String messageKey, String itemKey, String url, String name, long size) {
		System.out.println("download(" + messageKey + "," + itemKey + "," + url + ")");
	}

	@Override
	public void showImage(String messageKey, String itemKey, String url) {
		System.out.println("showImage(" + messageKey + "," + itemKey + "," + url + ")");
	}

	@Override
	public void resend(String key) {
		System.out.println("resend(" + key + ")");
	}

	@Override
	public void saveScrollTop(int top) {
		System.out.println("saveScrollTop(" + top + ")");
	}

	@Override
	public void onScrollTop(int s) {
		System.out.println("onScrollTop");
	}

	@Override
	public void openUrl(String url) {
		// TODO Auto-generated method stub
		
	}
}
