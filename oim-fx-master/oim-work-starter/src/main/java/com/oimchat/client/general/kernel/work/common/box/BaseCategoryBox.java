
package com.oimchat.client.general.kernel.work.common.box;

import java.util.ArrayList;
import java.util.HashMap;
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

public abstract class BaseCategoryBox<T> extends AbstractWorkCleaner {

	/**** <categoryKey,<itemKey,item>> ****/
	private final Map<String, Map<String, T>> categoryItemMultipleMap = new ConcurrentHashMap<>();

	/**** <itemKey,<categoryKey,item>> ****/
	private final Map<String, Map<String, T>> itemCategoryMultipleMap = new ConcurrentHashMap<>();

	public BaseCategoryBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void clear() {
		categoryItemMultipleMap.clear();
		itemCategoryMultipleMap.clear();
	}

	Map<String, T> getOrCreate(String key, Map<String, Map<String, T>> map) {
		Map<String, T> m = map.get(HereStringUtil.value(key));
		if (null == m) {
			m = new HashMap<>();
			map.put(HereStringUtil.value(key), m);
		}
		return m;
	}

	public void put(String categoryKey, String itemKey, T data) {
		Map<String, T> categoryItemMap = getOrCreate(categoryKey, categoryItemMultipleMap);
		Map<String, T> itemCategoryMap = getOrCreate(itemKey, itemCategoryMultipleMap);

		categoryItemMap.put(HereStringUtil.value(itemKey), data);
		itemCategoryMap.put(HereStringUtil.value(categoryKey), data);
	}

	public T get(String categoryKey, String itemKey) {
		T data = null;
		Map<String, T> map = categoryItemMultipleMap.get(HereStringUtil.value(categoryKey));
		if (null != map) {
			data = map.get(HereStringUtil.value(itemKey));
		}
		return data;
	}

	public List<T> getListByCategoryKey(String categoryKey) {
		List<T> list = new ArrayList<>();
		Map<String, T> map = categoryItemMultipleMap.get(HereStringUtil.value(categoryKey));
		if (null != map) {
			list.addAll(map.values());
		}
		return list;
	}

	public int getSizeByCategoryKey(String categoryKey) {
		int size = 0;
		Map<String, T> map = categoryItemMultipleMap.get(HereStringUtil.value(categoryKey));
		if (null != map) {
			size = (map.values().size());
		}
		return size;
	}

	public List<T> getListByItemKey(String itemKey) {
		List<T> list = new ArrayList<>();
		Map<String, T> map = itemCategoryMultipleMap.get(HereStringUtil.value(itemKey));
		if (null != map) {
			list.addAll(map.values());
		}
		return list;
	}

	public int getSizeByItemKey(String itemKey) {
		int size = 0;
		Map<String, T> map = itemCategoryMultipleMap.get(HereStringUtil.value(itemKey));
		if (null != map) {
			size = (map.values().size());
		}
		return size;
	}

	public List<T> removeCategory(String categoryKey) {
		List<T> list = new ArrayList<>();
		Map<String, T> itemKeyMap = categoryItemMultipleMap.remove(HereStringUtil.value(categoryKey));
		if (null != itemKeyMap) {
			list.addAll(itemKeyMap.values());
			for (String itemKey : itemKeyMap.keySet()) {
				Map<String, T> map = itemCategoryMultipleMap.get(HereStringUtil.value(itemKey));
				if (null != map) {
					map.remove(HereStringUtil.value(categoryKey));
				}
			}
		}
		return list;
	}

	public List<T> removeItem(String itemKey) {
		List<T> list = new ArrayList<>();
		Map<String, T> categoryKeyMap = itemCategoryMultipleMap.remove(HereStringUtil.value(itemKey));
		if (null != categoryKeyMap) {
			list.addAll(categoryKeyMap.values());
			for (String categoryKey : categoryKeyMap.keySet()) {
				Map<String, T> map = categoryItemMultipleMap.get(HereStringUtil.value(categoryKey));
				if (null != map) {
					map.remove(HereStringUtil.value(itemKey));
				}
			}
		}
		return list;
	}

	public T removeItem(String categoryKey, String itemKey) {
		T data = null;
		Map<String, T> itemKeyMap = categoryItemMultipleMap.get(HereStringUtil.value(categoryKey));
		if (null != itemKeyMap) {
			data = itemKeyMap.remove(HereStringUtil.value(itemKey));
		}
		Map<String, T> categoryKeyMap = itemCategoryMultipleMap.get(HereStringUtil.value(itemKey));
		if (null != categoryKeyMap) {
			categoryKeyMap.remove(HereStringUtil.value(categoryKey));
		}
		return data;
	}
}
