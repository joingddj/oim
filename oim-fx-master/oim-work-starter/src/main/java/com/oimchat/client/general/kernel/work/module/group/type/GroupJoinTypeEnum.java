
package com.oimchat.client.general.kernel.work.module.group.type;

/**
 * Description <br>
 * Date 2021-03-18 22:58:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum GroupJoinTypeEnum {

	/** 1：允许任何人加入 **/
	any("1", "允许任何人加入"),
	/** 2：需要验证消息 **/
	auth("2", "需要验证消息"),
	/** 3：需要回答正确问题 **/
	answer("3", "需要回答正确问题"),
	/** 4：需要回答问题并由管理员审核 **/
	confirm("4", "需要回答问题并由管理员审核"),
	/** 5：只允许邀请加入 **/
	invite("5", "只允许邀请加入"),
	/** 4：不允许任何人加入 **/
	never("6", "不允许任何人加入");

	String code;
	String text;

	private GroupJoinTypeEnum(String code, String text) {
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
