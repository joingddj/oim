
package com.oimchat.client.common.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-15 14:32:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class DataUpdateObservable<T> {

	List<DataUpdateAction<T>> list = new ArrayList<>();

	public void add(DataUpdateAction<T> ea) {
		if (null != ea && !list.contains(ea)) {
			list.add(ea);
		}
	}

	public void execute(T v) {
		for (DataUpdateAction<T> ea : list) {
			ea.value(v);
		}
	}
}
