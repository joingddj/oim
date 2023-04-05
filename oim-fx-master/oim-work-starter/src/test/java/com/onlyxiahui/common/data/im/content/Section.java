package com.onlyxiahui.common.data.im.content;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Date 2019-01-06 12:06:11<br>
 * Description 段落信息
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
public class Section {

	/**
	 * 段落中可能是有图片、文件、文本等信息混排
	 */
	private List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
