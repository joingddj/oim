package com.oimchat.server.general.kernel.work.module.group.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupMemberDAO;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupMemberQuery;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.oimchat.server.general.kernel.work.module.group.base.function.GroupBusinessFunction;
import com.oimchat.server.general.kernel.work.module.group.base.function.GroupJoinFunction;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberManager;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupMemberManageExtendService {
	@Autowired
	GroupMemberDAO groupMemberDAO;
	@Autowired
	GroupMemberManager groupMemberManager;
	@Autowired
	GroupJoinFunction groupJoinFunction;
	@Autowired
	GroupBusinessFunction groupBusinessFunction;

	public Info add(String groupId, List<String> userIds) {

		Info info = new Info();

		if (StringUtil.isNotBlank(groupId) && null != userIds && !userIds.isEmpty()) {
			List<String> adds = new ArrayList<>();
			for (String userId : userIds) {
				GroupMemberQuery q = new GroupMemberQuery();
				q.setGroupId(groupId);
				q.setUserId(userId);
				boolean has = null != groupMemberManager.get(q);
				if (!has) {
					adds.add(userId);
				}
			}
			for (String userId : adds) {
				GroupMember data = new GroupMember();
				data.setGroupId(groupId);
				data.setUserId(userId);
				groupJoinFunction.join(groupId, userId, "");
			}
		}
		return info;
	}

	public Info delete(String groupId, List<String> userIds) {

		Info info = new Info();

		if (StringUtil.isNotBlank(groupId) && null != userIds && !userIds.isEmpty()) {
			List<String> adds = new ArrayList<>();
			for (String userId : userIds) {
				GroupMemberQuery q = new GroupMemberQuery();
				q.setGroupId(groupId);
				q.setUserId(userId);
				boolean has = null != groupMemberManager.get(q);
				if (has) {
					adds.add(userId);
				}
			}
			for (String userId : adds) {
				GroupMemberQuery query = new GroupMemberQuery();
				query.setGroupId(groupId);
				query.setUserId(userId);

				groupBusinessFunction.quit(groupId, userId);
				// groupMemberDAO.deleteByCondition(query);
			}
		}
		return info;
	}
}