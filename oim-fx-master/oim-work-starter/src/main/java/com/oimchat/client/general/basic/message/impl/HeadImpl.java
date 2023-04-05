
package com.oimchat.client.general.basic.message.impl;

import com.onlyxiahui.common.message.node.Head;

/**
 * Description <br>
 * Date 2021-03-12 11:13:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HeadImpl implements Head {
	/**
	 * 消息的id，标识消息的唯一性
	 */
	public String key;
	/**
	 * 请求接口名称
	 */
	public String name;
	/**
	 * 请求动作类型
	 */
	public String action;
	/**
	 * 请求方法
	 */
	public String method;
	/**
	 * 请求接口版本
	 */
	public String version = "1";
	/**
	 * 响应时间
	 */
	public long timestamp;

	@Override
	public void setKey(String key) {
		this.key = key;

	}

	@Override
	public String getKey() {
		return this.key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
