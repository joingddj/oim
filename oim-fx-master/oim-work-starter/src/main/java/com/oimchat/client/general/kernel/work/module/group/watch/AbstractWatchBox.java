
package com.oimchat.client.general.kernel.work.module.group.watch;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-17 11:50:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractWatchBox<T> {

	final List<T> list = new ArrayList<>();

	public void addWatch(T watch) {
		if (null != watch && !list.contains(watch)) {
			list.add(watch);
		}
	}

	public List<T> watches() {
		return list;
	}
}
