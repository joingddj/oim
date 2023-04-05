
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.manager.PersonalGroupMemberManager;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-27 23:18:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalGroupMemberService extends AbstractMaterial {

	public PersonalGroupMemberService(AppContext appContext) {
		super(appContext);
	}

	public void loadAllJoined() {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownerUserId = pb.getOwnerUserId();

		PersonalGroupMemberManager gmm = appContext.getObject(PersonalGroupMemberManager.class);
		gmm.asynLoadAllJoinedListByUserId(ownerUserId, (i, l) -> {
		});
	}

	public void add(String groupId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		PersonalGroupMemberManager gmm = appContext.getObject(PersonalGroupMemberManager.class);
		gmm.loadByGroupIdUserId(groupId, pb.getOwnerUserId(), (i, g) -> {

		});
	}

	public void update(String groupId, String userId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		if (pb.getOwnerUserId().equals(userId)) {
			PersonalGroupMemberManager gmm = appContext.getObject(PersonalGroupMemberManager.class);
			gmm.loadByGroupIdUserId(groupId, userId, (i, g) -> {

			});
		}
	}

	public void updatePosition(String groupId, String userId, String position) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		if (pb.getOwnerUserId().equals(userId)) {
			PersonalGroupMemberManager gmm = appContext.getObject(PersonalGroupMemberManager.class);
			gmm.loadByGroupIdUserId(groupId, userId, (i, g) -> {

			});
		}
	}

	public void updateInfo(String groupId, String userId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		if (pb.getOwnerUserId().equals(userId)) {
			PersonalGroupMemberManager gmm = appContext.getObject(PersonalGroupMemberManager.class);
			gmm.loadByGroupIdUserId(groupId, userId, (i, g) -> {

			});
		}
	}
}
