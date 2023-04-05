
package com.oimchat.client.general.kernel.work.module.im.service;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.general.kernel.work.module.im.box.UserLastTimeBox;
import com.oimchat.client.general.kernel.work.module.im.box.UserSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageData;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageVO;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.general.kernel.work.module.im.handler.MessageContentWrapHandler;
import com.oimchat.client.general.kernel.work.module.im.observer.UserCahtListener;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-22 18:53:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatService extends AbstractMaterial {

	public UserChatService(AppContext appContext) {
		super(appContext);
	}

	public void motion(String sendUserId, String receiveUserId, Motion motion, boolean receive) {
		UserMessageVO<Motion> data = new UserMessageVO<>();
		UserManager userManager = this.appContext.getObject(UserManager.class);

		PersonalBox personalBox = this.appContext.getObject(PersonalBox.class);
		String ownUserId = personalBox.getUserId();
		User ownUser = personalBox.getUser();
		boolean isOwnChatUser = sendUserId.equals(ownUserId);
		String showUserId = (isOwnChatUser) ? receiveUserId : sendUserId;

		userManager.getOrLoadById(showUserId, (info, user) -> {
			if (info.isSuccess()) {
				UserCahtListener l = this.appContext.getObject(UserCahtListener.class);

				User showUser = user;
				User chatUser = (isOwnChatUser) ? ownUser : user;

				data.setReceive(receive);
				data.setOwn(isOwnChatUser);

				data.setReceiveUserId(receiveUserId);
				data.setSendUserId(sendUserId);

				data.setChatUser(chatUser);
				data.setShowUser(showUser);

				data.setData(motion);

				l.motionValue(data);
			}
		});
	}

	public void chat(String sendUserId, String receiveUserId, Content content, boolean receive) {
		chat(sendUserId, receiveUserId, content, receive, receive ? 1 : 0);
	}

	public void chat(String sendUserId, String receiveUserId, Content content, boolean receive, int status) {

		PersonalBox personalBox = this.appContext.getObject(PersonalBox.class);
		String ownUserId = personalBox.getUserId();
		User ownUser = personalBox.getUser();
		boolean isOwnChatUser = sendUserId.equals(ownUserId);
		String showUserId = (isOwnChatUser) ? receiveUserId : sendUserId;

		UserManager userManager = this.appContext.getObject(UserManager.class);
		userManager.getOrLoadById(showUserId, (info, user) -> {
			if (!info.isSuccess()) {
				user = new User();
				user.setId(showUserId);
				user.setNickname("加载失败的会话用户");
			}
			User showUser = user;
			User chatUser = (isOwnChatUser) ? ownUser : user;
			handler(receive, sendUserId, receiveUserId, isOwnChatUser, status, showUser, chatUser, content);

		});
	}

	public void handler(boolean receive, String sendUserId, String receiveUserId, boolean isOwnChatUser, int status, User showUser, User chatUser, Content content) {
		UserSendContentBox box = this.appContext.getObject(UserSendContentBox.class);

		MessageContentWrapHandler cwh = this.appContext.getObject(MessageContentWrapHandler.class);
		cwh.neaten(content, (info, c) -> {
			if (isOwnChatUser) {
				String name = UserInfoUtil.getShowName(chatUser);
				chat(receive, sendUserId, receiveUserId, isOwnChatUser, name, status, showUser, chatUser, c);
			} else {
				ContactRelationManager relationManager = this.appContext.getObject(ContactRelationManager.class);
				relationManager.getOrLoadByContactUserId(chatUser.getId(), (ri, relation) -> {
					String name = UserInfoUtil.getShowName(chatUser, null == relation ? "" : relation.getRemark());
					chat(receive, sendUserId, receiveUserId, isOwnChatUser, name, status, showUser, chatUser, c);
				});
			}
		});
		if (receive) {
			String key = showUser.getId();
			String messageKey = content.getKey();
			box.removeSend(key, messageKey);
		}
	}

	public void chat(boolean receive,
			String sendUserId,
			String receiveUserId,
			boolean isOwnChatUser,
			String name,
			int status,
			User showUser,
			User chatUser,
			Content content) {

		String id = showUser.getId();

		UserLastTimeBox lastTimeBox = this.appContext.getObject(UserLastTimeBox.class);
		Long lastTimestamp = lastTimeBox.getAndPut(id);
		Long timestamp = content.getTimestamp();

		UserSimple user = new UserSimple();
		BeanUtils.copyProperties(chatUser, user);

		ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);

		boolean timeVisible = timeTextHandler.timeVisible(timestamp, lastTimestamp);
		String timeText = timeTextHandler.timeText(timestamp);

		MessageContentWrapHandler cwh = this.appContext.getObject(MessageContentWrapHandler.class);
		MessageContentWrap contentWrap = cwh.wrap(user, content, name, status, timeVisible, timeText, isOwnChatUser, receive);

		UserMessageData data = new UserMessageData();

		data.setReceive(receive);
		data.setOwn(isOwnChatUser);

		data.setReceiveUserId(receiveUserId);
		data.setSendUserId(sendUserId);

		data.setChatUser(chatUser);
		data.setShowUser(showUser);

		data.setContent(content);
		data.setContentWrap(contentWrap);

		UserCahtListener l = this.appContext.getObject(UserCahtListener.class);
		l.messageValue(data);
	}

	public void input(String sendUserId, String receiveUserId) {
		// TODO Auto-generated method stub

	}
}
