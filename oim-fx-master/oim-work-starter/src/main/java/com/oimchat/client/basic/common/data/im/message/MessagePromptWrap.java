
package com.oimchat.client.basic.common.data.im.message;

/**
 * Description <br>
 * Date 2021-02-27 11:56:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessagePromptWrap extends MessageBaseWrap {

	private int type = 1;
	private String text = "";

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTimeText() {
		return timeText;
	}

	public void setTimeText(String timeText) {
		this.timeText = timeText;
	}

	public boolean isTimeVisible() {
		return timeVisible;
	}

	public void setTimeVisible(boolean timeVisible) {
		this.timeVisible = timeVisible;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
