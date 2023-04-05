package com.oimchat.server.general.kernel.work.module.business.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupJoinVerifyAnswerDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupJoinVerifyAnswer;

/**
 * Date 2019-01-26 15:06:33<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
public class GroupJoinVerifyAnswerService {

	@Autowired
	private GroupJoinVerifyAnswerDAO groupJoinVerifyAnswerDAO;

	public List<GroupJoinVerifyAnswer> getListByApplyIds(List<String> groupIds, List<String> applyIds) {
		return groupJoinVerifyAnswerDAO.getListByApplyIds(groupIds, applyIds);
	}
}
