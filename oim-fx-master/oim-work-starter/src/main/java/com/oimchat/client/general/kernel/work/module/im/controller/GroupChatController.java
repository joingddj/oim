
package com.oimchat.client.general.kernel.work.module.im.controller;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupMemberManager;
import com.oimchat.client.general.kernel.work.module.im.box.GroupSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.handler.GroupChatHandler;
import com.oimchat.client.general.kernel.work.module.im.service.GroupChatService;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-23 18:42:02<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatController extends AbstractMaterial {

	public GroupChatController(AppContext appContext) {
		super(appContext);
	}

	public void send(
			String groupId,
			Content content,
			InfoBack back) {

		GroupSendContentBox box = this.appContext.getObject(GroupSendContentBox.class);
		box.putSend(groupId, content);
		doSend(groupId, content, back);
	}

	private void doSend(
			String groupId,
			Content content,
			InfoBack back) {

		PersonalBox personalBox = this.appContext.getObject(PersonalBox.class);
		String userId = personalBox.getOwnerUserId();
		User ownUser = personalBox.getUser();

		UserSimple user = new UserSimple();
		BeanUtils.copyProperties(ownUser, user);

		GroupChatService cs = this.appContext.getObject(GroupChatService.class);
		cs.chat(groupId, userId, content, user, false);

		GroupChatHandler ch = this.appContext.getObject(GroupChatHandler.class);

		GroupMemberManager mm = this.appContext.getObject(GroupMemberManager.class);
		mm.getOrLoadByGroupIdUserId(groupId, userId, (gi, gm) -> {
			if (null != gm) {
				user.setRemark(gm.getNickname());
			}
			ch.chat(groupId, userId, user, content, (info) -> {
				if (!info.isSuccess()) {
					cs.chat(groupId, userId, content, user, false, 2);
				}
				back.back(info);
			});
		});
	}

	public void resend(String groupId, String messageKey, InfoBack back) {
		GroupSendContentBox box = this.appContext.getObject(GroupSendContentBox.class);
		Content content = box.getSend(groupId, messageKey);
		if (null != content) {
			doSend(groupId, content, back);
		}
	}
}
