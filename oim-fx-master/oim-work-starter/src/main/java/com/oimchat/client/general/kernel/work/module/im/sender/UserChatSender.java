package com.oimchat.client.general.kernel.work.module.im.sender;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 
 * 私聊业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */
@Sender
@ActionMapping(value = "2.2.001")
public interface UserChatSender {

	/**
	 * 
	 * 向用户发送抖动 <br>
	 * Date 2019-05-22 17:57:32<br>
	 * 
	 * @param receiveUserId
	 * @param motion
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0000")
	public void motion(
			@Define("body.receiveUserId") String receiveUserId,
			@Define("body.motion") Motion motion,
			DataBackAction back);

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
	@ActionMapping(value = "1.1.0001")
	public void chat(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId,
			@Define("body.content") Content content,
			DataBackAction back);

	/**
	 * 
	 * Description <br>
	 * Date 2020-04-21 10:13:01<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void input(
			@Define("body.sendUserId") String sendUserId,
			@Define("body.receiveUserId") String receiveUserId,
			DataBackAction back);
}
