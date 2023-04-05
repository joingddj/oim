
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.listener.ValueListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 17:44:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseDataObservable<A, U, D> extends AbstractDataObservable<ValueListener<A>, ValueListener<U>, ValueListener<D>> {

	public BaseDataObservable(AppContext appContext) {
		super(appContext);
	}

	public void executeAdd(A data) {
		super.executeAdd((l) -> {
			l.value(data);
		});
	}

	public void executeUpdate(U data) {
		super.executeUpdate((l) -> {
			l.value(data);
		});
	}

	public void executeDelete(D data) {
		super.executeDelete((l) -> {
			l.value(data);
		});
	}
}
