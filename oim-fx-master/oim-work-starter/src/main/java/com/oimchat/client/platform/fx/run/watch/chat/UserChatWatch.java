
package com.oimchat.client.platform.fx.run.watch.chat;

import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.net.ImageURLPathUtil;
import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.general.common.util.WebImagePathUtil;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.event.UserStatusObservable;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageData;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageVO;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.general.kernel.work.module.im.inform.UserUnreadBox;
import com.oimchat.client.general.kernel.work.module.im.observer.UserCahtListener;
import com.oimchat.client.general.kernel.work.module.im.type.MotionTypeEnum;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.common.inform.type.SoundType;
import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.module.chat.interaction.UserChatInteraction;
import com.oimchat.client.platform.fx.module.chat.store.UserChatPaneStore;
import com.oimchat.client.platform.fx.module.chat.wrap.ChatListStageWrap;
import com.oimchat.client.platform.fx.module.list.store.UserMessageListItemStore;
import com.oimchat.client.platform.fx.run.watch.chat.data.ChatPromptData;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.oimchat.client.platform.kernel.work.module.inform.hanlder.SoundHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.lib.util.json.JsonUtil;

/**
 * Description <br>
 * Date 2021-03-23 10:09:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatWatch extends AbstractWatch {

	public UserChatWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		UserCahtListener uc = this.appContext.getObject(UserCahtListener.class);
		uc.addMessageValueAcation((data) -> {
			chat(data);
		});

		uc.addMotionValueAcation((data) -> {
			motion(data);
		});

		UserStatusObservable ul = this.appContext.getObject(UserStatusObservable.class);
		ul.addListener((id, status) -> {
			updateStatus(id, status);
		});
	}

	private void updateStatus(String id, String status) {
		UserChatPaneStore store = this.appContext.getObject(UserChatPaneStore.class);
		String key = store.getKey(id);
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		HeadCloseItem item = stageWrap.getItem(key);
		if (null != item) {
			boolean gray = UserInfoUtil.isOffline(status);
			FxUtil.invoke(() -> {
				item.setGray(gray);
			});
		}
	}

	private void chat(UserMessageData data) {

		User showUser = data.getShowUser();
		User chatUser = data.getChatUser();
		MessageContentWrap contentWrap = data.getContentWrap();
		boolean isOwn = contentWrap.isOwn();
		String chatName = contentWrap.getName();
		String messageKey = contentWrap.getKey();
		Long timestamp = contentWrap.getTimestamp();

		String id = showUser.getId();

		UserChatPaneStore store = this.appContext.getObject(UserChatPaneStore.class);
		String key = store.getKey(chatUser);
		ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);
		String timeItem = timeTextHandler.timeItem(timestamp);

		UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
		String text = CoreContentUtil.getSimpleText(contentWrap.getContent());

		boolean isChatShowing = store.isChatShowing(showUser.getId());

		hi.loadAvatarUrl(chatUser.getHead(), chatUser.getAvatar(), (url) -> {

			if (null != url) {
				String avatar = url.toExternalForm();
				System.out.println(this.getClass());
				System.out.println(avatar);

				//avatar = ImageURLPathUtil.pathToFileImageSource(avatar);
				System.out.println(avatar);
				contentWrap.setAvatar(avatar);
				contentWrap.getUser().setAvatar(avatar);
			}

			String json = JsonUtil.toJson(contentWrap);
			store.insertLast(id, json);
			store.updateItemInfo(id, text, timeItem);
			if (!isOwn) {
				ChatPromptData pd = new ChatPromptData();
				pd.setChatText(text);
				pd.setChatUserName(chatName);
				pd.setMessageKey(messageKey);
				store.insertChatPrompt(id, JsonUtil.toJson(pd));
			}
			if (!isChatShowing && !isOwn) {

				PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
				pdbox.put(key, url, () -> {
					UserChatInteraction uco = this.appContext.getObject(UserChatInteraction.class);
					uco.showChat(showUser);
				});
			}
		});

		if (!isChatShowing && !isOwn) {
			UserUnreadBox unreadBox = this.appContext.getObject(UserUnreadBox.class);
			unreadBox.plusUnread(id);

			SoundHandler soundHandler = this.appContext.getObject(SoundHandler.class);
			soundHandler.put(SoundType.sound_type_message);
		}

		UserMessageListItemStore mls = this.appContext.getObject(UserMessageListItemStore.class);
		mls.addOrUpdateItem(showUser);
		mls.updateItemTextInfo(id, text, timeItem);
	}

	private void motion(UserMessageVO<Motion> data) {
		User showUser = data.getShowUser();
		// User chatUser = data.getChatUser();
		Motion m = data.getData();
		UserChatInteraction uci = this.appContext.getObject(UserChatInteraction.class);
		if (MotionTypeEnum.shake.getCode().equals(m.getType())) {
			uci.showChat(showUser);
			ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
			stageWrap.shake();

			SoundHandler soundHandler = this.appContext.getObject(SoundHandler.class);
			soundHandler.put(SoundType.sound_type_shake);
		}
	}
}
