
package com.oimchat.client.general.kernel.work.module.common.util;

import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.onlyxiahui.aware.basic.work.type.net.AddressType;
import com.onlyxiahui.aware.basic.work.type.net.Protocol;

/**
 * Description <br>
 * Date 2021-03-12 10:28:12<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerAddressUtil {

	public static String convertHttpUrl(ServerAddressData address) {
		String url = "";
		if (null != address) {
			if (Protocol.HTTP.isEquals(address.getProtocol())) {
				if (AddressType.URL.isEquals(address.getAddressType())) {
					url = address.getAddress();
				} else if (AddressType.IPv4.isEquals(address.getAddressType())) {
					url = "http://" + address.getAddress() + ":" + address.getPort();
				} else if (AddressType.IPv6.isEquals(address.getAddressType())) {
					url = "http://[" + address.getAddress() + "]:" + address.getPort();
				}
			}
			if (Protocol.HTTPS.isEquals(address.getProtocol())) {
				if (AddressType.URL.isEquals(address.getAddressType())) {
					url = address.getAddress();
				} else if (AddressType.IPv4.isEquals(address.getAddressType())) {
					url = "https://" + address.getAddress() + ":" + address.getPort();
				} else if (AddressType.IPv6.isEquals(address.getAddressType())) {
					url = "https://[" + address.getAddress() + "]:" + address.getPort();
				}
			}
		}
		return url;
	}
}
