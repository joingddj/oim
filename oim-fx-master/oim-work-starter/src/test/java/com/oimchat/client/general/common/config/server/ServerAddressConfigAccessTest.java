
package com.oimchat.client.general.common.config.server;

import com.oimchat.client.platform.common.config.server.ServerAddressConfigAccess;
import com.oimchat.client.platform.common.config.server.data.ServerAddressConfig;
import com.onlyxiahui.common.lib.util.json.JsonUtil;

/**
 * Description <br>
 * Date 2021-03-10 17:40:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerAddressConfigAccessTest {

	public static void main(String[] args) {
		ServerAddressConfigAccess sa = new ServerAddressConfigAccess();
		ServerAddressConfig data = sa.get();

		String json = JsonUtil.toJson(data);
		System.out.println(json);

		data.setAddress("http://127.0.0.1:10000");

		sa.save(data);

		data = sa.get();

		json = JsonUtil.toJson(data);
		System.out.println(json);
	}
}
