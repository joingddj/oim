package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 加入群设置<br>
 * Date 2019-01-22 22:50:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_join_setting")
public class GroupJoinSetting extends PrimaryStringEntity {

	/**
	 * 群id
	 */

	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 申请加群方式<br>
	 * 1.允许任何人加入<br>
	 * 2.需要验证消息<br>
	 * 3.需要回答正确问题<br>
	 * 4.需要回答问题并由管理员审核<br>
	 * 5.只允许邀请加入<br>
	 * 6.不允许任何人加入<br>
	 */

	@Column(nullable = false, length = 2)
	private String joinType;

	/**
	 * 邀请加群方式<br>
	 * 1.不允许邀请<br>
	 * 2.管理员邀请加入<br>
	 * 3.允许任何人邀请加入<br>
	 * 4.需要管理员验证<br>
	 */

	@Column(nullable = false, length = 2)
	private String inviteType;

	/**
	 * 申请加群方式为3.需要回答的问题
	 */

	@Column(nullable = false, length = 200)
	private String question;

	/**
	 * 申请加群方式为3.需要回答的答案
	 */

	@Column(nullable = false, length = 200)
	private String answer;

	/** 1：.允许任何人加入 **/
	public static final String join_verify_type_any = "1";
	/** 2：需要验证消息 **/
	public static final String join_verify_type_auth = "2";
	/** 3：需要回答正确问题 **/
	public static final String join_verify_type_answer = "3";
	/** 4：需要回答问题并由管理员审核 **/
	public static final String join_verify_type_confirm = "4";
	/** 5：只允许邀请加入 **/
	public static final String join_verify_type_invite = "5";
	/** 6：不允许任何人加入 **/
	public static final String join_verify_type_never = "6";

	/** 1：.不允许邀请 **/
	public static final String invite_type_never = "1";
	/** 2：管理员邀请加入 **/
	public static final String invite_type_admin = "2";
	/** 3：允许任何人邀请加入 **/
	public static final String invite_type_any = "3";
	/** 4：需要管理员验证 **/
	public static final String invite_type_auth = "4";

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getInviteType() {
		return inviteType;
	}

	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
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