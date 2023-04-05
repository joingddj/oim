package com.oimchat.client.general.kernel.work.module.im.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 用户最近聊天<br>
 * Date 2019-01-18 13:51:03<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "im_recent_chat")
public class RecentChat extends PrimaryStringEntity {

	/**
	 * 用户id
	 */
	@Column(nullable = false, length = 40)
	private String userId;

	/**
	 * 消息key
	 */
	@Column(nullable = false, length = 40)
	private String messageKey;

	/**
	 * 消息id
	 */
	@Column(nullable = false, length = 40)
	private String contentId;

	/**
	 * 具体聊天对象的id（如联系人，群）
	 */
	@Column(nullable = false, length = 40)
	private String chatId;

	/**
	 * 类型： 1：联系人 2：群 3：讨论组 4：聊天室
	 */
	@Column(nullable = false, length = 40)
	private String type;

	/**
	 * 时间（格式：2019-01-01 00:00:00）
	 */
	@Column(nullable = false)
	private LocalDateTime dateTime;

	/**
	 * 时间戳（毫秒）
	 */
	@Column(nullable = false, length = 32)
	private Long timestamp;

	public static String TYPE_USER = "1";// 联系人
	public static String TYPE_GROUP = "2";// 群
	public static String TYPE_TEAM = "3";// 讨论组
	public static String TYPE_ROOM = "4";// 聊天室
	public static String TYPE_MEETING = "5";// 会议

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
