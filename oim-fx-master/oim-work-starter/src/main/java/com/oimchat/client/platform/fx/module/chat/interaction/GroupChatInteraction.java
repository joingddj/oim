
package com.oimchat.client.platform.fx.module.chat.interaction;

import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.platform.fx.module.chat.store.GroupChatPaneStore;
import com.oimchat.client.platform.fx.module.chat.wrap.ChatListStageWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-27 23:05:04<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatInteraction extends AbstractMaterial {

	public GroupChatInteraction(AppContext appContext) {
		super(appContext);
	}

	public void showChat(String groupId) {
		GroupHandler uh = this.appContext.getObject(GroupHandler.class);
		uh.getById(groupId, (info, group) -> {
			if (info.isSuccess()) {
				showChat(group);
			}
		});
	}

	public void showChat(Group group) {
		if (null != group) {
			ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
			stageWrap.setVisible(true);
			GroupChatPaneStore store = this.appContext.getObject(GroupChatPaneStore.class);
			String key = store.getKey(group);
			if (!stageWrap.hasKey(key)) {
				store.showChat(group);
			} else {
				stageWrap.selected(key);
			}
		}
	}
}
