
package com.oimchat.client.general.kernel.work.common.call;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.client.basic.common.work.app.SessionEnum;
import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.common.http.HttpClientHandler;
import com.oimchat.client.general.common.constant.ServerConstant;
import com.oimchat.client.general.kernel.work.module.common.store.AuthStore;
import com.oimchat.client.general.kernel.work.module.common.util.ServerAddressUtil;
import com.oimchat.client.general.kernel.work.module.server.box.ServerBox;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.aware.basic.work.type.net.Protocol;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.message.request.AbstractRequestMessage;
import com.onlyxiahui.common.message.result.ResultMessage;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;

/**
 * Description <br>
 * Date 2021-03-10 16:13:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HttpWrap extends AbstractMaterial {

	HttpClientHandler hch = new HttpClientHandler();

	public HttpWrap(AppContext appContext) {
		super(appContext);
	}

	public void execute(String path, AbstractRequestMessage<?> data, DataBackAction dataBackAction) {
		try {
			AuthStore as = appContext.getObject(AuthStore.class);
			String source = as.getUserId();
			String token = as.getToken();
			Map<String, String> headMap = new HashMap<>();
			headMap.put(SessionEnum.source.getName(), source);
			headMap.put(SessionEnum.token.getName(), token);

			ServerBox box = this.appContext.getObject(ServerBox.class);
			if (box.hasAddress(ServerConstant.ServerType.main.value(), Protocol.HTTP.value())) {
				ServerAddressData address = box.getAddress(ServerConstant.ServerType.main.value(), Protocol.HTTP.value());
				String http = ServerAddressUtil.convertHttpUrl(address);
				String url = PathUtil.merge(http, path);
				hch.execute(url, data, headMap, dataBackAction);
			} else if (box.hasAddress(ServerConstant.ServerType.main.value(), Protocol.HTTPS.value())) {
				ServerAddressData address = box.getAddress(ServerConstant.ServerType.main.value(), Protocol.HTTPS.value());
				String http = ServerAddressUtil.convertHttpUrl(address);
				String url = PathUtil.merge(http, path);
				hch.execute(url, data, headMap, dataBackAction);
			} else {
				ResultMessage r = new ResultMessage();
				if (null != data) {
					r.setHead(data.getHead());
				}
				r.addWarning("4.001", "没有可用服务器！");
				String json = JsonUtil.toJson(r);
				hch.back(json, dataBackAction);
			}
		} catch (Exception e) {
			if (null != dataBackAction) {
				dataBackAction.lost();
			}
		}
	}
}
