
package com.oimchat.client.general.kernel.work.module.contact.service;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddResultData;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactEventObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactService extends AbstractMaterial {

	public ContactService(AppContext appContext) {
		super(appContext);
	}

	public void onReceiveApplyInfo(String applyId) {
		ContactEventObservable o = this.appContext.getObject(ContactEventObservable.class);
		ContactHandler handler = this.appContext.getObject(ContactHandler.class);
		handler.getApplyCaseById(applyId, (info, data) -> {
			o.execute((l) -> {
				l.onReceiveApplyInfo(data);
			});
		});
	}

	public void onReceiveApplyResult(ContactAddResultData data) {
		ContactEventObservable o = this.appContext.getObject(ContactEventObservable.class);
		o.execute((l) -> {
			l.onReceiveApplyResult(data);
		});
	}

	public void updateBlocked(String contactUserId, String isBlocked) {
		ContactEventObservable o = this.appContext.getObject(ContactEventObservable.class);
		o.execute((l) -> {
			l.updateBlocked(contactUserId, isBlocked);
		});
	}
}
