
package com.oimchat.client.general.kernel.work.module.contact.observer;

import com.oimchat.client.general.kernel.work.common.observer.AbstractObservable;
import com.oimchat.client.general.kernel.work.module.contact.observer.listener.ContactEventListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:08:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactEventObservable extends AbstractObservable<ContactEventListener> {

	public ContactEventObservable(AppContext appContext) {
		super(appContext);
	}
}
