package com.oimchat.client.general.kernel.work.module.im.handler;

import java.util.List;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.im.data.dto.GroupChatData;
import com.oimchat.client.general.kernel.work.module.im.data.query.GroupChatQuery;
import com.oimchat.client.general.kernel.work.module.im.sender.GroupChatDataSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.ListBody;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 
 * 群聊记录业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

public class GroupChatDataHandler extends AbstractMaterial {

	public GroupChatDataHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 历史记录数量<br>
	 * Date 2020-04-17 19:34:42<br>
	 * 
	 * @param query
	 * @since 1.0.0
	 */

	public void count(
			GroupChatQuery query,
			ValueBack<CountInfo> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.count(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 
	 * 历史记录列表<br>
	 * Date 2020-04-17 19:34:54<br>
	 * 
	 * @param query
	 * @param page
	 * @since 1.0.0
	 */

	public void list(
			GroupChatQuery query,
			Page page,
			ValueBack<PageResult<List<GroupChatData>>> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.list(query, page, new ValueBackActionImpl<PageResult<List<GroupChatData>>>(
				back,
				new TypeClass<PageResult<List<GroupChatData>>>() {
				}));
	}

	/**
	 * 
	 * 根据id获取详情<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @param contentId
	 * @since 1.0.0
	 */

	public void getByContentId(
			String contentId,
			ValueBack<GroupChatData> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.getByContentId(contentId, new ValueBackActionImpl<GroupChatData>(back, GroupChatData.class));
	}

	// 1.1.0004 ids 1.1.0005 key 1.1.0006 keys

	public void getListByContentIds(
			List<String> contentIds,
			ValueBack<ListBody<List<GroupChatData>>> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.getListByContentIds(contentIds, new ValueBackActionImpl<ListBody<List<GroupChatData>>>(back,
				new TypeClass<ListBody<List<GroupChatData>>>() {
				}));
	}

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

	public void getListFromStartId(
			String groupId,
			String startId,
			String direction,
			int count,
			ValueBack<ListBody<List<GroupChatData>>> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.getListFromStartId(groupId, startId, direction, count, new ValueBackActionImpl<ListBody<List<GroupChatData>>>(back,
				new TypeClass<ListBody<List<GroupChatData>>>() {
				}));
	}

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

	public void getListFromStartMessageKey(
			String groupId,
			String startMessageKey,
			String direction,
			int count,
			ValueBack<ListBody<List<GroupChatData>>> back) {
		GroupChatDataSender sender = this.appContext.getObject(GroupChatDataSender.class);
		sender.getListFromStartMessageKey(groupId, startMessageKey, direction, count, new ValueBackActionImpl<ListBody<List<GroupChatData>>>(back,
				new TypeClass<ListBody<List<GroupChatData>>>() {
				}));
	}

	// 1.1.0008 1.1.0009 1.1.0010

	// 获取未读消息数量 1.1.0011
	// 获取未读消息列表 1.1.0012
}
