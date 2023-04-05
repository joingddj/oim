package com.oimchat.server.general.kernel.work.module.business.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.module.business.group.dao.GroupExtendBusinessDAO;
import com.oimchat.server.general.kernel.work.module.business.group.data.query.GroupBusinessQuery;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupExtendDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.onlyxiahui.common.data.common.data.Page;

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
public class GroupService {

	@Autowired
	private GroupExtendBusinessDAO groupExtendBusinessDAO;
	@Autowired
	private GroupExtendDAO groupDAO;

	public Group getById(String id) {
		Group group = groupDAO.get(Group.class, id);
		return group;
	}

	public Group getByNumber(String number) {
		Group group = groupDAO.getByNumber(number);
		return group;
	}

	public List<Group> queryList(GroupBusinessQuery query, Page page) {
		List<Group> list = groupExtendBusinessDAO.queryList(query, page);
		return list;
	}

	public List<Group> getListByIds(List<String> ids) {
		return groupDAO.getListByIds(ids);
	}
}
