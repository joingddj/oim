
package com.oimchat.app.fx.base.component.chat;

import com.onlyxiahui.app.view.fx.common.data.DataConverter;

/**
 * Description <br>
 * Date 2021-03-02 09:08:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface MessageWriteEditBridge {

	public void paste();

	public DataConverter<String, String> getInputTextConverter();

	public void setInputTextConverter(DataConverter<String, String> inputTextConverter);

	public void showLog(Object o);
}
