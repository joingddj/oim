
package com.oimchat.client.general.kernel.work.module.server.box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-11 15:13:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerBox extends AbstractMaterial {

	public ServerBox(AppContext appContext) {
		super(appContext);
	}

	private Map<String, ServerData> map = new HashMap<>();

	public void addList(List<ServerData> list) {
		if (null != list) {
			for (ServerData data : list) {
				this.add(data);
			}
		}
	}

	public void add(ServerData data) {
		if (null != data) {
			this.map.put(data.getCode(), data);
		}
	}

	public ServerData getServer(String code) {
		ServerData data = this.map.get(code);
		return data;
	}

	public List<ServerAddressData> getAddressList(String code) {
		List<ServerAddressData> list = new ArrayList<>();
		ServerData data = this.getServer(code);
		if (null != data) {
			list = data.getAddresses();
		}
		if (null == list) {
			list = new ArrayList<>();
		}
		return list;
	}

	public ServerAddressData getAddress(String code, String protocol) {
		ServerAddressData address = null;
		List<ServerAddressData> list = this.getAddressList(code);
		if (null != list && null != protocol) {
			for (ServerAddressData data : list) {
				if ((protocol.equals(data.getProtocol())) && data.isEnabled()) {
					address = data;
					break;
				}
			}
		}
		return address;
	}

	public boolean hasAddress(String code, String protocol) {
		ServerAddressData address = this.getAddress(code, protocol);
		boolean has = (null != address && address.isEnabled());
		return has;
	}

	public void clear() {
		map.clear();
	}
}
