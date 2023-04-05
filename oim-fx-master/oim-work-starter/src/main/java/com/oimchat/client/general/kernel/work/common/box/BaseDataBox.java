
package com.oimchat.client.general.kernel.work.common.box;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.general.common.util.HereStringUtil;
import com.oimchat.client.general.kernel.work.common.cleaner.AbstractWorkCleaner;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-30 10:57:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseDataBox<T> extends AbstractWorkCleaner {

	private final Map<String, T> map = new ConcurrentHashMap<String, T>();

	public BaseDataBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void clear() {
		map.clear();
	}

	public void put(String key, T data) {
		map.put(HereStringUtil.value(key), data);
	}

	public T remove(String key) {
		return map.remove(key);
	}

	public Map<String, T> getMap() {
		return map;
	}

	public int size() {
		return map.size();
	}

	public T get(String key) {
		T ud = map.get(HereStringUtil.value(key));
		return ud;
	}

	public List<T> getList(List<String> keys) {
		List<T> list = new ArrayList<>();
		if (null != keys) {
			keys.forEach((key) -> {
				T ud = map.get(HereStringUtil.value(key));
				if (null != ud) {
					list.add(ud);
				}
			});
		}
		return list;
	}
}
