
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.listener.UpdateListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 17:44:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseUpdateObservable<T> extends AbstractObservable<UpdateListener<T>> {

	public BaseUpdateObservable(AppContext appContext) {
		super(appContext);
	}

	public void execute(T value) {
		super.execute((l) -> {
			l.update(value);
		});
	}
}
