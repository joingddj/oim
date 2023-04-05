
package com.oimchat.client.general.kernel.work.module.group.data.vo;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;

/**
 * Description <br>
 * Date 2021-04-01 21:32:38<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMemberUserData {

	private GroupMember member;
	private User user;

	public GroupMemberUserData() {
		super();
	}

	public GroupMemberUserData(GroupMember member, User user) {
		super();
		this.member = member;
		this.user = user;
	}

	public GroupMember getMember() {
		return member;
	}

	public void setMember(GroupMember member) {
		this.member = member;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
