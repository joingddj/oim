
package com.oimchat.client.common.asyn;

import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-12 14:35:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ValueBackInvoker {

	public static <T> void back(ValueBack<T> ib, Info info, T value) {
		if (null != ib) {
			ib.back(info, value);
		}
	}
}
