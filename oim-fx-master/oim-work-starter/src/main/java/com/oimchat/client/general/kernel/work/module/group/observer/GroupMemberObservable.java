
package com.oimchat.client.general.kernel.work.module.group.observer;

import com.oimchat.client.general.kernel.work.common.observer.BaseDataObservable;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:08:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMemberObservable extends BaseDataObservable<GroupMember, GroupMember, GroupMember> {

	public GroupMemberObservable(AppContext appContext) {
		super(appContext);
	}
}
