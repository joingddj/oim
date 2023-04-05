package com.oimchat.client.general.kernel.work.module.im.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.im.data.query.RecentChatQuery;
import com.oimchat.client.general.kernel.work.module.im.entity.RecentChat;
import com.oimchat.client.general.kernel.work.module.im.sender.RecentChatSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 
 * 最近聊天业务接口<br>
 * Date 2020-04-16 19:34:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 2.0.0
 */

public class RecentChatHandler extends AbstractMaterial {

	public RecentChatHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 获取数量<br>
	 * Date 2020-04-17 19:34:42<br>
	 * 
	 * @param query
	 * @since 1.0.0
	 */
	public void count(
			RecentChatQuery query,
			ValueBack<CountInfo> back) {
		RecentChatSender sender = this.appContext.getObject(RecentChatSender.class);
		sender.count(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 
	 * 获取列表<br>
	 * Date 2020-04-17 19:34:54<br>
	 * 
	 * @param query
	 * @param page
	 * @since 1.0.0
	 */
	public void list(
			RecentChatQuery query,
			Page page,
			ValueBack<PageResult<List<RecentChat>>> back) {
		RecentChatSender sender = this.appContext.getObject(RecentChatSender.class);
		sender.list(query, page, new ValueBackActionImpl<PageResult<List<RecentChat>>>(
				back,
				new TypeClass<PageResult<List<RecentChat>>>() {
				}));
	}

	/**
	 * 
	 * 根据id获取详情<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @since 1.0.0
	 */
	public void getById(
			String id,
			ValueBack<RecentChat> back) {
		RecentChatSender sender = this.appContext.getObject(RecentChatSender.class);
		sender.getById(id, new ValueBackActionImpl<RecentChat>(
				back, RecentChat.class));
	}

	/**
	 * 
	 * 删除<br>
	 * Date 2020-04-17 20:09:35<br>
	 * 
	 * @since 1.0.0
	 */
	public void remove(
			String userId,
			String chatId,
			String type,
			InfoBack back) {
		RecentChatSender sender = this.appContext.getObject(RecentChatSender.class);
		sender.remove(userId, chatId, type, new InfoBackActionImpl(back));
	}
}
