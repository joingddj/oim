
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

public class ValueObservable<T> {

	List<ValueAction<T>> list = new ArrayList<>();

	public void add(ValueAction<T> ea) {
		if (null != ea && !list.contains(ea)) {
			list.add(ea);
		}
	}

	public void execute(T v) {
		for (ValueAction<T> ea : list) {
			ea.value(v);
		}
	}
}
