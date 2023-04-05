
package com.oimchat.client.general.kernel.work.module.personal.observer;

import com.oimchat.client.general.kernel.work.common.observer.AbstractObservable;
import com.oimchat.client.general.kernel.work.module.personal.observer.listener.PersonalEventListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 15:38:07<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalEventObservable extends AbstractObservable<PersonalEventListener> {

	public PersonalEventObservable(AppContext appContext) {
		super(appContext);
	}
}
