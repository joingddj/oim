
package com.oimchat.client.general.kernel.work.module.im.type;

/**
 * Description <br>
 * Date 2021-03-27 15:43:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum MotionTypeEnum {

	shake("1", "抖一抖");

	String code;
	String text;

	private MotionTypeEnum(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
