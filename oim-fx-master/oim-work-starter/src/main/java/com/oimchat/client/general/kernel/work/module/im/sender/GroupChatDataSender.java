package com.oimchat.client.general.kernel.work.module.im.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.im.data.query.GroupChatQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 
 * 群聊记录业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */
@Sender
@ActionMapping(value = "2.3.002")
public interface GroupChatDataSender {

	/**
	 * 
	 * 历史记录数量<br>
	 * Date 2020-04-17 19:34:42<br>
	 * 
	 * @param query
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void count(
			@Define("body.query") GroupChatQuery query,
			DataBackAction back);

	/**
	 * 
	 * 历史记录列表<br>
	 * Date 2020-04-17 19:34:54<br>
	 * 
	 * @param query
	 * @param page
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.query") GroupChatQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 根据id获取详情<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @param contentId
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void getByContentId(
			@Define("body.contentId") String contentId,
			DataBackAction back);

	// 1.1.0004 ids 1.1.0005 key 1.1.0006 keys

	@ActionMapping(value = "1.1.0004")
	public void getListByContentIds(
			@Define("body.contentIds") List<String> contentIds,
			DataBackAction back);

	/**
	 * 
	 * 获取聊天记录(以数据库id为条件)<br>
	 * Date 2019-05-25 08:04:55<br>
	 * 
	 * @param groupId
	 * @param startId
	 * @param direction
	 * @param count
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0007")
	public void getListFromStartId(
			@Define("body.groupId") String groupId,
			@Define("body.startId") String startId,
			@Define("body.direction") String direction,
			@Define("body.count") int count,
			DataBackAction back);

	/**
	 * 
	 * 获取聊天记录(以消息key为条件) <br>
	 * Date 2019-05-25 08:05:13<br>
	 * 
	 * @param groupId
	 * @param startMessageKey
	 * @param direction
	 * @param count
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0008")
	public void getListFromStartMessageKey(
			@Define("body.groupId") String groupId,
			@Define("body.startMessageKey") String startMessageKey,
			@Define("body.direction") String direction,
			@Define("body.count") int count,
			DataBackAction back);

	// 1.1.0008 1.1.0009 1.1.0010

	// 获取未读消息数量 1.1.0011
	// 获取未读消息列表 1.1.0012
}
