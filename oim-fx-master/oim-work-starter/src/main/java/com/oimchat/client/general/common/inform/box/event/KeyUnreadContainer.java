
package com.oimchat.client.general.common.inform.box.event;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description <br>
 * Date 2021-03-26 15:25:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class KeyUnreadContainer<K> {

	Set<KeyUnreadEvent<K>> keySet = new CopyOnWriteArraySet<>();
	Set<AllUnreadEvent> allSet = new CopyOnWriteArraySet<>();

	public void add(KeyUnreadEvent<K> e) {
		if (null != e && !keySet.contains(e)) {
			keySet.add(e);
		}
	}

	public void event(K key, long count) {
		for (KeyUnreadEvent<K> e : keySet) {
			e.event(key, count);
		}
	}

	public void add(AllUnreadEvent e) {
		if (null != e && !allSet.contains(e)) {
			allSet.add(e);
		}
	}

	public void event(long count) {
		for (AllUnreadEvent e : allSet) {
			e.event(count);
		}
	}
}
