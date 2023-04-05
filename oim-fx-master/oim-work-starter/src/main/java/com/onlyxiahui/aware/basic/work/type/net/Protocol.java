
package com.onlyxiahui.aware.basic.work.type.net;

/**
 * Description <br>
 * Date 2020-05-15 18:32:18<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum Protocol {

	TCP("tcp"),
	UDP("udp"),
	HTTP("http"),
	HTTPS("https"),
	WS("ws"),
	WSS("wss"),
	FTP("ftp");

	private String value;

	private Protocol(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public boolean isEquals(String protocol) {
		return value().equals(protocol);
	}
}
