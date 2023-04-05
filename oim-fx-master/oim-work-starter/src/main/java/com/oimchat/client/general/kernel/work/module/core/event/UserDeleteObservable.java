
package com.oimchat.client.general.kernel.work.module.core.event;

import com.oimchat.client.general.kernel.work.common.observer.BaseValueObservable;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-15 14:31:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserDeleteObservable extends BaseValueObservable<String> {

	public UserDeleteObservable(AppContext appContext) {
		super(appContext);
	}
}
