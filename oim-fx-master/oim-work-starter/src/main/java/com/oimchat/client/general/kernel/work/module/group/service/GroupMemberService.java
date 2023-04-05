
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupMemberManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupMemberObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-27 23:18:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMemberService extends AbstractMaterial {

	public GroupMemberService(AppContext appContext) {
		super(appContext);
	}

	public void add(String groupId, String userId) {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		GroupMemberManager manager = this.appContext.getObject(GroupMemberManager.class);
		manager.loadByGroupIdUserId(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}

	public void update(String groupId, String userId) {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		GroupMemberManager manager = this.appContext.getObject(GroupMemberManager.class);
		manager.loadByGroupIdUserId(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}

	public void updatePosition(String groupId, String userId, String position) {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		GroupMemberManager manager = this.appContext.getObject(GroupMemberManager.class);
		manager.loadByGroupIdUserId(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}

	public void delete(String groupId, String userId) {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		GroupMember data = box.removeItem(groupId, userId);
		if (null == data) {
			data = new GroupMember();
			data.setGroupId(groupId);
			data.setUserId(userId);
		}
		o.executeDelete(data);
	}

	public void updateInfo(String groupId, String userId) {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		GroupMemberManager manager = this.appContext.getObject(GroupMemberManager.class);
		manager.loadByGroupIdUserId(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}
}
