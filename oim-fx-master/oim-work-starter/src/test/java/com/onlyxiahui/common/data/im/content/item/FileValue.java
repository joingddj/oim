package com.onlyxiahui.common.data.im.content.item;

/**
 * @author XiaHui
 * @date 2017-05-29 7:34:48
 */
public class FileValue {

	private String type;// 1:local upload 2:net
	private String id;
	private String extension;
	private String name;
	private String url;
	private long size;
	private String sizeText;
	private String iconUrl;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSizeText() {
		return sizeText;
	}

	public void setSizeText(String sizeText) {
		this.sizeText = sizeText;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
