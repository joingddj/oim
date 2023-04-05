
package com.onlyxiahui.aware.basic.work.type.net;

/**
 * Description <br>
 * Date 2020-05-15 18:32:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum AddressType {

	IPv4("IPv4"), IPv6("IPv6"), URL("URL");

	private String value;

	private AddressType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public boolean isEquals(String addressType) {
		return value().equals(addressType);
	}
}
