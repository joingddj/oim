
package com.oimchat.client.general.kernel.work.module.im.handler;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.im.neaten.MessageReceiveContentNeaten;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-28 10:56:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageContentWrapHandler extends AbstractMaterial {

	public MessageContentWrapHandler(AppContext appContext) {
		super(appContext);
	}

	public void neaten(Content content, ValueBack<Content> back) {
		MessageReceiveContentNeaten mrcn = this.appContext.getObject(MessageReceiveContentNeaten.class);
		if (null != mrcn) {
			mrcn.neaten(content, back);
		} else {
			back.back(new Info(), content);
		}
	}

	public MessageContentWrap wrap(
			UserSimple chatUser,
			Content content,
			String showName,
			int status,
			boolean timeVisible,
			String timeText,
			boolean isOwnChatUser,
			boolean receive) {
		String id = content.getId();
		String key = content.getKey();

		long timestamp = content.getTimestamp();

		boolean own = isOwnChatUser;
		String name = null == showName ? chatUser.getName() : showName;
		String avatar = chatUser.getAvatar();
		String userId = chatUser.getId();
		/**
		 * 0:发送中 1:发送成功 2:发送失败
		 */
		// int status = receive ? 0 : 1;
		boolean nameVisible = !isOwnChatUser;

		MessageContentWrap contentWrap = new MessageContentWrap();

		contentWrap.setId(id);
		contentWrap.setKey(key);

		contentWrap.setAvatar(avatar);
		contentWrap.setName(name);
		contentWrap.setNameVisible(nameVisible);
		contentWrap.setUserId(userId);

		contentWrap.setUser(chatUser);

		contentWrap.setOwn(own);

		contentWrap.setReceive(receive);
		contentWrap.setStatus(status);

		contentWrap.setTimestamp(timestamp);
		contentWrap.setTimeText(timeText);
		contentWrap.setTimeVisible(timeVisible);

		contentWrap.setContent(content);
		return contentWrap;
	}
}
