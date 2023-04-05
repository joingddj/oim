package com.oimchat.server.general.kernel.work.module.business.group.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.basic.common.util.KeyUtil;
import com.oimchat.server.general.kernel.work.module.business.group.dao.GroupExtendBusinessDAO;
import com.oimchat.server.general.kernel.work.module.business.group.dao.GroupManageDAO;
import com.oimchat.server.general.kernel.work.module.business.group.dao.GroupMemberExtendBusinessDAO;
import com.oimchat.server.general.kernel.work.module.business.group.data.query.GroupInviteVerifyQuery;
import com.oimchat.server.general.kernel.work.module.business.group.data.query.GroupJoinApplyQuery;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupRelationDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupInviteApply;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupJoinApply;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupBusinessPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupMemberPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupRelationPush;
import com.onlyxiahui.aware.basic.work.business.error.ErrorCode;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.message.bean.Info;

/**
 * 
 * Date 2019-01-23 22:01:34<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
@Transactional
public class GroupManageService {

	@Autowired
	private GroupExtendBusinessDAO groupDAO;
	@Autowired
	private GroupManageDAO groupManageDAO;
	@Autowired
	private GroupMemberExtendBusinessDAO groupMemberDAO;
	@Autowired
	private GroupRelationDAO groupRelationDAO;
	@Autowired
	private GroupExtendManager groupManager;
	@Autowired
	private GroupMemberExtendManager groupMemberManager;
	@Autowired
	private GroupMemberPush groupMemberPush;
	@Autowired
	private GroupBusinessPush groupBusinessPush;
	@Autowired
	private GroupRelationPush groupRelationPush;

	/**
	 * 
	 * Date 2019-01-24 22:08:14<br>
	 * Description 管理员或者群主获取申请加入群的请求列表
	 * 
	 * @author XiaHui<br>
	 * @param userId
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	public List<GroupJoinApply> getJoinRequestListByUserId(String userId, Page page) {
		List<GroupJoinApply> list = groupManageDAO.getJoinRequestListByUserId(userId, page);
		return list;
	}

	public List<GroupJoinApply> queryJoinRequestList(String userId, GroupJoinApplyQuery query, Page page) {
		List<GroupJoinApply> list = groupManageDAO.queryJoinRequestList(userId, query, page);
		return list;
	}

	/**
	 * 
	 * Date 2019-01-26 09:34:41<br>
	 * Description 管理员或者群主获取审批邀请的用户
	 * 
	 * @author XiaHui<br>
	 * @param userId
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	public List<GroupInviteApply> queryInviteRequestList(String userId, GroupInviteVerifyQuery query, Page page) {
		List<GroupInviteApply> list = groupManageDAO.queryInviteRequestList(userId, query, page);
		return list;
	}

	/**
	 * 
	 * Date 2019-01-26 14:54:46<br>
	 * Description 转让群主
	 * 
	 * @author XiaHui<br>
	 * @param groupId
	 * @param oldOwnerUserId
	 * @param newOwnerUserId
	 * @return
	 * @since 1.0.0
	 */
	public Info changeGroupOwner(String groupId, String oldOwnerUserId, String newOwnerUserId) {
		Info info = new Info();
		boolean isOwner = groupMemberManager.isOwner(groupId, oldOwnerUserId);
		// 是群主才能转让
		if (isOwner) {
			// 原群主设为普通成员
			groupMemberDAO.updatePosition(groupId, oldOwnerUserId, GroupMember.position_normal);
			// 设置新群主
			groupMemberDAO.updatePosition(groupId, newOwnerUserId, GroupMember.position_owner);

			// TODO推送群主变化消息
			List<String> userIds = new ArrayList<>();
			List<GroupMember> list = groupMemberDAO.getListByGroupId(groupId);
			for (GroupMember m : list) {
				userIds.add(m.getUserId());
			}
			groupMemberPush.pushUpdatePosition(userIds, KeyUtil.getKey(), groupId, oldOwnerUserId, GroupMember.position_normal);
			groupMemberPush.pushUpdatePosition(userIds, KeyUtil.getKey(), groupId, newOwnerUserId, GroupMember.position_owner);
		} else {
			info.addWarning(ErrorCode.business.code("001"), "没有权限！");
		}
		return info;
	}

	/**
	 * 
	 * Date 2019-01-26 15:01:20<br>
	 * Description 解散群
	 * 
	 * @author XiaHui<br>
	 * @param groupId
	 * @param ownerUserId
	 * @return
	 * @since 1.0.0
	 */
	public Info disbandGroup(String groupId, String ownerUserId) {
		Info info = new Info();
		boolean isOwner = groupMemberManager.isOwner(groupId, ownerUserId);
		// 是群主才能解散群
		if (isOwner) {
			List<String> userIds = new ArrayList<>();
			List<GroupMember> list = groupMemberDAO.getListByGroupId(groupId);
			for (GroupMember m : list) {
				userIds.add(m.getUserId());
			}
			// 删除群主以外的其他成员
			groupMemberDAO.deleteOutUserIdByGroupId(groupId, ownerUserId);

			groupRelationDAO.deleteOutUserIdByGroupId(groupId, ownerUserId);

			// TODO推送解散群消息 userIds
			groupBusinessPush.pushDisband(ownerUserId, KeyUtil.getKey(), groupId, ownerUserId);

			// 推送给群成员，移除群的分组关系
			userIds.remove(ownerUserId);
			groupRelationPush.pushDelete(userIds, KeyUtil.getKey(), groupId);
		} else {
			info.addWarning(ErrorCode.business.code("001"), "没有权限！");
		}
		return info;
	}
}
