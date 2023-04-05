package com.oimchat.server.general.kernel.work.module.group.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.message.bean.Info;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.oimchat.server.general.kernel.work.module.group.base.function.GroupBusinessFunction;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupMemberQuery;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberManager;

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
public class GroupMemberManageService {

	@Autowired
	GroupMemberManager groupMemberManager;
	@Autowired
	private GroupBusinessFunction groupBusinessFunction;

	/**
	 * 添加<br>
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	public void add(GroupMember data) {
		groupMemberManager.add(data);
	}

	/**
	 * 修改<br>
	 * 
	 * @param data
	 * @return int
	 * @since 1.0.0
	 */
	public int update(GroupMember data) {
		return groupMemberManager.updateSelective(data);
	}

	/**
	 * 
	 * 新增或者修改<br>
	 * 
	 * @param data
	 * @return
	 * @since 1.0.0
	 */
	public Info addOrUpdate(GroupMember data) {
		Info info = new Info();
		if (null == data.getId() || data.getId().isEmpty()) {
			groupMemberManager.add(data);
		} else {
			groupMemberManager.updateSelective(data);
		}
		return info;
	}

	/**
	 * 根据id查询<br>
	 * 
	 * @param id
	 * @return GroupMember
	 * @since 1.0.0
	 */
	public GroupMember getById(String id) {
		return groupMemberManager.getById(id);
	}

	/**
	 * 根据id删除<br>
	 * 
	 * @param id
	 * @param updatedUserId
	 * @return int
	 * @since 1.0.0
	 */
	public Info deleteById(String id, String updatedUserId) {
		Info info = new Info();
		GroupMember gm = getById(id);
		if (gm != null) {
			info = groupBusinessFunction.quit(gm.getGroupId(), gm.getUserId());
		}
		// int count = groupMemberManager.deleteById(id);
		return info;
	}

	/**
	 * 分页查询 <br>
	 * 
	 * @param page
	 * @param query
	 * @return List<GroupMember>
	 * @since 1.0.0
	 */
	public List<GroupMember> queryList(Page page, GroupMemberQuery query) {
		return groupMemberManager.queryList(page, query);
	}
}