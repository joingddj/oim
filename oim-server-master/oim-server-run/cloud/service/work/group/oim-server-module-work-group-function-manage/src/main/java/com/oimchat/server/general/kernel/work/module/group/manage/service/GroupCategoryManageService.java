package com.oimchat.server.general.kernel.work.module.group.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.message.bean.Info;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupCategory;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupCategoryQuery;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupCategoryManager;

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
public class GroupCategoryManageService {

	@Autowired
	GroupCategoryManager groupCategoryManager;

	/**
	 * 添加<br>
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	public void add(GroupCategory data) {
		groupCategoryManager.add(data);
	}

	/**
	 * 修改<br>
	 * 
	 * @param data
	 * @return int
	 * @since 1.0.0
	 */
	public int update(GroupCategory data) {
		return groupCategoryManager.updateSelective(data);
	}

	/**
	 * 
	 * 新增或者修改<br>
	 * 
	 * @param data
	 * @return
	 * @since 1.0.0
	 */
	public Info addOrUpdate(GroupCategory data) {
		Info info = new Info();
		if (null == data.getId() || data.getId().isEmpty()) {
			groupCategoryManager.add(data);
		} else {
			groupCategoryManager.updateSelective(data);
		}
		return info;
	}
	
	/**
	 * 根据id查询<br>
	 * 
	 * @param id
	 * @return GroupCategory
	 * @since 1.0.0
	 */
	public GroupCategory getById(String id) {
		return groupCategoryManager.getById(id);
	}

	/**
	 * 根据id删除<br>
	 * 
	 * @param id
	 * @param updatedUserId
	 * @return int
	 * @since 1.0.0
	 */
	public int deleteById(String id, String updatedUserId) {
		int count = groupCategoryManager.deleteById(id);
		return count;
	}

	/**
	 * 分页查询 <br>
	 * 
	 * @param page
	 * @param query
	 * @return List<GroupCategory>
	 * @since 1.0.0
	 */
	public List<GroupCategory> queryList(Page page, GroupCategoryQuery query) {
		return groupCategoryManager.queryList(page, query);
	}
}