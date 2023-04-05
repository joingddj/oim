
package com.oimchat.client.general.kernel.work.module.im.service;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.common.step.task.TaskNext;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupManager;
import com.oimchat.client.general.kernel.work.module.im.box.GroupLastTimeBox;
import com.oimchat.client.general.kernel.work.module.im.box.GroupSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.data.vo.GroupMessageData;
import com.oimchat.client.general.kernel.work.module.im.handler.ChatTimeTextHandler;
import com.oimchat.client.general.kernel.work.module.im.handler.MessageContentWrapHandler;
import com.oimchat.client.general.kernel.work.module.im.observer.GroupCahtListener;
import com.oimchat.client.general.kernel.work.module.im.util.ChatUtil;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-22 18:53:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatService extends AbstractMaterial {

	public GroupChatService(AppContext appContext) {
		super(appContext);
	}

	public void chat(String groupId, String userId, Content content, UserSimple user, boolean receive) {
		chat(groupId, userId, content, user, receive, receive ? 1 : 0);
	}

	public void chat(String groupId, String userId, Content content, UserSimple user, boolean receive, int status) {

		PersonalBox personalBox = this.appContext.getObject(PersonalBox.class);
		String ownUserId = personalBox.getUserId();
		User ownUser = personalBox.getUser();
		boolean isOwnChatUser = userId.equals(ownUserId);
		boolean isNullUser = (null == user || StringUtil.isBlank(user.getId()));

		if (isNullUser && isOwnChatUser) {
			user = new UserSimple();
			BeanUtils.copyProperties(ownUser, user);
		}

		UserManager userManager = this.appContext.getObject(UserManager.class);
		if (isOwnChatUser || !isNullUser) {
			handlerContent(groupId,
					userId,
					content,
					user,
					status,
					isOwnChatUser,
					receive);
		} else {
			userManager.getOrLoadById(userId, (info, u) -> {
				UserSimple su = null;
				if (!info.isSuccess()) {
					su = new UserSimple();
					su.setId(userId);
					su.setNickname("加载失败的用户");
				} else {
					su = new UserSimple();
					BeanUtils.copyProperties(u, su);
				}
				handlerContent(groupId,
						userId,
						content,
						su,
						status,
						isOwnChatUser,
						receive);

			});
		}
	}

	public void handlerContent(
			String groupId,
			String userId,
			Content content,
			UserSimple user,
			int status,
			boolean isOwnChatUser,
			boolean receive) {
		GroupSendContentBox box = this.appContext.getObject(GroupSendContentBox.class);

		MessageContentWrapHandler cwh = this.appContext.getObject(MessageContentWrapHandler.class);
		cwh.neaten(content, (info, contentData) -> {
			if (isOwnChatUser) {
				String name = ChatUtil.getShowName(user.getRemark(), user.getNickname());
				chat(
						groupId,
						userId,
						content,
						user,
						status,
						isOwnChatUser,
						name,
						receive);
			} else {
				ContactRelationManager relationManager = this.appContext.getObject(ContactRelationManager.class);
				relationManager.getOrLoadByContactUserId(userId, (ri, relation) -> {
					String name = ChatUtil.getShowName(user.getRemark(), user.getNickname(), null == relation ? "" : relation.getRemark());
					chat(
							groupId,
							userId,
							content,
							user,
							status,
							isOwnChatUser,
							name,
							receive);
				});
			}
		});
		if (receive) {
			String key = groupId;
			String messageKey = content.getKey();
			box.removeSend(key, messageKey);
		}
	}

	public void chat(
			String groupId,
			String userId,
			Content content,
			UserSimple user,
			int status,
			boolean isOwnChatUser,
			String name,
			boolean receive) {

		GroupLastTimeBox lastTimeBox = this.appContext.getObject(GroupLastTimeBox.class);
		Long lastTimestamp = lastTimeBox.getAndPut(groupId);
		Long timestamp = content.getTimestamp();

		ChatTimeTextHandler timeTextHandler = this.appContext.getObject(ChatTimeTextHandler.class);

		boolean timeVisible = timeTextHandler.timeVisible(timestamp, lastTimestamp);
		String timeText = timeTextHandler.timeText(timestamp);

		MessageContentWrapHandler cwh = this.appContext.getObject(MessageContentWrapHandler.class);
		MessageContentWrap contentWrap = cwh.wrap(user, content, name, status, timeVisible, timeText, isOwnChatUser, receive);

		GroupMessageData data = new GroupMessageData();

		data.setReceive(receive);
		data.setOwn(isOwnChatUser);

		data.setGroupId(groupId);
		data.setUserId(userId);

		data.setUser(user);

		data.setContent(content);
		data.setContentWrap(contentWrap);

		TaskNext tn = new TaskNext();
		tn.set((n) -> {
			GroupManager manager = this.appContext.getObject(GroupManager.class);
			manager.getLocalOrRemoteById(groupId, (info, g) -> {
				if (g == null) {
					g = new Group();
					g.setId(groupId);
				}
				data.setGroup(g);
				n.next();
			});
		}).set((n) -> {
			GroupCahtListener l = this.appContext.getObject(GroupCahtListener.class);
			l.messageValue(data);
			n.next();
		});
		tn.next();
	}
}
