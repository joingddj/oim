
package com.oimchat.client.general.kernel.work.module.im.box;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.general.kernel.work.common.cleaner.AbstractWorkCleaner;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 09:29:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseLastTimeBox extends AbstractWorkCleaner {

	Map<String, Long> timeMap = new ConcurrentHashMap<>();

	public BaseLastTimeBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void clear() {
		timeMap.clear();
	}

	public long getAndPut(String key) {
		Long timestamp = timeMap.get(key);
		if (null == timestamp) {
			timestamp = 0L;
		}
		timeMap.put(key, System.currentTimeMillis());
		return timestamp;
	}

	public void remove(String key) {
		timeMap.remove(key);
	}
}
