package com.oimchat.client.general.kernel.work.module.im.handler;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.general.kernel.work.module.im.sender.GroupChatSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * 
 * 群聊业务接口<br>
 * Date 2020-04-16 20:43:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

public class GroupChatHandler extends AbstractMaterial {

	public GroupChatHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 群聊<br>
	 * Date 2019-02-17 12:26:38<br>
	 * 
	 * @since 1.0.0
	 */

	public void chat(
			String groupId,
			String userId,
			UserSimple user,
			Content content,
			InfoBack back) {
		GroupChatSender sender = this.appContext.getObject(GroupChatSender.class);
		sender.chat(groupId, userId, user, content, new InfoBackActionImpl(back));
	}
}
