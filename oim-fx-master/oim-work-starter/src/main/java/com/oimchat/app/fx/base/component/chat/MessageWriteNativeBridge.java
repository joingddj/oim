
package com.oimchat.app.fx.base.component.chat;

/**
 * Description <br>
 * Date 2021-03-02 09:08:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface MessageWriteNativeBridge {

	public void onInput(String text, double x, double y, int rangeStartOffset, int rangeEndOffset);
}
