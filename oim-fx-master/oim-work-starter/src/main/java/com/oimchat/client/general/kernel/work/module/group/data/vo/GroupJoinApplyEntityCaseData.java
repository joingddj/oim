
package com.oimchat.client.general.kernel.work.module.group.data.vo;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;

/**
 * Description <br>
 * Date 2021-04-06 20:19:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupJoinApplyEntityCaseData extends GroupJoinApplyEntityCase {

	private User user;
	private Group group;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
