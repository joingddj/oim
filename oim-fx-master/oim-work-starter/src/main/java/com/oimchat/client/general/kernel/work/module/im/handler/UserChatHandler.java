package com.oimchat.client.general.kernel.work.module.im.handler;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.sender.UserChatSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * 
 * 私聊业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

public class UserChatHandler extends AbstractMaterial {

	public UserChatHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 向用户发送抖动 <br>
	 * Date 2019-05-22 17:57:32<br>
	 * 
	 * @param receiveUserId
	 * @param motion
	 * @since 1.0.0
	 */

	public void motion(
			String receiveUserId,
			Motion motion,
			InfoBack back) {
		UserChatSender sender = this.appContext.getObject(UserChatSender.class);
		sender.motion(receiveUserId, motion, new InfoBackActionImpl(back));

	}

	/**
	 * 发送私聊信息<br>
	 * Date 2019-02-17 17:51:26<br>
	 * 
	 * @author XiaHui<br>
	 * @param sendUserId
	 * @param receiveUserId
	 * @param content
	 * @since 1.0.0
	 */

	public void chat(
			String sendUserId,
			String receiveUserId,
			Content content,
			InfoBack back) {
		UserChatSender sender = this.appContext.getObject(UserChatSender.class);
		sender.chat(sendUserId, receiveUserId, content, new InfoBackActionImpl(back));
	}

	/**
	 * 
	 * Description <br>
	 * Date 2020-04-21 10:13:01<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @since 1.0.0
	 */

	public void input(
			String sendUserId,
			String receiveUserId,
			InfoBack back) {
		UserChatSender sender = this.appContext.getObject(UserChatSender.class);
		sender.input(sendUserId, receiveUserId, new InfoBackActionImpl(back));
	}
}
