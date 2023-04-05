
package com.oimchat.client.general.kernel.work.module.im.data.vo;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * Description <br>
 * Date 2021-03-22 18:47:35<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserMessageData {

	private boolean receive;
	private String sendUserId;
	private String receiveUserId;
	private boolean own;
	private User chatUser;
	private User showUser;
	private Content content;
	private MessageContentWrap contentWrap;

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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public MessageContentWrap getContentWrap() {
		return contentWrap;
	}

	public void setContentWrap(MessageContentWrap contentWrap) {
		this.contentWrap = contentWrap;
	}
}
