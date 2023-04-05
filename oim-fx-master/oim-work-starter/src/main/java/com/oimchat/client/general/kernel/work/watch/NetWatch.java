
package com.oimchat.client.general.kernel.work.watch;

import com.oimchat.client.general.kernel.work.module.index.controller.LoginController;
import com.oimchat.client.general.kernel.work.module.system.handler.HeartbeatHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.framework.net.handler.connect.action.ConnectStatusAction;
import com.onlyxiahui.framework.net.handler.connect.type.IdleStatus;
import com.onlyxiahui.wofa.client.work.core.WorkContext;

/**
 * Description <br>
 * Date 2021-03-17 09:06:01<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class NetWatch extends AbstractMaterial {

	public NetWatch(AppContext appContext) {
		super(appContext);
		init();
	}

	private void init() {
		WorkContext netContext = appContext.getObject(WorkContext.class);
		netContext.getNetHandler().addConnectStatusAction(new ConnectStatusAction() {

			@Override
			public void onStatusChange(boolean connected) {
				if (connected) {
					LoginController lc = appContext.getObject(LoginController.class);
					lc.reauth((info) -> {
					});
				}
			}

			@Override
			public void onAfterAutoConnect(boolean connected) {
				if (!connected) {
					LoginController lc = appContext.getObject(LoginController.class);
					lc.relogin();
				}
			}

			@Override
			public void onIdle(IdleStatus idleStatus) {
				HeartbeatHandler hh = appContext.getObject(HeartbeatHandler.class);
				hh.heartbeat();
			}
		});
	}
}
