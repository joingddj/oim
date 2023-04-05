
package com.oimchat.client.general.kernel.work.module.contact.type;

/**
 * Description <br>
 * Date 2021-03-18 22:58:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum ContactVerifyTypeEnum {

	/** 1：允许任何人添加 **/
	any("1", "允许任何人添加"),
	/** 2：需要验证 **/
	auth("2", "需要验证"),
	/** 3：需要回答正确的问题 **/
	answer("3", "需要回答正确的问题"),
	/** 4：需要回答问题并由我确认 **/
	confirm("4", "需要回答问题并由我确认");

	String code;
	String text;

	private ContactVerifyTypeEnum(String code, String text) {
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

	public boolean isEquals(String value) {
		return this.code.equals(value);
	}
}
