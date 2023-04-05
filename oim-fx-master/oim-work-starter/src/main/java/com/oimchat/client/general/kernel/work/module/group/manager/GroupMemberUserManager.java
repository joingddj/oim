
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberUserBox;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupMemberQuery;
import com.oimchat.client.general.kernel.work.module.group.data.vo.GroupMemberUserData;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupMemberHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-27 23:18:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMemberUserManager extends AbstractMaterial {

	long maxSize = 3000;

	public GroupMemberUserManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetCountByGroupId(String groupId, ValueBack<Long> back) {
		GroupMemberQuery query = new GroupMemberQuery();
		query.setGroupId(groupId);
		GroupMemberHandler cch = this.appContext.getObject(GroupMemberHandler.class);
		cch.queryGroupMemberCount(query, (info, c) -> {
			Long l = c.getCount();
			back.back(info, null == l ? 0L : l);
		});
	}

	public void asynGetAllListByGroupId(String groupId, ValueBack<List<GroupMemberUserData>> back) {
		GroupMemberQuery query = new GroupMemberQuery();
		query.setGroupId(groupId);

		UserHandler uh = this.appContext.getObject(UserHandler.class);

		GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
		handler.queryGroupMemberCount(query, (info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			l = (l > maxSize) ? maxSize : l;
			int size = l.intValue();
			if (size > 0) {
				List<GroupMemberUserData> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, query, (n, p, q) -> {
					handler.queryGroupMemberList(query, p, (i, pr) -> {

						List<GroupMember> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();

						uh.getMapByIds(list, (t) -> {
							return t.getUserId();
						}, (ui, map) -> {
							if (null != map) {
								for (GroupMember gm : list) {
									String userId = gm.getUserId();
									User u = map.get(userId);
									if (null != u) {
										allList.add(new GroupMemberUserData(gm, u));
									}
								}
							}
							if (p.getNumber() >= p.getTotalPage()) {
								back.back(info, allList);
							} else {
								n.next();
							}
						});
					});
				});
			} else {
				back.back(info, new ArrayList<>());
			}
		});
	}

	public void asynLoadAllListByGroupId(String groupId, ValueBack<List<GroupMemberUserData>> back) {
		GroupMemberUserBox gmubox = this.appContext.getObject(GroupMemberUserBox.class);
		GroupMemberBox gmbox = this.appContext.getObject(GroupMemberBox.class);

		gmubox.removeCategory(groupId);
		gmbox.removeCategory(groupId);

		asynGetAllListByGroupId(groupId, (info, list) -> {
			list.forEach((item) -> {
				GroupMember member = item.getMember();
				User user = item.getUser();
				gmubox.put(groupId, user.getId(), user);
				gmbox.put(groupId, member.getUserId(), member);
			});
			back.back(info, list);
		});
	}

	public void loadByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<User> back) {
		GroupMemberUserBox box = this.appContext.getObject(GroupMemberUserBox.class);

		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, user) -> {
			if (info.isSuccess() && null != user) {
				box.put(groupId, user.getId(), user);
			}
			back.back(info, user);
		});
	}

	public void getOrLoadByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<User> back) {
		GroupMemberUserBox box = this.appContext.getObject(GroupMemberUserBox.class);
		User gm = box.get(groupId, userId);
		if (null == gm) {
			loadByGroupIdUserId(groupId, userId, back);
		} else {
			back.back(new Info(), gm);
		}
	}

	public void getLocalOrRemoteByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<User> back) {
		GroupMemberUserBox box = this.appContext.getObject(GroupMemberUserBox.class);
		User gm = box.get(groupId, userId);
		if (null == gm) {
			UserHandler uh = this.appContext.getObject(UserHandler.class);
			uh.getById(userId, back);
		} else {
			back.back(new Info(), gm);
		}
	}
}
