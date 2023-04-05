
package com.oimchat.client.platform.fx.work.common.service;

import java.net.URL;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupManager;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-02 09:56:11<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupHeadImageService extends AbstractMaterial {

	public GroupHeadImageService(AppContext appContext) {
		super(appContext);
	}

	public void getLocalOrLoadAvatarUrl(String groupId, ValueAction<URL> back) {
		GroupManager um = this.appContext.getObject(GroupManager.class);
		GroupHeadImageHandler headHandler = this.appContext.getObject(GroupHeadImageHandler.class);
		um.getLocalOrRemoteById(groupId, (info, u) -> {
			if (null != u && null != u.getId()) {
				headHandler.getOrLoadAvatarUrl(groupId, u.getHead(), u.getAvatar(), back);
			} else {
				headHandler.loadHeadUrl("1", back);
			}
		});
	}
}
