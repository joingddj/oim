
package com.oimchat.client.platform.kernel.work.common.server;

import com.oimchat.client.general.kernel.work.module.server.call.ServerCall;
import com.oimchat.client.platform.common.config.server.ServerAddressConfigAccess;
import com.oimchat.client.platform.common.config.server.data.ServerAddressConfig;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-12 11:34:11<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerAddressConfigManager extends AbstractMaterial {

	public ServerAddressConfigManager(AppContext appContext) {
		super(appContext);
	}

	public String getAddress() {
		ServerAddressConfigAccess sa = new ServerAddressConfigAccess();
		ServerAddressConfig data = sa.get();
		return data.getAddress();
	}

	public void save(String address) {
		ServerAddressConfigAccess sa = new ServerAddressConfigAccess();
		ServerAddressConfig data = sa.get();
		data.setAddress(address);
		sa.save(data);

		ServerCall call = this.appContext.getObject(ServerCall.class);
		call.setAddress(address);
	}

	public void load() {
		String address = getAddress();
		ServerCall call = this.appContext.getObject(ServerCall.class);
		call.setAddress(address);
	}
}
