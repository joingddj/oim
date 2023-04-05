
package com.oimchat.client.general.kernel.work.module.im.data.setting;

/**
 * Description <br>
 * Date 2021-03-30 09:55:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessagePastTimeFormat {
	/**
	 * 过去时长
	 */
	private long millisecond = 1000 * 60 * 5;
	/**
	 * 展示格式
	 */
	private String format = "HH:mm:ss";

	public MessagePastTimeFormat() {
		super();
	}

	public MessagePastTimeFormat(long millisecond, String format) {
		super();
		this.millisecond = millisecond;
		this.format = format;
	}

	public long getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(long millisecond) {
		this.millisecond = millisecond;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
