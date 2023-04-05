
package com.oimchat.client.platform.fx.run.watch.group;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinResultData;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupInviteEventObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupJoinEventObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.listener.GroupInviteEventListener;
import com.oimchat.client.general.kernel.work.module.group.observer.listener.GroupJoinEventListener;
import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.work.inform.converge.GeneralApplyInformationConverge;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 20:09:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupApplyWatch extends AbstractWatch {

	public GroupApplyWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		GroupJoinEventObservable jo = this.appContext.getObject(GroupJoinEventObservable.class);
		jo.addListener(new GroupJoinEventListener() {

			@Override
			public void onReceiveJoinResult(GroupJoinResultData result) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onReceiveJoinApply(String groupId, String applyId) {
				receiveJoinApply();
			}
		});

		GroupInviteEventObservable io = this.appContext.getObject(GroupInviteEventObservable.class);
		io.addListener(new GroupInviteEventListener() {

			@Override
			public void onReceiveInviteInfo(String groupId, String applyId) {
				receiveInviteInfo();
			}

			@Override
			public void onReceiveInviteApply(String groupId, String applyId) {
				receiveInviteApply();
			}
		});
	}

	private void receiveJoinApply() {
		GeneralApplyInformationConverge is = this.appContext.getObject(GeneralApplyInformationConverge.class);
		is.refreshGroupJoinNotice();
	}

	private void receiveInviteInfo() {
		GeneralApplyInformationConverge is = this.appContext.getObject(GeneralApplyInformationConverge.class);
		is.refreshGroupInviteeNotice();
	}

	private void receiveInviteApply() {
		GeneralApplyInformationConverge is = this.appContext.getObject(GeneralApplyInformationConverge.class);
		is.refreshGroupInviteNotice();
	}
}
