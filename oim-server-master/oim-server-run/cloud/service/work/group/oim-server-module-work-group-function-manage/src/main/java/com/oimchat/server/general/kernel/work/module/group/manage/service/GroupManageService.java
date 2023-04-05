package com.oimchat.server.general.kernel.work.module.group.manage.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupNumberDAO;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupMemberQuery;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupQuery;
import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberManager;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;

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
public class GroupManageService {

	@Autowired
	GroupManager groupManager;

	@Autowired
	private GroupNumberDAO groupNumberDAO;

	@Autowired
	GroupMemberManager groupMemberManager;

	/**
	 * 添加<br>
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	public void add(Group data) {
		Long number = groupNumberDAO.getNumber();

		data.setNumber(number);
		data.setCreatedDateTime(DateUtil.getCurrentDateTime());
		if (null == data.getHead() || "".equals(data.getHead())) {
			int i = new Random().nextInt(8);
			i = i + 1;
			data.setHead(i + "");
		}
		groupManager.add(data);
	}

	/**
	 * 修改<br>
	 * 
	 * @param data
	 * @return int
	 * @since 1.0.0
	 */
	public int update(Group data) {
		return groupManager.updateSelective(data);
	}

	/**
	 * 
	 * 新增或者修改<br>
	 * 
	 * @param data
	 * @return
	 * @since 1.0.0
	 */
	public Info addOrUpdate(Group data) {
		Info info = new Info();
		if (null == data.getId() || data.getId().isEmpty()) {
			Long number = groupNumberDAO.getNumber();

			data.setNumber(number);
			data.setCreatedDateTime(DateUtil.getCurrentDateTime());
			if (null == data.getHead() || "".equals(data.getHead())) {
				int i = new Random().nextInt(8);
				i = i + 1;
				data.setHead(i + "");
			}
			groupManager.add(data);
		} else {
			groupManager.updateSelective(data);
		}
		return info;
	}

	/**
	 * 根据id查询<br>
	 * 
	 * @param id
	 * @return Group
	 * @since 1.0.0
	 */
	public Group getById(String id) {
		return groupManager.getById(id);
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
		GroupMemberQuery q = new GroupMemberQuery();
		q.setGroupId(id);
		boolean has = 0 < groupMemberManager.queryCount(q);
		if (has) {
			info.addWarning("3.001", "群中已经有成员，不能删除！");
		} else {
			groupManager.deleteById(id);
		}
		return info;
	}

	/**
	 * 分页查询 <br>
	 * 
	 * @param page
	 * @param query
	 * @return List<Group>
	 * @since 1.0.0
	 */
	public List<Group> queryList(Page page, GroupQuery query) {
		return groupManager.queryList(page, query);
	}
}