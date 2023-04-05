package com.oimchat.client.platform.common.view;

import com.onlyxiahui.app.basic.view.View;

/**
 * 登录界面
 * 
 * @author: XiaHui
 * @date 2018-01-21 10:30:05
 */
public interface LoginView extends View {

	public boolean isSavePassword();

	public void showWaiting(boolean show);

}
