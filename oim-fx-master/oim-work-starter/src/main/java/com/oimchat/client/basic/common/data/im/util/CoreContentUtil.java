
package com.oimchat.client.basic.common.data.im.util;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.Item;
import com.oimchat.client.basic.common.data.im.message.content.Section;
import com.oimchat.client.basic.common.data.im.message.content.item.AtValue;
import com.oimchat.client.basic.common.data.im.message.content.item.FileValue;

/**
 * Description <br>
 * Date 2021-03-22 17:48:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class CoreContentUtil {

	public static List<Item> getItemList(Content content, List<String> types) {
		List<Item> list = new ArrayList<Item>();
		if (null != content) {
			List<Section> sections = content.getSections();
			if (null != sections) {
				for (Section s : sections) {
					List<Item> items = s.getItems();
					if (null != items) {
						for (Item i : items) {
							if (types.contains(i.getType())) {
								list.add(i);
							}
						}
					}
				}
			}
		}
		return list;
	}

	public static List<Item> getItemList(Content content, String... types) {
		List<String> typeList = new ArrayList<>();
		if (null != types) {
			for (String t : types) {
				typeList.add(t);
			}
		}
		return getItemList(content, typeList);
	}

	public static List<Item> getItemList(Content content, String type) {
		List<String> typeList = new ArrayList<>();
		if (null != type) {
			typeList.add(type);
		}
		return getItemList(content, typeList);
	}

	public static String getAllText(Content content) {
		StringBuilder sb = new StringBuilder();
		List<Item> items = getItemList(content, Item.type_text);
		if (null != items) {
			for (Item i : items) {
				if (Item.type_text.equals(i.getType())) {
					sb.append(i.getValue());
				}
			}
		}
		return sb.toString();
	}

	public static String getSimpleText(Content content) {
		StringBuilder sb = new StringBuilder();
		List<Item> items = getItemList(content, Item.type_text, Item.type_face, Item.type_image);
		if (null != items) {
			for (Item i : items) {
				if (Item.type_text.equals(i.getType())) {
					sb.append(i.getValue());
				}
				if (Item.type_face.equals(i.getType())) {
					sb.append("[表情]");
				}
				if (Item.type_image.equals(i.getType())) {
					sb.append("[图片]");
				}
				if (sb.length() > 60) {
					break;
				}
			}
		}
		return sb.toString();
	}

	public static Content createContent(Item item) {

		Section section = new Section();
		section.getItems().add(item);

		Content content = new Content();
		content.setKey(item.getKey());
		content.getSections().add(section);
		return content;
	}

	public static Content createContent(String key, FileValue fv) {

		Item item = new Item();
		item.setKey(key);
		item.setType(Item.type_file);
		item.setValue(fv);

		return createContent(item);
	}

	public static int getItemSize(Content content) {
		int size = 0;
		if (null != content) {
			List<Section> sections = content.getSections();
			if (null != sections) {
				for (Section s : sections) {
					List<Item> items = s.getItems();
					if (null != items) {
						size = size + items.size();
					}
				}
			}
		}
		return size;
	}

	public static boolean hasImage(Content content) {
		boolean has = false;
		if (null != content) {
			List<Section> sections = content.getSections();
			if (null != sections) {
				for (Section s : sections) {
					List<Item> items = s.getItems();
					if (null != items) {
						for (Item i : items) {
							if (Item.type_image.equals(i.getType())) {
								has = true;
								break;
							}
						}
					}
				}
			}
		}
		return has;
	}

	public static List<Item> getImageItemList(Content content) {
		return CoreContentUtil.getItemList(content, Item.type_image);
	}

	public static List<Item> getFileItemList(Content content) {
		return CoreContentUtil.getItemList(content, Item.type_file);
	}

	public static List<Item> getFaceItemList(Content content) {
		return CoreContentUtil.getItemList(content, Item.type_face);
	}

	public static AtValue getAtValue(Content content, String userId) {
		AtValue atUser = null;
		AtValue atAll = null;
		if (null != userId) {
			List<Item> list = CoreContentUtil.getItemList(content, Item.type_at);
			if (null != list) {
				for (Item i : list) {
					Object o = i.getValue();
					if (o instanceof AtValue) {
						AtValue av = (AtValue) o;
						if (userId.equals(av.getUserId())) {
							atUser = av;
						}
						if (null == av.getUserId() || av.getUserId().trim().isEmpty()) {
							atAll = av;
						}
					}
				}
			}
		}
		return (null != atUser) ? atUser : atAll;
	}
}
