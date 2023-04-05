package com.oimchat.client.platform.common.inform.data;

import java.net.URL;

import com.oimchat.client.common.event.ExecuteAction;

/**
 * @Author: XiaHui
 * @Date: 2016年1月23日
 * @ModifyUser: XiaHui
 * @ModifyDate: 2016年1月23日
 */
public class PromptData {

	private String key;
	private URL iconUrl;
	private ExecuteAction executeAction;

	public PromptData(String key, URL iconUrl, ExecuteAction executeAction) {
		super();
		this.key = key;
		this.iconUrl = iconUrl;
		this.executeAction = executeAction;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public URL getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(URL iconUrl) {
		this.iconUrl = iconUrl;
	}

	public ExecuteAction getExecuteAction() {
		return executeAction;
	}

	public void setExecuteAction(ExecuteAction executeAction) {
		this.executeAction = executeAction;
	}
}
