package com.oimchat.client.general.kernel.work.module.im.manager;

import java.util.List;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.im.data.dto.GroupChatData;
import com.oimchat.client.general.kernel.work.module.im.data.query.GroupChatQuery;
import com.oimchat.client.general.kernel.work.module.im.handler.GroupChatDataHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.ListBody;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 
 * 私聊记录业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

public class GroupChatDataManager extends AbstractMaterial {

	public GroupChatDataManager(AppContext appContext) {
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
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.count(query, back);
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
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.list(query, page, back);
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
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.getByContentId(contentId, back);
	}

	// 1.1.0004 ids 1.1.0005 key 1.1.0006 keys

	public void getListByContentIds(
			List<String> contentIds,
			ValueBack<ListBody<List<GroupChatData>>> back) {
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.getListByContentIds(contentIds, back);
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
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.getListFromStartId(groupId, startId, direction, count, back);
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
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		handler.getListFromStartMessageKey(groupId, startMessageKey, direction, count, back);
	}
}
