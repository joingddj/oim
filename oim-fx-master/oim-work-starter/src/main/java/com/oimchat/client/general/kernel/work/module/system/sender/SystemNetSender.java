
package com.oimchat.client.general.kernel.work.module.system.sender;

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
@ActionMapping(value = "1.0.001")
public interface SystemNetSender {

	/**
	 * 
	 * 心跳<br>
	 * Date 2021-03-13 20:07:55<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void heartbeat(DataBackAction back);
}
