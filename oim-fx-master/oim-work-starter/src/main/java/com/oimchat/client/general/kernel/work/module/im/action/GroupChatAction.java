package com.oimchat.client.general.kernel.work.module.im.action;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.kernel.work.module.im.service.GroupChatService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群聊业务推送
 * 
 * @author: XiaHui
 * @date: 2016-08-26 9:21:41
 * @docModuleSuperKey 2.0.0
 */

@ActionMapping("2.3.001")
public class GroupChatAction extends AbstractMaterial {

	public GroupChatAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送群聊信息<br>
	 * Date 2020-04-16 20:29:18<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void chat(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			@Define("body.content") Content content,
			@Define("body.user") UserSimple user) {
		GroupChatService service = this.appContext.getObject(GroupChatService.class);
		service.chat(groupId, userId, content, user, true);
	}
}
