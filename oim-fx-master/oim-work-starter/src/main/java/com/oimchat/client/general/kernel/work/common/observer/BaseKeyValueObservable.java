
package com.oimchat.client.general.kernel.work.common.observer;

import com.oimchat.client.general.kernel.work.common.observer.listener.KeyValueListener;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 17:44:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseKeyValueObservable<K, V> extends AbstractObservable<KeyValueListener<K, V>> {

	public BaseKeyValueObservable(AppContext appContext) {
		super(appContext);
	}

	public void execute(K key, V value) {
		super.execute((l) -> {
			l.value(key, value);
		});
	}
}
