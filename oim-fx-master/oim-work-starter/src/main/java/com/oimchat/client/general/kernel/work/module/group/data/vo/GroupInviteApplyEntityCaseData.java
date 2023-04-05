
package com.oimchat.client.general.kernel.work.module.group.data.vo;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupInviteApply;

/**
 * Description <br>
 * Date 2021-04-06 20:58:36<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInviteApplyEntityCaseData {

	private GroupInviteApply apply;
	/**
	 * 发起邀请者
	 */
	private User inviterUser;
	/**
	 * 被邀请者
	 */
	private User inviteeUser;

	private Group group;

	public GroupInviteApply getApply() {
		return apply;
	}

	public void setApply(GroupInviteApply apply) {
		this.apply = apply;
	}

	public User getInviterUser() {
		return inviterUser;
	}

	public void setInviterUser(User inviterUser) {
		this.inviterUser = inviterUser;
	}

	public User getInviteeUser() {
		return inviteeUser;
	}

	public void setInviteeUser(User inviteeUser) {
		this.inviteeUser = inviteeUser;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
