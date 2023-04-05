
package com.oimchat.client.general.kernel.work.common.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-15 14:32:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ListenerBox<T> {

	List<T> list = new ArrayList<>();

	public void add(T ea) {
		if (null != ea && !list.contains(ea)) {
			list.add(ea);
		}
	}

	public void execute(Put<T> p) {
		for (T v : list) {
			p.put(v);
		}
	}

	public interface Put<T> {
		void put(T t);
	}
}
