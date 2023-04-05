package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 
 * 加入群时验证问题 <br>
 * Date 2019-07-27 16:24:12<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_join_verify_question")
public class GroupJoinVerifyQuestion extends PrimaryStringEntity {

	/**
	 * 群id
	 */

	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 问题
	 */

	@Column(nullable = false, length = 200)
	private String question;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
