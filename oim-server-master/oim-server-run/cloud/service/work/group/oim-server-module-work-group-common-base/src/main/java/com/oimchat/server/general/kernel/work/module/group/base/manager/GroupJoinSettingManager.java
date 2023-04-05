package com.oimchat.server.general.kernel.work.module.group.base.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupJoinSettingDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupJoinSetting;

/**
 * 
 * Date 2019-01-20 13:37:17<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
public class GroupJoinSettingManager {

	@Autowired
	private GroupJoinSettingDAO groupJoinSettingDAO;

	public GroupJoinSetting getByGroupId(String groupId) {
		GroupJoinSetting uhs = groupJoinSettingDAO.getByGroupId(groupId);
		if (null == uhs) {
			uhs = new GroupJoinSetting();
			uhs.setGroupId(groupId);
			uhs.setJoinType(GroupJoinSetting.join_verify_type_auth);
			uhs.setInviteType(GroupJoinSetting.invite_type_auth);
		}
		return uhs;
	}
}
