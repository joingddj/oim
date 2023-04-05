package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 群邀请请求<br>
 * Date 2019-01-22 22:53:42<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_invite_apply")
public class GroupInviteApply extends PrimaryStringEntity {

	/**
	 * 群id
	 */
	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 受邀请人id
	 */
	@Column(nullable = false, length = 40)
	private String inviteeUserId;

	/**
	 * 处理结果：0:未处理 1:同意 2:拒绝 3:忽略
	 */
	@Column(nullable = false, length = 2)
	private String inviteeHandleType;

	/**
	 * 被邀请人处理时间(毫秒)
	 */
	@Column(nullable = false)
	private Long inviterHandleTimestamp;

	/**
	 * 邀请人id
	 */
	@Column(nullable = false, length = 40)
	private String inviterUserId;

	/**
	 * 发起邀请人的群权限
	 */
	@Column(nullable = false, length = 2)
	private String inviterUserPosition;

	/**
	 * 发起邀请人附加消息
	 */
	@Column(nullable = false, length = 500)
	private String inviterMessage;

	/**
	 * 当时设定的验证方式
	 */
	@Column(nullable = false, length = 2)
	private String verifyType;

	/**
	 * 处理邀请的用户id（群主/管理员）
	 */
	@Column(nullable = false, length = 40)
	private String verifyUserId;

	/**
	 * 处理邀请的用户权限（群主/管理员）
	 */
	@Column(nullable = false, length = 2)
	private String verifyUserPosition;

	/**
	 * 处理结果：0:未处理 1:同意 2:拒绝 3:忽略
	 */
	@Column(nullable = false, length = 2)
	private String verifyHandleType;

	/**
	 * 群主/管理员验证处理时间(毫秒)
	 */
	@Column(nullable = false)
	private Long verifyHandleTimestamp;

	/**
	 * 群主/管理员验证时附加消息
	 */
	@Column(nullable = false, length = 500)
	private String verifyMessage;

	/** 0:未处理 **/
	public static final String verify_handle_type_untreated = "0";
	/** 1:同意 **/
	public static final String verify_handle_type_accept = "1";
	/** 2:拒绝 **/
	public static final String verify_handle_type_reject = "2";
	/** 3:忽略 **/
	public static final String verify_handle_type_ignore = "3";

	/** 0:未处理 **/
	public static final String invitee_handle_type_untreated = "0";
	/** 1:同意 **/
	public static final String invitee_handle_type_accept = "1";
	/** 2:拒绝 **/
	public static final String invitee_handle_type_reject = "2";
	/** 3:忽略 **/
	public static final String invitee_handle_type_ignore = "3";

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getInviteeUserId() {
		return inviteeUserId;
	}

	public void setInviteeUserId(String inviteeUserId) {
		this.inviteeUserId = inviteeUserId;
	}

	public String getInviteeHandleType() {
		return inviteeHandleType;
	}

	public void setInviteeHandleType(String inviteeHandleType) {
		this.inviteeHandleType = inviteeHandleType;
	}

	public Long getInviterHandleTimestamp() {
		return inviterHandleTimestamp;
	}

	public void setInviterHandleTimestamp(Long inviterHandleTimestamp) {
		this.inviterHandleTimestamp = inviterHandleTimestamp;
	}

	public String getInviterUserId() {
		return inviterUserId;
	}

	public void setInviterUserId(String inviterUserId) {
		this.inviterUserId = inviterUserId;
	}

	public String getInviterUserPosition() {
		return inviterUserPosition;
	}

	public void setInviterUserPosition(String inviterUserPosition) {
		this.inviterUserPosition = inviterUserPosition;
	}

	public String getInviterMessage() {
		return inviterMessage;
	}

	public void setInviterMessage(String inviterMessage) {
		this.inviterMessage = inviterMessage;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	public String getVerifyUserId() {
		return verifyUserId;
	}

	public void setVerifyUserId(String verifyUserId) {
		this.verifyUserId = verifyUserId;
	}

	public String getVerifyUserPosition() {
		return verifyUserPosition;
	}

	public void setVerifyUserPosition(String verifyUserPosition) {
		this.verifyUserPosition = verifyUserPosition;
	}

	public String getVerifyHandleType() {
		return verifyHandleType;
	}

	public void setVerifyHandleType(String verifyHandleType) {
		this.verifyHandleType = verifyHandleType;
	}

	public Long getVerifyHandleTimestamp() {
		return verifyHandleTimestamp;
	}

	public void setVerifyHandleTimestamp(Long verifyHandleTimestamp) {
		this.verifyHandleTimestamp = verifyHandleTimestamp;
	}

	public String getVerifyMessage() {
		return verifyMessage;
	}

	public void setVerifyMessage(String verifyMessage) {
		this.verifyMessage = verifyMessage;
	}
}
