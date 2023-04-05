package com.oimchat.client.general.kernel.work.module.im.action;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.service.UserChatService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * 私聊业务推送<br>
 * Date 2016年8月26日 上午9:21:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

@ActionMapping("2.2.001")
public class UserChatAction extends AbstractMaterial {

	public UserChatAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 动作<br>
	 * Date 2020-04-16 20:25:47<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @param motion
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0000")
	public void motion(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId,
			@Define("body.motion") Motion motion) {
		UserChatService receive = this.appContext.getObject(UserChatService.class);
		receive.motion(sendUserId, receiveUserId, motion, true);
	}

	/**
	 * 
	 * 推送私聊信息<br>
	 * Date 2020-04-16 20:25:17<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @param content
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void chat(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId,
			@Define("body.content") Content content) {
		UserChatService receive = this.appContext.getObject(UserChatService.class);
		receive.chat(sendUserId, receiveUserId, content, true);
	}

	/**
	 * 
	 * 输入中<br>
	 * Date 2020-04-21 10:10:25<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @param content
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void input(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId) {
		UserChatService receive = this.appContext.getObject(UserChatService.class);
		receive.input(sendUserId, receiveUserId);
	}
}
