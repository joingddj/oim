
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.group.box.PersonalGroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupMemberHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-02 10:47:27<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalGroupMemberManager extends AbstractMaterial {

	public PersonalGroupMemberManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetAllJoinedListByUserId(String userId, ValueBack<List<GroupMember>> back) {
		GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
		handler.getOwnerGroupMemberCount(userId, (info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			int size = l.intValue();
			if (size > 0) {
				List<GroupMember> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, null, (n, p, q) -> {
					handler.getOwnerGroupMemberList(userId, p, (i, pr) -> {
						List<GroupMember> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
						allList.addAll(list);
						if (p.getNumber() >= p.getTotalPage()) {
							back.back(info, allList);
						} else {
							n.next();
						}
					});
				});
			} else {
				back.back(info, new ArrayList<>());
			}
		});
	}

	public void asynLoadAllJoinedListByUserId(String userId, ValueBack<List<GroupMember>> back) {
		PersonalGroupMemberBox box = this.appContext.getObject(PersonalGroupMemberBox.class);
		box.clear();
		asynGetAllJoinedListByUserId(userId, (info, list) -> {
			box.setGroupMemberList(list);
			back.back(info, list);
		});
	}

	public void loadByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<GroupMember> back) {
		PersonalGroupMemberBox box = this.appContext.getObject(PersonalGroupMemberBox.class);
		GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
		handler.getGroupMember(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(data);
			}
			back.back(info, data);
		});
	}
}
