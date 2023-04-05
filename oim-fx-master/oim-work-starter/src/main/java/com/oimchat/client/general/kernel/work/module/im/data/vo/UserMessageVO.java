
package com.oimchat.client.general.kernel.work.module.im.data.vo;

import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * Description <br>
 * Date 2021-03-27 15:48:12<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserMessageVO<T> {

	private boolean receive;
	private String sendUserId;
	private String receiveUserId;
	private boolean own;
	private User chatUser;
	private User showUser;
	private T data;

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public boolean isOwn() {
		return own;
	}

	public void setOwn(boolean own) {
		this.own = own;
	}

	public User getChatUser() {
		return chatUser;
	}

	public void setChatUser(User chatUser) {
		this.chatUser = chatUser;
	}

	public User getShowUser() {
		return showUser;
	}

	public void setShowUser(User showUser) {
		this.showUser = showUser;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
