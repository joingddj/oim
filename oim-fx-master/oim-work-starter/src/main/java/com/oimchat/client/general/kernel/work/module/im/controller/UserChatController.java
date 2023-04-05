
package com.oimchat.client.general.kernel.work.module.im.controller;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.general.kernel.work.module.im.box.UserSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.handler.UserChatHandler;
import com.oimchat.client.general.kernel.work.module.im.service.UserChatService;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.lib.util.json.JsonUtil;

/**
 * Description <br>
 * Date 2021-03-23 18:42:02<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatController extends AbstractMaterial {

	public UserChatController(AppContext appContext) {
		super(appContext);
	}

	public void send(
			String receiveUserId,
			final Content content,
			InfoBack back) {

		UserSendContentBox box = this.appContext.getObject(UserSendContentBox.class);
		box.putSend(receiveUserId, content);

		doSend(
				receiveUserId,
				content,
				back);
	}

	private void doSend(
			String receiveUserId,
			Content content,
			InfoBack back) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String sendUserId = pb.getOwnerUserId();

		String json = JsonUtil.toJson(content);
		Content contentCopy = JsonUtil.toObject(json, Content.class);

		UserChatService cs = this.appContext.getObject(UserChatService.class);
		// cs.chat(sendUserId, receiveUserId, contentCopy, false);

		UserChatHandler ch = this.appContext.getObject(UserChatHandler.class);
		ch.chat(sendUserId, receiveUserId, content, (info) -> {
			if (!info.isSuccess()) {
				cs.chat(sendUserId, receiveUserId, contentCopy, false, 2);
			}
			back.back(info);
		});
	}

	public void send(String receiveUserId, Motion m, InfoBack back) {
		UserChatHandler ch = this.appContext.getObject(UserChatHandler.class);
		ch.motion(receiveUserId, m, back);
	}

	public void resend(String receiveUserId, String messageKey, InfoBack back) {
		UserSendContentBox box = this.appContext.getObject(UserSendContentBox.class);
		Content content = box.getSend(receiveUserId, messageKey);
		if (null != content) {
			doSend(
					receiveUserId,
					content,
					back);
		}
	}
}
