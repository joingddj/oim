
package com.oimchat.client.general.kernel.work.module.system.sender;

import com.oimchat.client.general.basic.message.data.Client;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * Description <br>
 * Date 2021-03-13 19:59:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Sender
@ActionMapping(value = "1.0.002")
public interface SystemAuthSender {

	/**
	 * 
	 * 认证<br>
	 * Date 2021-03-13 20:08:12<br>
	 * 
	 * @param token
	 * @param client
	 * @param back
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void auth(@Define("body.token") String token, @Define("body.client") Client client, DataBackAction back);
}
