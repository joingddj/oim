
package com.oimchat.client.general.basic.message.builder;

import com.oimchat.client.general.basic.message.impl.HeadImpl;
import com.onlyxiahui.aware.basic.util.IdUtil;

/**
 * Description <br>
 * Date 2021-03-12 11:12:42<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HeadBuilder {

	public static HeadImpl build(String action, String method) {
		String key = IdUtil.generateId() + "";
		HeadImpl head = new HeadImpl();
		head.setAction(action);
		head.setMethod(method);
		head.setKey(key);
		head.setTimestamp(System.currentTimeMillis());
		return head;
	}
}
