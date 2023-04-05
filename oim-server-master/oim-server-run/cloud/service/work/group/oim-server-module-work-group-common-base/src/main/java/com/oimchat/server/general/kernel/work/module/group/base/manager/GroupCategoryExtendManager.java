package com.oimchat.server.general.kernel.work.module.group.base.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupCategoryExtendDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupCategory;

/**
 * 
 * @author: XiaHui
 *
 */
@Service
public class GroupCategoryExtendManager {

	@Autowired
	GroupCategoryExtendDAO groupCategoryDAO;

	/**
	 * 
	 * Date 2019-01-20 20:58:43<br>
	 * Description 获取默认的群分组，没有则创建
	 * 
	 * @author XiaHui<br>
	 * @param userId
	 * @return
	 * @since 1.0.0
	 */
	public GroupCategory getOrCreateDefault(String userId) {
		GroupCategory bean = groupCategoryDAO.getDefaultGroupCategory(userId);
		if (null == bean) {
			bean = new GroupCategory();
			bean.setUserId(userId);
			bean.setName("我的聊天群");
			bean.setSort(0);
			bean.setType(GroupCategory.type_default);
			groupCategoryDAO.save(bean);
		}
		return bean;
	}

	/**
	 * 
	 * Date 2019-01-24 22:38:28<br>
	 * Description
	 * 
	 * @author XiaHui<br>
	 * @param userId
	 * @return
	 * @since 1.0.0
	 */
	public String getOrCreateDefaultCategoryId(String userId) {
		GroupCategory bean = getOrCreateDefault(userId);
		return null != bean ? bean.getId() : "";
	}
}
