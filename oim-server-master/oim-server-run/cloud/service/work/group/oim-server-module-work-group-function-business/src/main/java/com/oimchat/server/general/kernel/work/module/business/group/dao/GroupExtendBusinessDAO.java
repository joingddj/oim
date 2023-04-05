package com.oimchat.server.general.kernel.work.module.business.group.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.oimchat.server.general.kernel.work.module.business.group.data.query.GroupBusinessQuery;
import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.onlyxiahui.aware.basic.dao.BaseEntityDAO;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.extend.query.hibernate.QueryWrapper;
import com.onlyxiahui.extend.query.hibernate.parameter.ValueOptionInfo;
import com.onlyxiahui.extend.query.hibernate.parameter.ValueOptionType;
import com.onlyxiahui.extend.query.hibernate.util.QueryUtil;
import com.onlyxiahui.extend.query.page.QueryPage;

/**
 * 描述：
 * 
 * @author XiaHui
 * @version 0.0.1
 */
@Repository
public class GroupExtendBusinessDAO extends BaseEntityDAO<Group> {

	public List<Group> queryList(GroupBusinessQuery query, Page page) {
		QueryWrapper queryWrapper = new QueryWrapper();
		QueryPage queryPage = queryWrapper.setPage(page.getNumber(), page.getSize());
		List<ValueOptionInfo> optionList = new ArrayList<ValueOptionInfo>();
		optionList.add(new ValueOptionInfo("queryText", ValueOptionType.likeAll));// 设置查询条件为like
		QueryUtil.getQueryWrapperType(query, queryWrapper, optionList);
		List<Group> list = this.queryListByName("group.queryGroupList", queryWrapper, Group.class);
		page.setTotalCount(queryPage.getTotalCount());
		page.setTotalPage(queryPage.getTotalPage());
		return list;
	}
}
