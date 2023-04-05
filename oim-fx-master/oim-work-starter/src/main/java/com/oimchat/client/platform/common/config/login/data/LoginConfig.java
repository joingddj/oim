
package com.oimchat.client.platform.common.config.login.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Description <br>
 * Date 2021-03-11 10:34:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginConfig {

	Map<String, LoginSaveData> configMap = new HashMap<>();

	public Map<String, LoginSaveData> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, LoginSaveData> configMap) {
		this.configMap = configMap;
	}

	public void put(String key, LoginSaveData data) {
		configMap.put(key, data);
	}

	public LoginSaveData get(String key) {
		LoginSaveData data = configMap.get(key);
		return data;
	}

	public void remove(String key) {
		configMap.remove(key);
	}
}
