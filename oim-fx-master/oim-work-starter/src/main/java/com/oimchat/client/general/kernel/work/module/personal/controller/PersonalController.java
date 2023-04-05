
package com.oimchat.client.general.kernel.work.module.personal.controller;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.general.kernel.work.module.index.store.LoginUserStore;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-11 17:01:25<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalController extends AbstractMaterial {

	public PersonalController(AppContext appContext) {
		super(appContext);
	}

	public void updateStatus(
			String status, InfoBack back) {
		PersonalHandler ph = this.appContext.getObject(PersonalHandler.class);
		ph.updateStatus(status, back);
	}

	public void updateStatus() {
		LoginUserStore lus = appContext.getObject(LoginUserStore.class);
		if (null != lus.getLoginUser()) {
			String status = lus.getLoginUser().getStatus();
			updateStatus(status, (info) -> {

			});
		}
	}
}
