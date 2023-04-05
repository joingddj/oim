package com.oimchat.client.general.kernel.work.module.im.data.dto;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;

/**
 * Date 2019-02-17 17:54:19<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */

public class GroupChatData {

	private GroupSimple group;
	private UserSimple user;
	private GroupMemberSimple groupMember;
	private Content content;

	public GroupSimple getGroup() {
		return group;
	}

	public void setGroup(GroupSimple group) {
		this.group = group;
	}

	public UserSimple getUser() {
		return user;
	}

	public void setUser(UserSimple user) {
		this.user = user;
	}

	public GroupMemberSimple getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(GroupMemberSimple groupMember) {
		this.groupMember = groupMember;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
}
