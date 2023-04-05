
package com.oimchat.client.platform.fx.module.chat.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.app.fx.base.component.chat.MessageReadMapper;
import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.common.sync.SyncGet;
import com.oimchat.client.platform.fx.module.chat.wrap.ChatListStageWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-22 14:43:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseChatPaneStore<T> extends AbstractMaterial {

	Map<String, ChatPane> paneMap = new ConcurrentHashMap<>();

	public BaseChatPaneStore(AppContext appContext) {
		super(appContext);
	}

	private void removeByKey(String key, T data) {
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		Info info = checkRemove(data);
		if (null != info && info.isSuccess()) {
			stageWrap.removeKey(key);
			paneMap.remove(key);
			onRemove(data);
		}
	}

	private void selectByKey(String key, T data) {
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			stageWrap.setMainPnae(cp);
		}
		onSelect(data);
	}

	protected ChatPane buildChatPane(T data) {
		ChatPane cp = SyncGet.<ChatPane>syncGet((p) -> {
			FxUtil.invoke(() -> {
				p.put(new ChatPane());
			});
		});
		// ChatPane cp = new ChatPane();
		String key = getKey(data);
		paneMap.put(key, cp);
		initialize(cp, data);
		setInfo(cp, data);
		return cp;
	}

	protected HeadCloseItem buildItem(T data) {
		String key = getKey(data);
		HeadCloseItem head = new HeadCloseItem();
		head.setOnCloseAction(a -> {
			removeByKey(key, data);
		});
		head.selectedProperty().addListener((o, ov, nv) -> {
			selectByKey(key, data);
		});
		initialize(head, data);
		setInfo(head, data);
		head.setKey(key);
		return head;
	}

	public String getKey(String id) {
		String type = type();
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append("_");
		sb.append(id);
		return sb.toString();
	}

	public abstract String type();

	public abstract String getKey(T data);

	protected abstract Info checkRemove(T data);

	protected abstract void onRemove(T data);

	protected abstract void onSelect(T data);

	protected abstract void initialize(ChatPane cp, T data);

	protected abstract void setInfo(ChatPane cp, T data);

	protected abstract void initialize(HeadCloseItem item, T data);

	protected abstract void setInfo(HeadCloseItem item, T data);

	public void insertBefore(String id, String json) {
		String key = getKey(id);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			MessageReadMapper readMapper = cp.getAreaPane().getReadPane().getMapper();
			FxUtil.invoke(() -> {
				readMapper.insertBefore(json);
			});
		}
	}

	public void insertLast(String id, String json) {
		String key = getKey(id);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			MessageReadMapper readMapper = cp.getAreaPane().getReadPane().getMapper();
			FxUtil.invoke(() -> {
				readMapper.insertLast(json);
			});
		}
	}

	public void clear(String id) {
		String key = getKey(id);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			MessageReadMapper readMapper = cp.getAreaPane().getReadPane().getMapper();
			FxUtil.invoke(() -> {
				readMapper.clear();
			});
		}
	}

	public void insertAt(String id, String json) {
		String key = getKey(id);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			MessageReadMapper readMapper = cp.getAreaPane().getReadPane().getMapper();
			FxUtil.invoke(() -> {
				readMapper.insertAt(json);
			});
		}
	}

	public void insertChatPrompt(String id, String json) {
		String key = getKey(id);
		ChatPane cp = paneMap.get(key);
		if (null != cp) {
			MessageReadMapper readMapper = cp.getAreaPane().getReadPane().getMapper();
			FxUtil.invoke(() -> {
				readMapper.insertChatPrompt(json);
			});
		}
	}

	public void updateItemInfo(String id, String text, String time) {
		String key = getKey(id);
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		HeadCloseItem item = stageWrap.getItem(key);
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setShowText(null == text ? "" : text);
				item.setTime(null == time ? "" : time);
			});
		}
	}

	public void updateItemRed(String id, boolean red, String count) {
		String key = getKey(id);
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		HeadCloseItem item = stageWrap.getItem(key);
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setRed(red);
				item.setRedText(count);
			});
		}
	}

	public boolean isChatShowing(String id) {
		String key = getKey(id);
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		boolean isShowing = stageWrap.isShowing();
		boolean isSelected = stageWrap.isSelected(key);
		return isShowing && isSelected;
	}

	public void showChat(T data) {
		String key = getKey(data);
		ChatPane cp = paneMap.get(key);
		ChatListStageWrap stageWrap = this.appContext.getObject(ChatListStageWrap.class);
		if (null == cp) {
			cp = buildChatPane(data);
		} else {
			setInfo(cp, data);
		}
		HeadCloseItem item = stageWrap.getItem(key);
		if (null == item) {
			item = buildItem(data);
			stageWrap.addItem(item);
		}
		setInfo(cp, data);
		setInfo(item, data);
		stageWrap.selected(key);
	}
}
