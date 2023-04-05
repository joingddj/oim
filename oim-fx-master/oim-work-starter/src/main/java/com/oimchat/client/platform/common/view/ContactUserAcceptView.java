package com.oimchat.client.platform.common.view;

import com.onlyxiahui.app.basic.view.View;
import com.onlyxiahui.app.view.fx.common.event.ExecuteEvent;

/**
 * 描述： 添加好友或者加入群显示窗口
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public interface ContactUserAcceptView extends View {

	public void setData(String userId, String applyId, ExecuteEvent back);
}
