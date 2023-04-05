package com.oimchat.server.general.kernel.work.module.business.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.oimchat.server.general.kernel.work.module.group.base.function.GroupBusinessFunction;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.result.ResultBodyMessage;

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
public class GroupBusinessService {

	@Autowired
	private GroupBusinessFunction groupBusinessFunction;

	public ResultBodyMessage<Group> add(String key, String userId, Group group) {
		return groupBusinessFunction.add(key, userId, group);
	}

	public Info update(String key, String userId, Group group) {
		return groupBusinessFunction.update(key, userId, group);
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
		return groupBusinessFunction.quit(groupId, userId);
	}
}
