
package com.oimchat.client.platform.fx.work.common.service;

import java.net.URL;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-02 09:56:11<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserHeadImageService extends AbstractMaterial {

	public UserHeadImageService(AppContext appContext) {
		super(appContext);
	}

	public void getLocalOrLoadAvatarUrl(String userId, ValueAction<URL> back) {
		UserManager um = this.appContext.getObject(UserManager.class);
		UserHeadImageHandler headHandler = this.appContext.getObject(UserHeadImageHandler.class);
		um.getLocalOrRemoteById(userId, (info, u) -> {
			if (null != u && null != u.getId()) {
				headHandler.getOrLoadAvatarUrl(userId, u.getHead(), u.getAvatar(), back);
			} else {
				headHandler.loadHeadUrl("1", back);
			}
		});
	}
}
