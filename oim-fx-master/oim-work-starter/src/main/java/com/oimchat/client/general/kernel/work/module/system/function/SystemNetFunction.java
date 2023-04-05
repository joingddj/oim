
package com.oimchat.client.general.kernel.work.module.system.function;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.InfoBackInvoker;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.common.constant.ServerConstant;
import com.oimchat.client.general.kernel.work.module.base.box.AppInfoBox;
import com.oimchat.client.general.kernel.work.module.server.box.ServerBox;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.oimchat.client.general.kernel.work.module.system.sender.SystemAuthSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.aware.basic.work.type.net.Protocol;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.framework.net.handler.connect.action.ConnectBackAction;
import com.onlyxiahui.framework.net.handler.connect.data.ConnectData;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;
import com.onlyxiahui.wofa.client.work.core.WorkContext;

/**
 * Description <br>
 * Date 2021-03-13 20:12:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class SystemNetFunction extends AbstractMaterial {

	public SystemNetFunction(AppContext appContext) {
		super(appContext);
	}

	public void connect(InfoBack back) {
		ServerBox box = this.appContext.getObject(ServerBox.class);

		if (box.hasAddress(ServerConstant.ServerType.main.value(), Protocol.WS.value())) {
			ServerAddressData addressData = box.getAddress(ServerConstant.ServerType.main.value(), Protocol.WS.value());
//		if (box.hasAddress(ServerConstant.ServerType.main.value(), Protocol.TCP.value())) {
//			ServerAddressData addressData = box.getAddress(ServerConstant.ServerType.main.value(), Protocol.TCP.value());

			String address = addressData.getAddress();
			int port = addressData.getPort();
			ConnectData connectData = new ConnectData();
			connectData.setAddress(address);
			connectData.setPort(port);
			connect(connectData, (s) -> {
				Info info = new Info();
				if (!s) {
					info.addWarning("4.002", "连接失败，请检查网络是否正常!");
				}
				back.back(info);
			});
		} else {
			Info info = new Info();
			info.addWarning("4.001", "没有可用服务器！");
			back.back(info);
		}
	}

	public void connect(ConnectData connectData, ConnectBackAction back) {
		WorkContext netContext = appContext.getObject(WorkContext.class);
		if (!netContext.getNetHandler().getConnectHandler().isConnected()) {
			// 因为负责连接服务器的和负责发送消息的线程不同，在执行登录之前是没有连接的，所以在这里先添加个连接后回掉的action
			// 当连接成功后再把登陆消息发出去，不然先把消息发了，再连接就没有执行登陆操作了
			netContext.connect(connectData, back);
		} else {
			back.connectBack(true);
		}
	}

	public void auth(String token, InfoBack ib) {
		SystemAuthSender sender = this.appContext.getObject(SystemAuthSender.class);
		InfoBackActionImpl back = new InfoBackActionImpl(ib) {

			@Back
			public void back(@Define("info") Info info) {
				InfoBackInvoker.back(ib, info);
			}
		};
		AppInfoBox box = this.appContext.getObject(AppInfoBox.class);
		Client client = box.getClient();
		sender.auth(token, client, back);
	}
}
