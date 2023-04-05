package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 
 * 用户加入群时回答验证问题 <br>
 * Date 2019-07-27 17:35:50<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_join_verify_answer")
public class GroupJoinVerifyAnswer extends PrimaryStringEntity {

	/**
	 * 请求消息的id(对应GroupJoinApply中的id)
	 */

	@Column(nullable = false, length = 40)
	private String applyId;

	/**
	 * 申请的用户id
	 */

	@Column(nullable = false, length = 40)
	private String applyUserId;

	/**
	 * 加入的群
	 */

	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 问题id(对应GroupJoinVerifyQuestion中的id
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
