
package com.oimchat.client.general.kernel.work.module.group.type;

/**
 * Description <br>
 * Date 2021-03-18 22:58:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public enum GroupInviteTypeEnum {

	/** 1：不允许邀请 **/
	never("1", "不允许邀请"),
	/** 2：管理员邀请加入 **/
	admin("2", "管理员邀请加入"),
	/** 3：允许任何人邀请加入 **/
	any("3", "允许任何人邀请加入"),
	/** 4：需要管理员验证 **/
	auth("4", "需要管理员验证");

	String code;
	String text;

	private GroupInviteTypeEnum(String code, String text) {
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
