
package com.oimchat.client.platform.fx.module.chat.interaction;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.platform.fx.module.chat.store.UserChatPaneStore;
import com.oimchat.client.platform.fx.module.chat.wrap.ChatListStageWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-22 15:24:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatInteraction extends AbstractMaterial {

	public UserChatInteraction(AppContext appContext) {
		super(appContext);
	}

	public void showChat(String userId) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, user) -> {
			if (info.isSuccess()) {
				showChat(user);
			}
		});
	}

	public void showChat(User user) {
		if (null != user) {
			ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
			stageWrap.setVisible(true);
			UserChatPaneStore store = this.appContext.getObject(UserChatPaneStore.class);
			String key = store.getKey(user);
			if (!stageWrap.hasKey(key)) {
				store.showChat(user);
			} else {
				stageWrap.selected(key);
			}
		}
	}
}
