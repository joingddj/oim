
package com.oimchat.client.platform.fx.run.watch.contact;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddResultData;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactEventObservable;
import com.oimchat.client.general.kernel.work.module.contact.observer.listener.ContactEventListener;
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

public class ContactWatch extends AbstractWatch {

	public ContactWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		ContactEventObservable o = this.appContext.getObject(ContactEventObservable.class);
		o.addListener(new ContactEventListener() {

			@Override
			public void updateBlocked(String contactUserId, String isBlocked) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onReceiveApplyResult(ContactAddResultData data) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onReceiveApplyInfo(ContactAddApplyEntityCase apply) {
				receiveApplyInfo(apply);
			}
		});
	}

	private void receiveApplyInfo(ContactAddApplyEntityCase apply) {
		GeneralApplyInformationConverge is = this.appContext.getObject(GeneralApplyInformationConverge.class);
		is.refreshContactApplyNotice();
	}
}
