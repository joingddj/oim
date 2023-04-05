
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.listener.ExecuteListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 17:44:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseExecuteObservable extends AbstractObservable<ExecuteListener> {

	public BaseExecuteObservable(AppContext appContext) {
		super(appContext);
	}

	public void execute() {
		super.execute((l) -> {
			l.execute();
		});
	}
}
