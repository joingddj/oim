
package com.oimchat.client.general.common.inform.box;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.client.general.common.inform.box.event.AllUnreadEvent;
import com.oimchat.client.general.common.inform.box.event.KeyUnreadContainer;
import com.oimchat.client.general.common.inform.box.event.KeyUnreadEvent;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-26 15:16:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractKeyUnreadBox<K> extends AbstractMaterial {

	private Map<K, Long> map = new HashMap<>();
	private Long totalUnreadCount = 0L;
	private final KeyUnreadContainer<K> listener = new KeyUnreadContainer<>();

	public AbstractKeyUnreadBox(AppContext appContext) {
		super(appContext);
	}

	private synchronized long getOrDefault(Map<K, Long> map, K key, long count) {
		Long size = map.get(key);
		if (null == size) {
			size = count;
		}
		return size;
	}

	public void plusUnread(K key) {
		this.plusUnreadCount(key, 1);
	}

	public synchronized void plusUnreadCount(K key, long unreadCount) {
		if (unreadCount < 0) {
			unreadCount = 0;
		}
		long count = this.getOrDefault(this.map, key, 0); // 获取聊天对象未读消息数量
		long allCount = count + unreadCount;
		this.map.put(key, allCount);
		this.handleEvent(key);
		this.handleAllEvent();

		AllUnreadBox allUnreadBox = this.appContext.getObject(AllUnreadBox.class);
		allUnreadBox.plusUnread(unreadCount);
	}

	public synchronized void setUnreadCount(K key, long count) {
		if (count < 0) {
			count = 0;
		}
		long oldCount = this.getOrDefault(this.map, key, 0);
		this.map.put(key, count);
		long newTotalCount = (this.totalUnreadCount - oldCount) + count;
		if (newTotalCount >= 0) {
			this.totalUnreadCount = newTotalCount;
		}
		this.handleEvent(key);
		this.handleAllEvent();

		AllUnreadBox allUnreadBox = this.appContext.getObject(AllUnreadBox.class);
		allUnreadBox.minusUnread(oldCount);
	}

	public long getUnreadCount(K key) {
		long count = this.getOrDefault(this.map, key, 0);
		return count;
	}

	public long getTotalUnreadCount() {
		return this.totalUnreadCount;
	}

	private void handleAllEvent() {
		listener.event(getTotalUnreadCount());
	}

	private void handleEvent(K key) {
		long unreadCount = this.getUnreadCount(key);
		listener.event(key, unreadCount);
	}

	public void add(KeyUnreadEvent<K> e) {
		listener.add(e);
	}

	public void add(AllUnreadEvent e) {
		listener.add(e);
	}
}
