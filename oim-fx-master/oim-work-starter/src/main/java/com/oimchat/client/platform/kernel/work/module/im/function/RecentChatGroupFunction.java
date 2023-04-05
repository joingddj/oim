
package com.oimchat.client.platform.kernel.work.module.im.function;

import java.util.List;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupManager;
import com.oimchat.client.general.kernel.work.module.im.data.dto.GroupChatData;
import com.oimchat.client.general.kernel.work.module.im.data.dto.GroupSimple;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.platform.fx.module.list.store.GroupMessageListItemStore;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 18:02:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RecentChatGroupFunction extends AbstractMaterial {

	public RecentChatGroupFunction(AppContext appContext) {
		super(appContext);
	}

	public void setRecentChatList(List<GroupChatData> list) {
		if (null != list) {
			// PersonalBox pb = this.appContext.getObject(PersonalBox.class);
			// String ownUserId = pb.getOwnerUserId();
			// boolean isReceive = true;

			GroupMessageListItemStore mls = this.appContext.getObject(GroupMessageListItemStore.class);

			// const userChatItemManager: UserChatItemManager =
			// this.appContext.getMaterial(UserChatItemManager);

			GroupManager groupManager = this.appContext.getObject(GroupManager.class);

			for (GroupChatData data : list) {

				// const messageKey: string = data.messageKey;
				// const contentId: string = data.contentId;

				Content content = data.getContent();
				GroupSimple group = data.getGroup();
				// UserSimple user = data.getUser();

				String groupId = (null != group) ? group.getId() : "";
				String key = groupId;

				long timestamp = content.getTimestamp();

				ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);
				String timeItem = timeTextHandler.timeItem(timestamp);

				String text = CoreContentUtil.getSimpleText(content);

				groupManager.getOrLoadById(groupId, (info, g) -> {
					if (info.isSuccess() && null != g) {
						mls.addOrUpdateItem(g);
						mls.updateItemTextInfo(key, text, timeItem);
					}
				});
			}
		}
	}
}
