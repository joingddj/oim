
package com.oimchat.client.general.common.inform.box;

import com.oimchat.client.general.common.inform.box.event.AllUnreadContainer;
import com.oimchat.client.general.common.inform.box.event.AllUnreadEvent;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-26 15:05:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AllUnreadBox extends AbstractMaterial {

	private long totalUnreadCount = 0;
	private AllUnreadContainer listener = new AllUnreadContainer();

	public AllUnreadBox(AppContext appContext) {
		super(appContext);
	}

	public synchronized long plusUnread(long count) {
		if (count >= 0) {
			this.totalUnreadCount = this.totalUnreadCount + count;
		} else {
			this.plusTotalUnread();
		}
		this.handleEvent();
		return this.totalUnreadCount;
	}

	public synchronized long minusUnread(long count) {
		if (count >= 0) {
			this.totalUnreadCount = this.totalUnreadCount - count;
		} else {
			this.minusTotalUnread();
		}
		if (this.totalUnreadCount < 0) {
			this.totalUnreadCount = 0;
		}
		this.handleEvent();
		return this.totalUnreadCount;
	}

	public synchronized void setUnreadCount(long count) {
		if (count < 0) {
			count = 0;
		}
		if (count >= 0) {
			this.totalUnreadCount = count;
		}
		this.handleEvent();
	}

	public long getTotalUnreadCount() {
		return this.totalUnreadCount;
	}

	private synchronized void plusTotalUnread() {
		this.totalUnreadCount++;
	}

	private synchronized void minusTotalUnread() {
		this.totalUnreadCount--;
	}

	private void handleEvent() {
		listener.event(getTotalUnreadCount());
	}

	public void add(AllUnreadEvent e) {
		listener.add(e);
	}
}
