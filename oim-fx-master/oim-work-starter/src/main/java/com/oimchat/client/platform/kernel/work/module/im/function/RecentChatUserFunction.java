
package com.oimchat.client.platform.kernel.work.module.im.function;

import java.util.List;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.general.kernel.work.module.im.data.dto.UserChatData;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.fx.module.list.store.UserMessageListItemStore;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 17:25:18<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RecentChatUserFunction extends AbstractMaterial {

	public RecentChatUserFunction(AppContext appContext) {
		super(appContext);
	}

	public void setRecentChatList(List<UserChatData> list) {
		if (null != list) {
			PersonalBox pb = this.appContext.getObject(PersonalBox.class);
			String ownUserId = pb.getOwnerUserId();
			// boolean isReceive = true;

			UserMessageListItemStore mls = this.appContext.getObject(UserMessageListItemStore.class);

			// const userChatItemManager: UserChatItemManager =
			// this.appContext.getMaterial(UserChatItemManager);

			UserManager userManager = this.appContext.getObject(UserManager.class);

			for (UserChatData data : list) {
				// const messageKey: string = data.messageKey;
				// const contentId: string = data.contentId;
				Content content = data.getContent();
				UserSimple receiveUser = data.getReceiveUser();
				UserSimple sendUser = data.getSendUser();

				String sendUserId = sendUser.getId();
				// String receiveUserId = receiveUser.getId();

				boolean isOwn = sendUserId.equals(ownUserId);

				UserSimple showUser = (isOwn) ? receiveUser : sendUser;
				// UserSimple chatUser = (isOwn) ? sendUser : showUser;

				String key = showUser.getId();

				long timestamp = content.getTimestamp();

				ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);
				String timeItem = timeTextHandler.timeItem(timestamp);

				String text = CoreContentUtil.getSimpleText(content);
				userManager.loadById(key, (info, u) -> {
					if (info.isSuccess() && null != u) {
						mls.addOrUpdateItem(u);
						mls.updateItemTextInfo(key, text, timeItem);
					}
				});
			}
		}
	}
}
