
package com.oimchat.app.fx.base.component.chat;

/**
 * Description <br>
 * Date 2021-03-02 09:08:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface MessageReadNativeBridge {

	public void download(String messageKey, String itemKey, String url, String name, long size);

	public void showImage(String messageKey, String itemKey, String url);

	public void openUrl(String url);

	public void resend(String key);

	public void saveScrollTop(int top);

	public void onScrollTop(int elementSize);
}
