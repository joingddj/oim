
package com.oimchat.client.general.kernel.work.module.im.service;

import com.oimchat.client.general.kernel.work.module.im.inform.GroupUnreadBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-31 16:02:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatPromptService extends AbstractMaterial {

	public GroupChatPromptService(AppContext appContext) {
		super(appContext);
	}

	public void removePrompt(String groupId) {
		GroupUnreadBox unreadBox = this.appContext.getObject(GroupUnreadBox.class);
		long count = unreadBox.getUnreadCount(groupId);
		if (count > 0) {
			unreadBox.setUnreadCount(groupId, 0);
		}
	}
}
