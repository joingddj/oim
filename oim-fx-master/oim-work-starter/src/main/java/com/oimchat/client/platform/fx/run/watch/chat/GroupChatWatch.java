
package com.oimchat.client.platform.fx.run.watch.chat;

import java.net.URL;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.basic.common.data.im.message.content.item.AtValue;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.general.kernel.work.module.im.data.vo.GroupMessageData;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.general.kernel.work.module.im.inform.GroupUnreadBox;
import com.oimchat.client.general.kernel.work.module.im.observer.GroupCahtListener;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.common.inform.type.SoundType;
import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.module.chat.interaction.GroupChatInteraction;
import com.oimchat.client.platform.fx.module.chat.store.GroupChatPaneStore;
import com.oimchat.client.platform.fx.run.watch.chat.data.AtPromptData;
import com.oimchat.client.platform.fx.run.watch.chat.data.ChatPromptData;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.oimchat.client.platform.fx.work.common.service.GroupHeadImageService;
import com.oimchat.client.platform.kernel.work.module.inform.hanlder.SoundHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-23 10:09:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatWatch extends AbstractWatch {

	public GroupChatWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		GroupCahtListener uc = this.appContext.getObject(GroupCahtListener.class);
		uc.addMessageValueAcation((data) -> {
			chat(data);
		});
	}

	private void chat(GroupMessageData data) {

		String groupId = data.getGroupId();

		UserSimple chatUser = data.getUser();
		MessageContentWrap contentWrap = data.getContentWrap();
		boolean isOwn = contentWrap.isOwn();
		String chatName = contentWrap.getName();
		String messageKey = contentWrap.getKey();
		Long timestamp = contentWrap.getTimestamp();

		GroupChatPaneStore store = this.appContext.getObject(GroupChatPaneStore.class);
		String key = store.getKey(groupId);
		ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);
		String timeItem = timeTextHandler.timeItem(timestamp);

		UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
		String text = CoreContentUtil.getSimpleText(contentWrap.getContent());

		boolean isChatShowing = store.isChatShowing(groupId);

		if (StringUtil.isBlank(chatUser.getAvatar())) {
			URL url = hi.getHeadUrl(chatUser.getHead());
			if (null != url) {
				String avatar = url.toExternalForm();
				contentWrap.setAvatar(avatar);
				contentWrap.getUser().setAvatar(avatar);
			}
		}

		String json = JsonUtil.toJson(contentWrap);
		store.insertLast(groupId, json);
		store.updateItemInfo(groupId, text, timeItem);

		if (!isOwn) {
			PersonalBox pb = this.appContext.getObject(PersonalBox.class);
			String ownerUserId = pb.getOwnerUserId();
			AtValue av = CoreContentUtil.getAtValue(contentWrap.getContent(), ownerUserId);
			if (null != av) {
				AtPromptData ad = new AtPromptData();
				ad.setChatText(text);
				ad.setChatUserName(chatName);
				ad.setMessageKey(messageKey);

				store.insertAt(groupId, JsonUtil.toJson(ad));
			}
		}

		if (!isOwn) {
			ChatPromptData pd = new ChatPromptData();
			pd.setChatText(text);
			pd.setChatUserName(chatName);
			pd.setMessageKey(messageKey);
			store.insertChatPrompt(groupId, JsonUtil.toJson(pd));
		}

		if (!isChatShowing && !isOwn) {
			GroupUnreadBox unreadBox = this.appContext.getObject(GroupUnreadBox.class);
			unreadBox.plusUnread(groupId);

			SoundHandler soundHandler = this.appContext.getObject(SoundHandler.class);
			soundHandler.put(SoundType.sound_type_message);

			GroupHeadImageService ghs = this.appContext.getObject(GroupHeadImageService.class);

			PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
			ghs.getLocalOrLoadAvatarUrl(groupId, (url) -> {
				pdbox.put(key, url, () -> {
					GroupChatInteraction uco = this.appContext.getObject(GroupChatInteraction.class);
					uco.showChat(groupId);
				});
			});
		}
	}
}
