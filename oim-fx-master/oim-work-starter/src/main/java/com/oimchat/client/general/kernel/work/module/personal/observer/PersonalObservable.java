
package com.oimchat.client.general.kernel.work.module.personal.observer;

import com.oimchat.client.general.kernel.work.common.observer.AbstractObservable;
import com.oimchat.client.general.kernel.work.common.observer.listener.UpdateListener;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 10:46:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalObservable extends AbstractObservable<UpdateListener<User>> {

	public PersonalObservable(AppContext appContext) {
		super(appContext);
	}
}
