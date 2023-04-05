package com.oimchat.client.general.kernel.work.module.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 用户被添加的时验证问题回答<br>
 * Date 2019-01-20 13:19:29<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_contact_add_verify_answer")
public class ContactAddVerifyAnswer extends PrimaryStringEntity {

	/**
	 * 请求消息的id(对应ContactAddApply中的id)
	 */
	@Column(nullable = false, length = 40)
	private String applyId;
	/**
	 * 请求用户id
	 */
	@Column(nullable = false, length = 40)
	private String applyUserId;
	/**
	 * 被添加的用户id
	 */
	@Column(nullable = false, length = 40)
	private String targetUserId;
	/**
	 * 问题id(对应ContactAddVerifyQuestion中的id)
	 */
	@Column(nullable = false, length = 40)
	private String questionId;
	/**
	 * 问题
	 */
	@Column(nullable = false, length = 200)
	private String question;
	/**
	 * 答案
	 */
	@Column(nullable = false, length = 200)
	private String answer;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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
