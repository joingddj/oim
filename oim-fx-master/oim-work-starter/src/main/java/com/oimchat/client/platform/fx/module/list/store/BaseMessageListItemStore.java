
package com.oimchat.client.platform.fx.module.list.store;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.platform.fx.module.list.wrap.MessageListViewWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;

import javafx.scene.image.Image;

/**
 * Description <br>
 * Date 2021-04-01 16:15:35<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseMessageListItemStore<T> extends AbstractMaterial {

	public BaseMessageListItemStore(AppContext appContext) {
		super(appContext);
	}

	public abstract String type();

	public abstract String getKeyByData(T data);

	protected abstract Info checkRemove(T data);

	protected abstract void onRemove(T data);

	protected abstract void onSelect(T data);

	protected abstract void initialize(HeadItem item, T data);

	protected abstract void setInfo(HeadItem item, T data);

	public String getKeyById(String id) {
		String type = type();
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append("_");
		sb.append(id);
		return sb.toString();
	}

	public void setItemTimeInfo(HeadItem item, String time) {
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setTime(null == time ? "" : time);
			});
		}
	}

	public void setItemTextInfo(HeadItem item, String text) {
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setShowText(null == text ? "" : text);
			});
		}
	}

	public void setItemImageInfo(HeadItem item, Image image) {
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setHeadImage(image);
			});
		}
	}

	public void setItemImageInfo(HeadItem item, boolean gray) {
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setGray(gray);
			});
		}
	}

	public void setItemInfo(HeadItem item, String name, Image image, boolean gray) {
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setGray(gray);
			});
		}
	}

	public boolean has(String id) {
		String key = getKeyById(id);
		MessageListViewWrap wrap = this.appContext.getObject(MessageListViewWrap.class);
		return wrap.hasItem(key);
	}

	public void addOrUpdateItem(T data) {
		String key = getKeyByData(data);
		MessageListViewWrap wrap = this.appContext.getObject(MessageListViewWrap.class);
		HeadItem item = wrap.getItem(key);
		if (null == item) {
			item = new HeadItem();
			initialize(item, data);
		}
		this.setInfo(item, data);
		wrap.addItem(key, item);
	}

	public void updateItemRed(String id, boolean red, String count) {
		String key = getKeyById(id);
		MessageListViewWrap wrap = this.appContext.getObject(MessageListViewWrap.class);
		HeadItem item = wrap.getItem(key);
		if (null != item) {
			FxUtil.invoke(() -> {
				item.setRed(red);
				item.setRedText(count);
			});
		}
	}

	public void updateItemTextInfo(String id, String text, String time) {
		String key = getKeyById(id);
		MessageListViewWrap wrap = this.appContext.getObject(MessageListViewWrap.class);
		HeadItem item = wrap.getItem(key);
		setItemTextInfo(item, text);
		setItemTimeInfo(item, time);
	}

	public void updateItemTimeInfo(String id, String time) {
		String key = getKeyById(id);
		MessageListViewWrap wrap = this.appContext.getObject(MessageListViewWrap.class);
		HeadItem item = wrap.getItem(key);
		setItemTimeInfo(item, time);
	}
}
