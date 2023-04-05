package com.oimchat.client.general.kernel.work.module.group.box;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.general.kernel.work.common.cleaner.AbstractWorkCleaner;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.onlyxiahui.app.context.AppContext;

/**
 * @author: XiaHui
 * @date: 2017年6月19日 下午1:48:30
 */
public class PersonalGroupMemberBox extends AbstractWorkCleaner {

	Map<String, GroupMember> groupMemberMap = new ConcurrentHashMap<String, GroupMember>();

	public PersonalGroupMemberBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void clear() {
		groupMemberMap.clear();
	}

	public void put(GroupMember gm) {
		if (null != gm) {
			groupMemberMap.put(gm.getGroupId(), gm);
		}
	}

	public void setGroupMemberList(List<GroupMember> list) {
		if (null != list) {
			for (GroupMember gm : list) {
				put(gm);
			}
		}
	}

	public List<GroupMember> getAllList() {
		List<GroupMember> list = new ArrayList<>(groupMemberMap.values());
		return list;
	}

	public GroupMember removeGroupMember(String groupId) {
		GroupMember gm = groupMemberMap.remove(groupId);
		return gm;
	}

	public String getGroupPosition(String groupId) {
		String position = "3";
		GroupMember gm = groupMemberMap.get(groupId);
		if (null != gm) {
			position = gm.getPosition();
		}
		return position;
	}

	public boolean isOwner(String groupId) {
		String position = getGroupPosition(groupId);
		boolean mark = GroupMember.position_owner.equals(position);
		return mark;
	}

	public boolean isNormal(String groupId) {
		String position = getGroupPosition(groupId);
		boolean mark = !GroupMember.position_owner.equals(position) && !GroupMember.position_admin.equals(position);
		return mark;
	}

	public boolean isAdmin(String groupId) {
		String position = getGroupPosition(groupId);
		boolean mark = GroupMember.position_admin.equals(position);
		return mark;
	}

	public boolean isJoined(String groupId) {
		GroupMember gm = groupMemberMap.get(groupId);
		return null != gm;
	}
}
