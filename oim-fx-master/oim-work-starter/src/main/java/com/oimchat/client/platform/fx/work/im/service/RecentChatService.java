
package com.oimchat.client.platform.fx.work.im.service;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.im.data.query.RecentChatQuery;
import com.oimchat.client.general.kernel.work.module.im.entity.RecentChat;
import com.oimchat.client.general.kernel.work.module.im.handler.GroupChatDataHandler;
import com.oimchat.client.general.kernel.work.module.im.handler.RecentChatHandler;
import com.oimchat.client.general.kernel.work.module.im.handler.UserChatDataHandler;
import com.oimchat.client.platform.kernel.work.module.im.function.RecentChatGroupFunction;
import com.oimchat.client.platform.kernel.work.module.im.function.RecentChatUserFunction;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 17:47:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RecentChatService extends AbstractMaterial {

	public RecentChatService(AppContext appContext) {
		super(appContext);
	}

	public void loadListByCount(int count, ValueBack<List<RecentChat>> back) {
		RecentChatHandler handler = this.appContext.getObject(RecentChatHandler.class);
		RecentChatQuery query = new RecentChatQuery();
		List<RecentChat> allList = new ArrayList<>(count);
		PageUtil.stream(count, 100, query, (n, p, q) -> {
			handler.list(query, p, (i, pr) -> {
				List<RecentChat> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
				allList.addAll(list);
				if (p.getNumber() >= p.getTotalPage()) {
					back.back(i, allList);
				} else {
					n.next();
				}
			});
		});
	}

	public void loadListByCount(int count, InfoBack back) {
		loadListByCount(count, (info, list) -> {
			setRecentChatList(list);
			back.back(info);
		});
	}

	public void setRecentChatList(List<RecentChat> list) {
		if (null != list) {
			List<String> userContentIds = new ArrayList<>();
			List<String> groupContentIds = new ArrayList<>();
			for (RecentChat v : list) {
				if (RecentChat.TYPE_USER.equals(v.getType())) {
					userContentIds.add(v.getContentId());
				}
				if (RecentChat.TYPE_GROUP.equals(v.getType())) {
					groupContentIds.add(v.getContentId());
				}
			}
			this.setUserContentIds(userContentIds);
			this.setGroupContentIds(groupContentIds);
		}
	}

	public void setUserContentIds(List<String> contentIds) {
		UserChatDataHandler handler = this.appContext.getObject(UserChatDataHandler.class);
		RecentChatUserFunction work = this.appContext.getObject(RecentChatUserFunction.class);

		handler.getListByContentIds(contentIds, (info, bl) -> {
			if (null != bl) {
				work.setRecentChatList(bl.getItems());
			}
		});
	}

	public void setGroupContentIds(List<String> contentIds) {
		GroupChatDataHandler handler = this.appContext.getObject(GroupChatDataHandler.class);
		RecentChatGroupFunction work = this.appContext.getObject(RecentChatGroupFunction.class);
		handler.getListByContentIds(contentIds, (info, bl) -> {
			if (null != bl) {
				work.setRecentChatList(bl.getItems());
			}
		});
	}
}
