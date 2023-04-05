package com.oimchat.client.general.kernel.work.module.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 被加好友防止骚扰的设置<br>
 * Date 2019-01-20 10:15:59<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_contact_harass_setting")
public class ContactHarassSetting extends PrimaryStringEntity {

	/**
	 * 用户id
	 */
	@Column(nullable = false, length = 40)
	private String userId;
	/**
	 * 验证方式 1：允许任何人添加 2：需要验证 3：需要回答正确的问题 4：需要回答问题并由我确认 <br>
	 */
	@Column(nullable = false, length = 2)
	private String verifyType;
	/**
	 * 
	 */
	@Column(nullable = false, length = 200)
	private String question;
	/**
	 * 
	 */
	@Column(nullable = false, length = 200)
	private String answer;

	/** 1：允许任何人添加 **/
	public static final String verify_type_any = "1";
	/** 2：需要验证 **/
	public static final String verify_type_auth = "2";
	/** 3：需要回答正确的问题 **/
	public static final String verify_type_answer = "3";
	/** 4：需要回答问题并由我确认 **/
	public static final String verify_type_confirm = "4";

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
