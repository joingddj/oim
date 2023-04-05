
package com.oimchat.client.basic.common.data.im.message;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;

/**
 * Description <br>
 * Date 2021-02-27 11:56:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageContentWrap extends MessageBaseWrap {

	private int type = 0;
	private boolean own = false;
	private String name = "";
	private String avatar = "";
	private String userId = "";
	private UserSimple user = new UserSimple();
	/**
	 * 0:发送中 1:发送成功 2:发送失败
	 */
	private int status = 0;
	private boolean nameVisible = false;

	private boolean receive = false;
	private Content content = new Content();

	public long getTimestamp() {
		Content content = this.content;
		return content != null ? content.getTimestamp() : 0;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isOwn() {
		return own;
	}

	public void setOwn(boolean own) {
		this.own = own;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserSimple getUser() {
		return user;
	}

	public void setUser(UserSimple user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isNameVisible() {
		return nameVisible;
	}

	public void setNameVisible(boolean nameVisible) {
		this.nameVisible = nameVisible;
	}

	public String getTimeText() {
		return timeText;
	}

	public void setTimeText(String timeText) {
		this.timeText = timeText;
	}

	public boolean isTimeVisible() {
		return timeVisible;
	}

	public void setTimeVisible(boolean timeVisible) {
		this.timeVisible = timeVisible;
	}

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
