package com.oimchat.client.general.kernel.work.module.im.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 用户聊天未读<br>
 * Date 2019-02-17 11:58:41<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "im_user_chat_unread")
public class UserChatUnread extends PrimaryStringEntity {

	// "接受用户id"
	@Column(nullable = false, length = 40)
	private String receiveUserId;

	// ("发送用户id")
	@Column(nullable = false, length = 40)
	private String sendUserId;

	// ("消息id")
	@Column(nullable = false, length = 40)
	private String lastContentId;

	// ("未读数量")
	@Column(nullable = false, length = 11)
	private Integer unreadCount;

	// ("时间戳（毫秒）")
	@Column(nullable = false, length = 32)
	private Long timestamp;

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getLastContentId() {
		return lastContentId;
	}

	public void setLastContentId(String lastContentId) {
		this.lastContentId = lastContentId;
	}

	public Integer getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
