
package com.oimchat.client.platform.fx.run.watch.chat.data;

/**
 * Description <br>
 * Date 2021-03-26 23:34:00<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatPromptData {

	private String messageKey;
	private String chatUserName;
	private String chatText;

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getChatUserName() {
		return chatUserName;
	}

	public void setChatUserName(String chatUserName) {
		this.chatUserName = chatUserName;
	}

	public String getChatText() {
		return chatText;
	}

	public void setChatText(String chatText) {
		this.chatText = chatText;
	}
}
