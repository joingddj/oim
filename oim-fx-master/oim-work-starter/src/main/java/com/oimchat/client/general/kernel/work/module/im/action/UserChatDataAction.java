package com.oimchat.client.general.kernel.work.module.im.action;

import com.oimchat.client.general.kernel.work.module.im.service.UserChatPromptService;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * 私聊记录业务推送<br>
 * Date 2016年8月26日 上午9:21:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

@ActionMapping("2.2.002")
public class UserChatDataAction extends AbstractMaterial {

	public UserChatDataAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送私聊信息已读(userId)<br>
	 * Date 2020-04-16 20:25:17<br>
	 * 
	 * @param writeKeys
	 * @param key
	 * @param sendUserId
	 * @param receiveUserId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void read(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownUserId = pb.getOwnerUserId();
		boolean isSendOwn = ownUserId.equals(sendUserId);
		String userId = (isSendOwn) ? receiveUserId : sendUserId;
		UserChatPromptService sevice = this.appContext.getObject(UserChatPromptService.class);
		sevice.removePrompt(userId);
	}

	/**
	 * 
	 * 推送私聊信息已读(contentId)<br>
	 * Date 2020-04-21 10:10:25<br>
	 * 
	 * @param writeKeys
	 * @param key
	 * @param sendUserId
	 * @param receiveUserId
	 * @param contentId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void read(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId,
			@Define("body.contentId") String contentId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownUserId = pb.getOwnerUserId();
		boolean isSendOwn = ownUserId.equals(sendUserId);
		String userId = (isSendOwn) ? receiveUserId : sendUserId;
		UserChatPromptService sevice = this.appContext.getObject(UserChatPromptService.class);
		sevice.removePrompt(userId);
	}
}
