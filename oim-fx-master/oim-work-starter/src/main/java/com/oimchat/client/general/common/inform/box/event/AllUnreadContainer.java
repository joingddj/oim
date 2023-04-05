
package com.oimchat.client.general.common.inform.box.event;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description <br>
 * Date 2021-03-26 15:06:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AllUnreadContainer {

	Set<AllUnreadEvent> set = new CopyOnWriteArraySet<>();

	public void add(AllUnreadEvent e) {
		if (null != e && !set.contains(e)) {
			set.add(e);
		}
	}

	public void event(long count) {
		for (AllUnreadEvent e : set) {
			e.event(count);
		}
	}
}
