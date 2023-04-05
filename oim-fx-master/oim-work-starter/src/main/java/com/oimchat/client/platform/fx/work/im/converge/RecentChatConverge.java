
package com.oimchat.client.platform.fx.work.im.converge;

import com.oimchat.client.platform.fx.work.im.service.RecentChatService;
import com.oimchat.client.platform.fx.work.im.service.UserChatUnreadService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 19:39:31<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RecentChatConverge extends AbstractMaterial {

	public RecentChatConverge(AppContext appContext) {
		super(appContext);
	}

	public void load() {

		UserChatUnreadService ucm = this.appContext.getObject(UserChatUnreadService.class);

		RecentChatService rcs = this.appContext.getObject(RecentChatService.class);
		rcs.loadListByCount(100, (info) -> {
			ucm.loadAllList();
		});
	}

}
