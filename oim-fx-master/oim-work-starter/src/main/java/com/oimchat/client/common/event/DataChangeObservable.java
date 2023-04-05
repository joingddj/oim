
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

public class DataChangeObservable<T> {

	List<DataChangeAction<T>> list = new ArrayList<>();

	public void add(DataChangeAction<T> ea) {
		if (null != ea && !list.contains(ea)) {
			list.add(ea);
		}
	}

	public void executeAdd(T v) {
		for (DataChangeAction<T> ea : list) {
			ea.add(v);
		}
	}

	public void executeUpdate(T v) {
		for (DataChangeAction<T> ea : list) {
			ea.update(v);
		}
	}

	public void executeDelete(T v) {
		for (DataChangeAction<T> ea : list) {
			ea.delete(v);
		}
	}
}
