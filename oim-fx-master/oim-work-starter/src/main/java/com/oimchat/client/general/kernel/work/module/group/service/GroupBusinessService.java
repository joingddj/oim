
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.observer.GroupBusinessEventObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupBusinessService extends AbstractMaterial {

	public GroupBusinessService(AppContext appContext) {
		super(appContext);
	}

	public void disband(String groupId, String ownerUserId) {
		GroupBusinessEventObservable o = this.appContext.getObject(GroupBusinessEventObservable.class);
		o.execute((l) -> {
			l.disband(groupId, ownerUserId);
		});
	}

	/**
	 * 
	 * 推送退出群(通知管理员和群主)<br>
	 * Date 2020-04-11 13:43:17<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	public void quit(String groupId, String userId) {
		GroupBusinessEventObservable o = this.appContext.getObject(GroupBusinessEventObservable.class);
		o.execute((l) -> {
			l.quit(groupId, userId);
			;
		});
	}
}
