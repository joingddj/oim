
package com.oimchat.client.platform.fx.work.im.service;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.general.kernel.work.module.im.data.dto.UserChatData;
import com.oimchat.client.general.kernel.work.module.im.data.query.UserChatUnreadQuery;
import com.oimchat.client.general.kernel.work.module.im.entity.UserChatUnread;
import com.oimchat.client.general.kernel.work.module.im.manager.UserChatDataManager;
import com.oimchat.client.platform.kernel.work.module.im.function.RecentChatUserFunction;
import com.oimchat.client.platform.kernel.work.module.im.function.UserChatUnreadFunction;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 19:57:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatUnreadService extends AbstractMaterial {

	public UserChatUnreadService(AppContext appContext) {
		super(appContext);
	}

	public void loadAllList() {
		UserChatUnreadQuery query = new UserChatUnreadQuery();
		UserChatDataManager manager = this.appContext.getObject(UserChatDataManager.class);
		manager.getAllUnreadList(query, (info, list) -> {
			setUnreadList(list);
		});
	}

	public void loadListByCount(int count) {
		UserChatUnreadQuery query = new UserChatUnreadQuery();
		UserChatDataManager manager = this.appContext.getObject(UserChatDataManager.class);
		manager.getUnreadCountList(query, count, (info, list) -> {
			setUnreadList(list);
		});
	}

	public void setUnreadList(List<UserChatUnread> list) {
		if (null != list) {
			List<String> contentIds = new ArrayList<>();
			// List<String> groupContentIds = new ArrayList<>();
			for (UserChatUnread v : list) {
				if (null != v.getLastContentId()) {
					contentIds.add(v.getLastContentId());
				}
			}
			this.setUserContentIds(list, contentIds);
		}
	}

	public void setUserContentIds(List<UserChatUnread> list, List<String> contentIds) {
		UserChatDataManager manager = this.appContext.getObject(UserChatDataManager.class);
		RecentChatUserFunction recentWork = this.appContext.getObject(RecentChatUserFunction.class);
		UserChatUnreadFunction unreadWork = this.appContext.getObject(UserChatUnreadFunction.class);

		manager.getListByContentIds(contentIds, (info, lb) -> {
			List<UserChatData> items = null == lb ? new ArrayList<>() : lb.getItems();
			recentWork.setRecentChatList(items);
			unreadWork.setList(list);
		});
	}
}
