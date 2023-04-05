
package com.oimchat.client.general.kernel.work.module.im.box;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.kernel.work.common.cleaner.AbstractWorkCleaner;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-26 14:56:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseSendContentBox extends AbstractWorkCleaner {

	Map<String, Map<String, Content>> sendContentMap = new ConcurrentHashMap<>();

	public BaseSendContentBox(AppContext appContext) {
		super(appContext);
	}

	public void createSend(String key) {
		Map<String, Content> map = sendContentMap.get(key);
		if (null == map) {
			map = new ConcurrentHashMap<>();
			sendContentMap.put(key, map);
		}
	}

	public boolean putSend(String key, Content content) {
		boolean mark = false;
		if (null != content) {
			Map<String, Content> map = sendContentMap.get(key);
			if (null != map) {
				String messageKey = content.getKey();
				map.put(messageKey, content);
				mark = true;
			}
		}
		return mark;
	}

	public void removeSend(String key, String messageKey) {
		Map<String, Content> map = sendContentMap.get(key);
		if (null != map) {
			map.remove(messageKey);
		}
	}

	public Content getSend(String key, String messageKey) {
		Content content = null;
		Map<String, Content> map = sendContentMap.get(key);
		if (null != map) {
			content = map.get(messageKey);
		}
		return content;
	}

	public void clearSend(String key) {
		sendContentMap.remove(key);
	}

	public void clear() {
		sendContentMap.clear();
	}
}
