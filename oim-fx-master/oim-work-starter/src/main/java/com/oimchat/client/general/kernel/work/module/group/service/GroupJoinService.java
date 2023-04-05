
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinResultData;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupJoinEventObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupJoinService extends AbstractMaterial {

	public GroupJoinService(AppContext appContext) {
		super(appContext);
	}

	public void onReceiveJoinApply(String groupId, String applyId) {
		GroupJoinEventObservable o = this.appContext.getObject(GroupJoinEventObservable.class);
		o.execute((l) -> {
			l.onReceiveJoinApply(groupId, applyId);
		});
	}

	public void onReceiveJoinResult(GroupJoinResultData result) {
		GroupJoinEventObservable o = this.appContext.getObject(GroupJoinEventObservable.class);
		o.execute((l) -> {
			l.onReceiveJoinResult(result);
		});
	}
}
