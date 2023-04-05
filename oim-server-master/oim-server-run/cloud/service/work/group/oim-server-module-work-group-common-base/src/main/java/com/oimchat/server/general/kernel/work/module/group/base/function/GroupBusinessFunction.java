package com.oimchat.server.general.kernel.work.module.group.base.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.basic.common.util.KeyUtil;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupExtendDAO;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupMemberExtendDAO;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupNumberDAO;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupRelationDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupCategory;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupRelation;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupCategoryExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupBusinessPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupInfoPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupMemberPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupRelationPush;
import com.onlyxiahui.aware.basic.work.business.error.ErrorCode;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.result.ResultBodyMessage;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;

/**
 * 
 * Date 2019-01-21 11:39:20<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
@Transactional
public class GroupBusinessFunction {

	@Autowired
	private GroupExtendDAO groupDAO;
	@Autowired
	private GroupNumberDAO groupNumberDAO;
	@Autowired
	private GroupMemberExtendDAO groupMemberDAO;
	@Autowired
	private GroupRelationDAO groupRelationDAO;

	@Autowired
	private GroupCategoryExtendManager groupCategoryManager;
	@Autowired
	private GroupMemberExtendManager groupMemberManager;

	@Autowired
	private GroupInfoPush groupInfoPush;
	@Autowired
	private GroupBusinessPush groupBusinessPush;
	@Autowired
	private GroupMemberPush groupMemberPush;
	@Autowired
	private GroupRelationPush groupRelationPush;

	public ResultBodyMessage<Group> add(String key, String userId, Group group) {
		// 生成数子账号
		Long number = groupNumberDAO.getNumber();

		group.setNumber(number);
		group.setCreatedDateTime(DateUtil.getCurrentDateTime());
		if (null == group.getHead() || "".equals(group.getHead())) {
			int i = new Random().nextInt(8);
			i = i + 1;
			group.setHead(i + "");
		}
		groupDAO.save(group);

		GroupCategory category = groupCategoryManager.getOrCreateDefault(userId);

		GroupRelation relation = new GroupRelation();
		relation.setUserId(userId);
		relation.setGroupId(group.getId());
		relation.setCategoryId(category.getId());

		groupRelationDAO.save(relation);

		GroupMember groupMember = new GroupMember();
		groupMember.setUserId(userId);
		groupMember.setGroupId(group.getId());
		groupMember.setPosition(GroupMember.position_owner);
		groupMemberDAO.save(groupMember);

		// TODO 推送新增群信息
		// groupPush.pushAdd(KeyUtil.getKey(), group, userId);

		groupRelationPush.pushAdd(userId, KeyUtil.getKey(), group.getId());
		ResultBodyMessage<Group> message = new ResultBodyMessage<>(group);
		return message;
	}

	public Info update(String key, String userId, Group group) {
		Info info = new Info();
		if (null != group && null != group.getId()) {
			String groupId = group.getId();
			String position = groupMemberManager.getPosition(groupId, userId);
			boolean isOwner = groupMemberManager.isOwner(position);
			if (isOwner) {
				handleUpdate(group);
				groupDAO.updateSelective(group);
				List<String> userIds = groupMemberManager.getGroupMemberUserIdList(groupId);
				groupInfoPush.pushUpdate(userIds, KeyUtil.getKey(), groupId);
			}
		}
		return info;
	}

	public void handleUpdate(Group group) {
		if (null != group) {
			group.setNumber(null);
			group.setAvatar(null);
			group.setCreatedDateTime(null);
			group.setCreatedTimestamp(null);
		}
	}

	/**
	 * 
	 * Date 2019-01-27 11:08:11<br>
	 * Description 退出
	 * 
	 * @author XiaHui<br>
	 * @param groupId
	 * @param userId
	 * @return
	 * @since 1.0.0
	 */
	public Info quit(String groupId, String userId) {

		Info info = new Info();
		// 群主不可以退群
		boolean isOwner = groupMemberManager.isOwner(groupId, userId);

		if (!isOwner) {
			boolean mark = groupMemberDAO.deleteByGroupIdUserId(groupId, userId);
			groupRelationDAO.deleteGroupCategoryMember(userId, groupId);

			if (!mark) {
				info.addWarning(ErrorCode.business.code("003"), "退群失败！");
			} else {

				// TODO 推送信息通知管理员和群主
				List<String> aoIds = groupMemberManager.getGroupAdminAndOwnerUserIdList(groupId);
				groupBusinessPush.pushQuit(aoIds, KeyUtil.getKey(), groupId, userId);

				List<String> userIds = groupMemberManager.getGroupMemberUserIdList(groupId);

				// 用户退出后推送群成员，移除群成员列表
				groupMemberPush.pushDelete(userIds, KeyUtil.getKey(), groupId, userId);

				List<String> ids = new ArrayList<>();
				ids.add(userId);
				// 用户退出后推送删除群分组关系，推送方式可以同步其他同时在线设备
				groupRelationPush.pushDelete(ids, KeyUtil.getKey(), groupId);
			}
		} else if (isOwner) {
			info.addWarning(ErrorCode.business.code("001"), "群主不可以退群！");
		}
		return info;
	}
}
