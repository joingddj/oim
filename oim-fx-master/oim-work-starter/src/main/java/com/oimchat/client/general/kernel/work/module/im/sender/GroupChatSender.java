package com.oimchat.client.general.kernel.work.module.im.sender;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 
 * 群聊业务接口<br>
 * Date 2020-04-16 20:43:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */
@Sender
@ActionMapping(value = "2.3.001")
public interface GroupChatSender {

	/**
	 * 
	 * 群聊<br>
	 * Date 2019-02-17 12:26:38<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void chat(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			@Define("body.user") UserSimple user,
			@Define("body.content") Content content,
			DataBackAction back);
}
