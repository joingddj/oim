package com.oimchat.server.general.kernel.work.module.group.base.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.basic.common.util.KeyUtil;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupRelation;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupCategoryExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupRelationExtendManager;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupMemberPush;
import com.oimchat.server.general.kernel.work.module.group.base.push.GroupRelationPush;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-04-22 14:06:06<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Service
public class GroupJoinFunction {

	@Autowired
	private GroupMemberExtendManager groupMemberManager;
	@Autowired
	private GroupRelationExtendManager groupRelationManager;
	@Autowired
	private GroupCategoryExtendManager groupCategoryExtendManager;
	@Autowired
	private GroupMemberPush groupMemberPush;
	@Autowired
	private GroupRelationPush groupRelationPush;

	public Info join(
			String groupId,
			String userId,
			String categoryId,
			String remark) {
		Info info = new Info();
		GroupRelation gr = groupRelationManager.getByGroupId(userId, groupId);
		if (null == gr) {
			gr = groupRelationManager.add(
					groupId,
					userId,
					categoryId,
					remark);
		}

		if (!groupMemberManager.inGroup(groupId, userId)) {
			groupMemberManager.add(groupId, userId);
		}

		List<String> userIds = groupMemberManager.getGroupMemberUserIdList(groupId);
		groupRelationPush.pushAdd(userId, KeyUtil.getKey(), groupId);
		groupMemberPush.pushAdd(userIds, KeyUtil.getKey(), groupId, userId);

		return info;
	}

	public Info join(
			String groupId,
			String userId,
			String remark) {
		String categoryId = groupCategoryExtendManager.getOrCreateDefaultCategoryId(userId);
		return join(
				groupId,
				userId,
				categoryId,
				remark);
	}
}
