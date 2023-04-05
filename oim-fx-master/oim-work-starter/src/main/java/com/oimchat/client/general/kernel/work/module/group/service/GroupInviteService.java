
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.observer.GroupInviteEventObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInviteService extends AbstractMaterial {

	public GroupInviteService(AppContext appContext) {
		super(appContext);
	}

	public void onReceiveInviteApply(String groupId, String applyId) {
		GroupInviteEventObservable o = this.appContext.getObject(GroupInviteEventObservable.class);
		o.execute((l) -> {
			l.onReceiveInviteApply(groupId, applyId);
		});
	}

	public void onReceiveInviteInfo(String groupId, String applyId) {
		GroupInviteEventObservable o = this.appContext.getObject(GroupInviteEventObservable.class);
		o.execute((l) -> {
			l.onReceiveInviteInfo(groupId, applyId);
		});
	}
}
