package com.oimchat.client.general.kernel.work.module.im.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.im.data.dto.UserChatData;
import com.oimchat.client.general.kernel.work.module.im.data.query.UserChatQuery;
import com.oimchat.client.general.kernel.work.module.im.data.query.UserChatUnreadQuery;
import com.oimchat.client.general.kernel.work.module.im.entity.UserChatUnread;
import com.oimchat.client.general.kernel.work.module.im.sender.UserChatDataSender;
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

public class UserChatDataHandler extends AbstractMaterial {

	public UserChatDataHandler(AppContext appContext) {
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
			UserChatQuery query,
			ValueBack<CountInfo> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
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
			UserChatQuery query,
			Page page,
			ValueBack<PageResult<List<UserChatData>>> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.list(query, page, new ValueBackActionImpl<PageResult<List<UserChatData>>>(back,
				new TypeClass<PageResult<List<UserChatData>>>() {
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
			ValueBack<UserChatData> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getByContentId(contentId, new ValueBackActionImpl<UserChatData>(back,
				UserChatData.class));
	}

	// 1.1.0004 ids 1.1.0005 key 1.1.0006 keys

	public void getListByContentIds(
			List<String> contentIds,
			ValueBack<ListBody<List<UserChatData>>> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getListByContentIds(contentIds, new ValueBackActionImpl<ListBody<List<UserChatData>>>(back,
				new TypeClass<ListBody<List<UserChatData>>>() {
				}));
	}

	/**
	 * 
	 * 获取聊天记录(以数据库id为条件)<br>
	 * Date 2019-05-25 08:04:55<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @param startId
	 * @param direction
	 * @param count
	 * @since 1.0.0
	 */

	public void getListFromStartId(
			String sendUserId,
			String receiveUserId,
			String startId,
			String direction,
			int count,
			ValueBack<ListBody<List<UserChatData>>> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getListFromStartId(sendUserId, receiveUserId, startId, direction, count, new ValueBackActionImpl<ListBody<List<UserChatData>>>(back,
				new TypeClass<ListBody<List<UserChatData>>>() {
				}));
	}

	/**
	 * 
	 * 获取聊天记录(以消息key为条件) <br>
	 * Date 2019-05-25 08:05:13<br>
	 * 
	 * @param sendUserId
	 * @param receiveUserId
	 * @param startMessageKey
	 * @param direction
	 * @param count
	 * @since 1.0.0
	 */

	public void getListFromStartMessageKey(
			String sendUserId,
			String receiveUserId,
			String startMessageKey,
			String direction,
			int count,
			ValueBack<ListBody<List<UserChatData>>> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getListFromStartMessageKey(sendUserId, receiveUserId, startMessageKey, direction, count, new ValueBackActionImpl<ListBody<List<UserChatData>>>(back,
				new TypeClass<ListBody<List<UserChatData>>>() {
				}));
	}

	/**
	 * 
	 * 标记信息为已读(根据sendUserId)<br>
	 * Date 2019-05-25 08:23:23<br>
	 * 
	 * @param sendUserId
	 * @since 1.0.0
	 */

	public void updateToReadBySendUserId(
			String sendUserId,
			InfoBack back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.updateToReadBySendUserId(sendUserId, new InfoBackActionImpl(back));
	}

	/**
	 * 
	 * 标记信息为已读(根据contentId)<br>
	 * Date 2020-04-16 19:46:30<br>
	 * 
	 * @param contentId
	 * @since 1.0.0
	 */

	public void updateToReadByContentId(
			String sendUserId,
			String contentId,
			InfoBack back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.updateToReadByContentId(sendUserId, contentId, new InfoBackActionImpl(back));
	}

	/**
	 * 
	 * 标记信息为已读(根据messageKey)<br>
	 * Date 2020-04-16 19:46:30<br>
	 * 
	 * @param contentId
	 * @since 1.0.0
	 */

	public void updateToReadByMessageKey(
			String messageKey,
			InfoBack back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.updateToReadByMessageKey(messageKey, new InfoBackActionImpl(back));
	}

	// 1.1.0010 1.1.0012 1.1.0014

	/**
	 * 
	 * 获取未读消息联系人数量<br>
	 * Date 2020-04-17 19:35:46<br>
	 * 
	 * @param query
	 * @since 1.0.0
	 */

	public void getUnreadCount(
			UserChatUnreadQuery query,
			ValueBack<CountInfo> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getUnreadCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));

	}

	/**
	 * 
	 * 获取未读消息联系人列表<br>
	 * Date 2020-04-17 19:36:13<br>
	 * 
	 * @param page
	 * @param query
	 * @since 1.0.0
	 */
	public void getUnreadList(
			Page page,
			UserChatUnreadQuery query,
			ValueBack<ListBody<List<UserChatUnread>>> back) {
		UserChatDataSender sender = this.appContext.getObject(UserChatDataSender.class);
		sender.getUnreadList(page, query, new ValueBackActionImpl<ListBody<List<UserChatUnread>>>(back,
				new TypeClass<ListBody<List<UserChatUnread>>>() {
				}));
	}
}
