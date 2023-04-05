
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupMemberQuery;
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

public class GroupMemberManager extends AbstractMaterial {

	long maxSize = 3000;

	public GroupMemberManager(AppContext appContext) {
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

	public void asynGetAllListByGroupId(String groupId, ValueBack<List<GroupMember>> back) {
		GroupMemberQuery query = new GroupMemberQuery();
		query.setGroupId(groupId);

		GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
		handler.queryGroupMemberCount(query, (info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			l = (l > maxSize) ? maxSize : l;
			int size = l.intValue();
			if (size > 0) {
				List<GroupMember> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, query, (n, p, q) -> {
					handler.queryGroupMemberList(query, p, (i, pr) -> {
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

	public void asynLoadAllListByGroupId(String groupId, ValueBack<List<GroupMember>> back) {
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		asynGetAllListByGroupId(groupId, (info, list) -> {
			list.forEach((item) -> {
				box.put(item.getGroupId(), item.getUserId(), item);
			});
			back.back(info, list);
		});
	}

	public void loadByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<GroupMember> back) {
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
		handler.getGroupMember(groupId, userId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(data.getGroupId(), data.getUserId(), data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<GroupMember> back) {
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		GroupMember gm = box.get(groupId, userId);
		if (null == gm) {
			loadByGroupIdUserId(groupId, userId, back);
		} else {
			back.back(new Info(), gm);
		}
	}

	public void getLocalOrRemoteByGroupIdUserId(
			String groupId,
			String userId,
			ValueBack<GroupMember> back) {
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		GroupMember gm = box.get(groupId, userId);
		if (null == gm) {
			GroupMemberHandler handler = this.appContext.getObject(GroupMemberHandler.class);
			handler.getGroupMember(groupId, userId, back);
		} else {
			back.back(new Info(), gm);
		}
	}
}
