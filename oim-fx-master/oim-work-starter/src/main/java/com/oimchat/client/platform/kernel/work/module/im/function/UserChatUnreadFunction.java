
package com.oimchat.client.platform.kernel.work.module.im.function;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.im.entity.UserChatUnread;
import com.oimchat.client.general.kernel.work.module.im.inform.UserUnreadBox;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.common.inform.type.SoundType;
import com.oimchat.client.platform.fx.module.chat.interaction.UserChatInteraction;
import com.oimchat.client.platform.fx.module.chat.store.UserChatPaneStore;
import com.oimchat.client.platform.kernel.work.module.inform.hanlder.SoundHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 19:41:09<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatUnreadFunction extends AbstractMaterial {

	public UserChatUnreadFunction(AppContext appContext) {
		super(appContext);
	}

	public void setList(List<UserChatUnread> list) {
		if (null != list) {
			for (UserChatUnread data : list) {
				// String receiveUserId = data.getReceiveUserId();
				String sendUserId = data.getSendUserId();
				// String lastContentId = data.getLastContentId();
				int unreadCount = data.getUnreadCount();

				this.setUnread(sendUserId, unreadCount);
			}
		}
	}

	public void setUnread(String userId, int count) {

		UserChatPaneStore store = this.appContext.getObject(UserChatPaneStore.class);
		String key = store.getKey(userId);

		boolean isChatShowing = store.isChatShowing(userId);

		if (!isChatShowing && count > 0) {
			UserUnreadBox unreadBox = this.appContext.getObject(UserUnreadBox.class);
			unreadBox.plusUnreadCount(userId, count);

			SoundHandler soundHandler = this.appContext.getObject(SoundHandler.class);
			soundHandler.put(SoundType.sound_type_message);

			PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
			pdbox.put(key, null, () -> {
				UserChatInteraction uco = this.appContext.getObject(UserChatInteraction.class);
				uco.showChat(userId);
			});
		}
	}
}
