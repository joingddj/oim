package com.oimchat.client.general.kernel.work.module.im.data.dto;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;

/**
 * 聊天信息<br>
 * Date 2019-02-17 17:54:19<br>
 * 
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */

public class UserChatData {

	private UserSimple receiveUser;
	private UserSimple sendUser;
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public UserSimple getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(UserSimple receiveUser) {
		this.receiveUser = receiveUser;
	}

	public UserSimple getSendUser() {
		return sendUser;
	}

	public void setSendUser(UserSimple sendUser) {
		this.sendUser = sendUser;
	}
}
