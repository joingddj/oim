
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.listener.ChangeListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 17:44:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseChangeObservable<T> extends AbstractObservable<ChangeListener<T>> {

	public BaseChangeObservable(AppContext appContext) {
		super(appContext);
	}

	public void execute(T newValue, T oldValue) {
		super.execute((l) -> {
			l.change(newValue, oldValue);
		});
	}
}
