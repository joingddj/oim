
package com.oimchat.client.platform.fx.module.chat.store.impl;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.general.kernel.work.module.im.data.dto.UserChatData;
import com.oimchat.client.general.kernel.work.module.im.handler.MessageContentWrapHandler;
import com.oimchat.client.general.kernel.work.module.im.manager.UserChatDataManager;
import com.oimchat.client.general.kernel.work.module.im.util.ContentTimeUtil;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.view.operator.UserChatOperator;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-02 12:10:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserMessageReadNativeBridgeImpl extends BaseMessageReadNativeBridgeImpl {

	private String userId;
	private User user;
	private ContactRelation relation;
	private String userAvatar;
	private String ownerAvatar;
	private String lastMessageKey;
	private boolean loadHistory = false;

	public UserMessageReadNativeBridgeImpl(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void resend(String key) {
//		System.out.println("resend(" + key + ")");
		UserChatOperator co = this.appContext.getObject(UserChatOperator.class);
		co.resend(userId, key);
	}

	@Override
	public void onScrollTop(int elementSize) {
		if (!loadHistory) {
			if (elementSize > 300) {
				readMapper.setTextPrompt("更多信息请查看历史记录");
			} else {
				loadHistory = true;
				lastMessageKey = readMapper.getFirstMessageKey();
				loadHistory(userId, lastMessageKey);
			}
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			laodData();
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContactRelation getRelation() {
		return relation;
	}

	public void setRelation(ContactRelation relation) {
		this.relation = relation;
	}

	public void loadHistory(String userId, String messageKey) {

		String direction = "before";
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		String sendUserId = (userId == null || "".equals(userId)) ? "00000" : userId;
		String receiveUserId = pb.getOwnerUserId();
		UserChatDataManager cs = appContext.getObject(UserChatDataManager.class);

		cs.getListFromStartMessageKey(sendUserId, receiveUserId, messageKey, direction, 30, (info, lb) -> {
			List<UserChatData> list = (null != lb && null != lb.getItems()) ? lb.getItems() : new ArrayList<>();
			setList(userId, list);
			if (StringUtil.isNotBlank(lastMessageKey)) {
				FxUtil.invoke(() -> {
					System.out.println("-----------------xxxxxxxxxxxxxxx:" + lastMessageKey);
					readMapper.updateScrollIntoView(lastMessageKey);
				});
			}
			loadHistory = false;
		});
	}

	private void setList(String userId, List<UserChatData> list) {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User ownerUser = pb.getUser();
		String ownerUserId = pb.getOwnerUserId();
		MessageContentWrapHandler wh = this.appContext.getObject(MessageContentWrapHandler.class);
		list.sort((a, b) -> {
			Content contentA = a.getContent();
			Content contentB = b.getContent();
			long timeA = (null == contentA) ? 0L : contentA.getTimestamp();
			long timeB = (null == contentB) ? 0L : contentB.getTimestamp();
			long t = timeB - timeA;
			if (t > 0) {
				return 1;
			} else if (t < 0) {
				return -1;
			} else {
				return 0;
			}
		});
		for (UserChatData cd : list) {

			int status = 1;

			UserSimple sendSimpleUser = cd.getSendUser();
			UserSimple receiveSimpleUser = cd.getReceiveUser();

			String sendUserId = sendSimpleUser.getId();
			// String receiveUserId = sendSimpleUser.getId();

			Content content = cd.getContent();

			boolean isOwn = ownerUserId.equals(sendUserId);

			UserSimple chatSimpleUser = isOwn ? sendSimpleUser : receiveSimpleUser;
			User chatUser = isOwn ? ownerUser : user;

			boolean timeVisible = true;
			String timeText = ContentTimeUtil.getChatShowTime(content.getTimestamp());

			String remark = (null == relation) ? null : relation.getRemark();

			String name = UserInfoUtil.getShowName(chatUser, remark);
			wh.neaten(content, (info, c) -> {
				MessageContentWrap contentWrap = wh.wrap(chatSimpleUser, c, name, status, timeVisible, timeText, isOwn, true);
				content(contentWrap);
			});
		}
	}

	private void content(MessageContentWrap contentWrap) {
		if (contentWrap.isOwn()) {
			contentWrap.setAvatar(ownerAvatar);
			contentWrap.getUser().setAvatar(ownerAvatar);
		} else {
			contentWrap.setAvatar(userAvatar);
			contentWrap.getUser().setAvatar(userAvatar);
		}
		String json = JsonUtil.toJson(contentWrap);
		FxUtil.invoke(() -> {
			readMapper.insertBefore(json);
		});
	}

	private void laodData() {
		UserManager userManager = this.appContext.getObject(UserManager.class);
		userManager.getOrLoadById(userId, (info, user) -> {
			setUser(user);
			loadAvatar(user);
		});

		ContactRelationManager cch = this.appContext.getObject(ContactRelationManager.class);
		cch.getOrLoadByContactUserId(userId, (info, relation) -> {
			setRelation(relation);
		});
	}

	private void loadAvatar(User user) {

		UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User ownerUser = pb.getUser();
		hi.loadAvatarUrl(ownerUser.getHead(), ownerUser.getAvatar(), (url) -> {
			if (null != url) {
				ownerAvatar = url.toExternalForm();
			}
		});

		if (null != user) {
			hi.loadAvatarUrl(user.getHead(), user.getAvatar(), (url) -> {
				if (null != url) {
					userAvatar = url.toExternalForm();
				}
				loadHistory(userId, null);
			});
		}
	}
}
