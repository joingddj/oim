package com.oimchat.client.general.kernel.work.module.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 用户被添加的时验证问题<br>
 * Date 2019-01-20 13:16:40<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_contact_add_verify_question")
public class ContactAddVerifyQuestion extends PrimaryStringEntity {
	/**
	 * 用户id
	 */
	@Column(nullable = false, length = 40)
	private String userId;
	/**
	 * 问题
	 */
	@Column(nullable = false, length = 200)
	private String question;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
