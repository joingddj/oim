package com.oimchat.server.general.kernel.work.module.business.group.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.business.group.dao.GroupMemberExtendBusinessDAO;
import com.oimchat.server.general.kernel.work.module.business.group.data.query.GroupMemberBusinessQuery;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.onlyxiahui.common.data.common.data.Page;

/**
 * 
 * Date 2019-01-26 19:32:01<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
public class GroupMemberExtendBusinessManager {

	@Autowired
	private GroupMemberExtendBusinessDAO groupMemberDAO;

	/**
	 * 
	 * 分页条件查询<br>
	 * Date 2020-04-12 18:04:23<br>
	 * 
	 * @param query
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	public List<GroupMember> queryList(GroupMemberBusinessQuery query, Page page) {
		return groupMemberDAO.queryList(query, page);
	}
}
