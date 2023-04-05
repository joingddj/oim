
package com.oimchat.client.general.basic.message.builder;

import com.oimchat.client.general.basic.message.impl.HeadImpl;
import com.onlyxiahui.common.message.request.RequestBodyMessage;
import com.onlyxiahui.common.message.request.RequestMessage;

/**
 * Description <br>
 * Date 2021-03-12 10:01:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageBuilder {

	public static RequestMessage request(String action, String method) {
		HeadImpl head = HeadBuilder.build(action, method);
		RequestMessage r = new RequestMessage();
		r.setHead(head);
		return r;
	}

	public static <T> RequestBodyMessage<T> request(String action, String method, T body) {
		HeadImpl head = HeadBuilder.build(action, method);
		RequestBodyMessage<T> r = new RequestBodyMessage<>();
		r.setHead(head);
		r.setBody(body);
		return r;
	}
}
