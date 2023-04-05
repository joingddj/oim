
package com.oimchat.client.general.kernel.work.module.personal.service;

import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalEventObservable;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalObservable;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalStatusObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 10:46:06<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalService extends AbstractMaterial {

	public PersonalService(AppContext appContext) {
		super(appContext);
	}

	public void loadPersonal() {
		PersonalHandler ph = this.appContext.getObject(PersonalHandler.class);
		PersonalObservable pl = appContext.getObject(PersonalObservable.class);
		ph.get((info, u) -> {
			if (info.isSuccess()) {
				pl.execute((l) -> {
					l.update(u);
				});
			}
		});
	}

	public void updateStatus(String status) {
		PersonalStatusObservable psl = appContext.getObject(PersonalStatusObservable.class);
		psl.execute((l) -> {
			l.update(status);
		});
	}

	public void otherOnline(Client client, boolean offline) {
		PersonalEventObservable l = appContext.getObject(PersonalEventObservable.class);
		l.execute((e) -> {
			e.otherOnline(client, offline);
		});
	}

	public void updatePassword() {
		PersonalEventObservable l = appContext.getObject(PersonalEventObservable.class);
		l.execute((e) -> {
			e.updatePassword();
		});
	}
}
