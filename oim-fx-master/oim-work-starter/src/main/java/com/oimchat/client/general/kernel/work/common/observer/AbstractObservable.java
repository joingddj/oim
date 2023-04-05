
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.ListenerBox.Put;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 16:50:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractObservable<T> extends AbstractMaterial {

	ListenerBox<T> box = new ListenerBox<>();

	public AbstractObservable(AppContext appContext) {
		super(appContext);
	}

	public void addListener(T e) {
		box.add(e);
	}

	public void execute(Put<T> p) {
		box.execute(p);
	}
}
