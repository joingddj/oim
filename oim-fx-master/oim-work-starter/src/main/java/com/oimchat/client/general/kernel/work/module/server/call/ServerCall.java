
package com.oimchat.client.general.kernel.work.module.server.call;

import java.util.HashMap;

import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.common.http.HttpClientHandler;
import com.oimchat.client.general.kernel.work.common.call.BaseCall;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.request.RequestMessage;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;

/**
 * Description <br>
 * Date 2021-03-11 15:23:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerCall extends BaseCall {

	public String address;

	public ServerCall(AppContext appContext) {
		super(appContext);
	}

	public void getAddressList(DataBackAction dataBackAction) {
		HttpClientHandler hch = new HttpClientHandler();
		String path = "/v1/portal/server/address.list";
		hch.execute(PathUtil.merge(address, path), new RequestMessage(), new HashMap<>(), dataBackAction);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
