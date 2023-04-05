
package com.oimchat.client.general.kernel.work.module.im.service;

import com.oimchat.client.general.kernel.work.module.im.inform.UserUnreadBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-31 16:02:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatPromptService extends AbstractMaterial {

	public UserChatPromptService(AppContext appContext) {
		super(appContext);
	}

	public void removePrompt(String userId) {
		UserUnreadBox unreadBox = this.appContext.getObject(UserUnreadBox.class);
		long count = unreadBox.getUnreadCount(userId);
		if (count > 0) {
			unreadBox.setUnreadCount(userId, 0);
		}
	}
}
