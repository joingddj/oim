package com.oimchat.client.general.kernel.work.module.im.sender;

import com.oimchat.client.general.kernel.work.module.im.data.query.RecentChatQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 
 * 最近聊天业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */
@Sender
@ActionMapping(value = "2.1.001")
public interface RecentChatSender {

	/**
	 * 
	 * 获取数量<br>
	 * Date 2020-04-17 19:34:42<br>
	 * 
	 * @param query
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void count(
			@Define("body.query") RecentChatQuery query,
			DataBackAction back);

	/**
	 * 
	 * 获取列表<br>
	 * Date 2020-04-17 19:34:54<br>
	 * 
	 * @param query
	 * @param page
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.query") RecentChatQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 根据id获取详情<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void getById(
			@Define("body.id") String id,
			DataBackAction back);

	/**
	 * 
	 * 删除<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0004")
	public void remove(
			@Define("body.userId") String userId,
			@Define("body.chatId") String chatId,
			@Define("body.type") String type,
			DataBackAction back);
}
