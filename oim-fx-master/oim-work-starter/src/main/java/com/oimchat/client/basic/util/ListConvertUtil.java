
package com.oimchat.client.basic.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-16 15:48:37<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ListConvertUtil {

	public static <R, T> List<R> convert(List<T> list, Convert<R, T> c) {
		List<R> items = new ArrayList<>();
		if (null != list && null != c) {
			list.forEach((i) -> {
				R r = c.get(i);
				items.add(r);
			});
		}
		return items;
	}

	public interface Convert<R, T> {

		R get(T t);
	}
}
