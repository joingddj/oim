
package com.oimchat.client.general.kernel.work.module.index.cell;

import com.oimchat.client.general.basic.message.builder.MessageBuilder;
import com.oimchat.client.general.kernel.work.common.call.BaseCall;
import com.oimchat.client.general.kernel.work.common.call.PathBuilder;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginData;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.request.RequestBodyMessage;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;

/**
 * Description <br>
 * Date 2021-03-11 15:23:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginCall extends BaseCall {

	private String action = "1.1.002";

	public LoginCall(AppContext appContext) {
		super(appContext);
	}

	public void login(LoginData data, DataBackAction back) {
		String method = "1.1.0005";
		String path = PathBuilder.path(action, method);
		RequestBodyMessage<LoginData> rm = MessageBuilder.<LoginData>request(action, method, data);
		this.execute(path, rm, back);
	}
}
