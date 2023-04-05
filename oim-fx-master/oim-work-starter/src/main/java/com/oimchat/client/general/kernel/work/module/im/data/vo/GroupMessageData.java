
package com.oimchat.client.general.kernel.work.module.im.data.vo;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;

/**
 * Description <br>
 * Date 2021-03-22 18:47:35<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMessageData {

	private boolean receive;
	private String groupId;
	private String userId;
	private boolean own;
	private Group group;
	private UserSimple user;
	private Content content;
	private MessageContentWrap contentWrap;

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isOwn() {
		return own;
	}

	public void setOwn(boolean own) {
		this.own = own;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserSimple getUser() {
		return user;
	}

	public void setUser(UserSimple user) {
		this.user = user;
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
