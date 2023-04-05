
package com.oimchat.client.general.kernel.work.module.core.type;

/**
 * Description <br>
 * Date 2021-03-17 15:22:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum UserStatusTypeEnum {

	online("1", "在线"),
	call_me("2", "Call我"),
	away("3", "离开"),
	busy("4", "忙碌"),
	mute("5", "勿扰"),
	invisible("6", "隐身"),
	offline("7", "离线");

	String code;
	String text;

	private UserStatusTypeEnum(String code, String text) {
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
