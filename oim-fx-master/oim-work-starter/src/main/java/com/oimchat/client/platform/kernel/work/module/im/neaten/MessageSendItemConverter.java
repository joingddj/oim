
package com.oimchat.client.platform.kernel.work.module.im.neaten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.Item;
import com.oimchat.client.basic.common.data.im.message.content.Section;
import com.oimchat.client.basic.common.data.im.message.content.item.AtValue;
import com.oimchat.client.basic.common.data.im.message.content.item.AudioValue;
import com.oimchat.client.basic.common.data.im.message.content.item.FaceValue;
import com.oimchat.client.basic.common.data.im.message.content.item.FileValue;
import com.oimchat.client.basic.common.data.im.message.content.item.ImageValue;
import com.oimchat.client.basic.common.data.im.message.content.item.PositionValue;
import com.oimchat.client.basic.common.data.im.message.content.item.VideoValue;
import com.oimchat.client.platform.kernel.work.module.im.manager.FaceManager;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.oim.face.bean.FaceInfo;

/**
 * Description <br>
 * Date 2021-03-22 17:27:37<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageSendItemConverter extends AbstractMaterial {

	Map<String, Class<?>> map = new HashMap<>();

	public MessageSendItemConverter(AppContext appContext) {
		super(appContext);
		init();
	}

	private void init() {
		map.put(Item.type_at, AtValue.class);
		map.put(Item.type_audio, AudioValue.class);
		map.put(Item.type_face, FaceValue.class);
		map.put(Item.type_file, FileValue.class);
		map.put(Item.type_image, ImageValue.class);
		map.put(Item.type_position, PositionValue.class);
		// map.put(Item.type_url, AudioValue.class);
		map.put(Item.type_video, VideoValue.class);
	}

	public void convert(Content content) {
		if (null != content) {
			List<Section> sections = content.getSections();
			if (null != sections) {
				for (Section s : sections) {
					List<Item> items = s.getItems();
					if (null != items) {
						for (Item item : items) {
							convert(item);
						}
					}
				}
			}
		}
	}

	public void convert(Item item) {

		if (null != item) {
			String type = item.getType();
			Object o = item.getValue();
			if (!Item.type_text.equalsIgnoreCase(type)) {
				Class<?> clazz = map.get(type);
				if (null != clazz) {
					if (o instanceof String) {
						String json = (String) o;
						o = JsonUtil.toObject(json, clazz);
					} else if (o instanceof JSONObject) {
						JSONObject jo = (JSONObject) o;
						o = JsonUtil.toObject(jo, clazz);
					} else {
						String json = o.toString();
						if (JsonUtil.maybeJsonObject(json)) {
							o = JsonUtil.toObject(json, clazz);
						}
					}
				}
			}
			item.setValue(o);
		}
	}

	public void handleFaceItem(List<Item> items) {
		FaceManager fm = this.appContext.getObject(FaceManager.class);
		if (null != items) {
			for (Item item : items) {
				if (Item.type_face.equalsIgnoreCase(item.getType())) {
					Object v = item.getValue();
					if (v instanceof FaceValue) {
						FaceValue fv = (FaceValue) v;
						String cid = fv.getCategoryId();
						String key = fv.getKey();
						FaceInfo fi = fm.getFaceInfo(cid, key);
						if (null != fi) {
							// String realPath = fi.getRealPath();
							// String showPath = fi.getShowPath();
							String text = fi.getText();
							double width = fi.getWidth();
							double height = fi.getHeight();

							fv.setText(text);
							fv.setPath("");

							if (width > 0 && height > 0) {
//								fv.setWidth(width);
//								fv.setHeight(height);
							}
						}
					}
				}
			}
		}
	}
}
