
package com.oimchat.client.basic.common.data.im.message;

/**
 * Description <br>
 * Date 2021-03-22 15:46:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class MessageBaseWrap {

	protected int type = 0;
	protected String id = "";
	protected String key = "";

	protected String timeText = "";
	protected boolean timeVisible = true;
	protected long timestamp = 0L;

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
}
