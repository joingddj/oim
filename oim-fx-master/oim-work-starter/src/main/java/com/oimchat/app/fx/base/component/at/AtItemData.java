
package com.oimchat.app.fx.base.component.at;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * Description <br>
 * Date 2021-03-04 16:24:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AtItemData {

	private final Map<Object, Object> attributeMap = new HashMap<Object, Object>();
	private Image image;
	private String text;
	private String key;

	public void addAttribute(Object key, Object value) {
		attributeMap.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(Object key) {
		return (T) attributeMap.get(key);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<Object, Object> getAttributeMap() {
		return attributeMap;
	}
}
