
package com.oimchat.client.platform.common.inform.box;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.common.event.ExecuteAction;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.platform.common.inform.data.PromptData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 22:13:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PromptDataBox extends AbstractMaterial {

	private Map<String, PromptData> map = new ConcurrentHashMap<String, PromptData>();

	public PromptDataBox(AppContext appContext) {
		super(appContext);
	}

	public PromptData getPromptData() {
		PromptData pd = null;
		for (PromptData p : map.values()) {
			pd = p;
			break;
		}
		return pd;
	}

	public PromptData getPromptData(String key) {
		return map.get(key);
	}

	public void remove(String key) {
		map.remove(key);
	}

	public void put(String key, PromptData promptData) {
		map.put(key, promptData);
	}

	public void put(String key, URL iconUrl, ExecuteAction callAction) {
		put(key, new PromptData(key, iconUrl, callAction));
	}

	/**
	 * 如果提示信息由执行操作，当点击头像或者托盘时，会执行其操作。
	 * 
	 * @Author: XiaHui
	 * @Date: 2016年2月16日
	 * @ModifyUser: XiaHui
	 * @ModifyDate: 2016年2月16日
	 * @param key
	 */
	public void execute(String key) {
		PromptData promptData = map.remove(key);
		if (null != promptData) {
			final ExecuteAction ca = promptData.getExecuteAction();
			if (null != ca) {
				RunExecutor run = this.appContext.getObject(RunExecutor.class);
				run.execute(() -> {
					try {
						ca.execute();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
	}
}
