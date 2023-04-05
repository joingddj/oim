package com.oimchat.server.general.kernel.work.module.business.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupJoinSettingDAO;
import com.oimchat.server.general.kernel.work.module.group.base.dao.GroupJoinVerifyQuestionDAO;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupJoinSetting;
import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupJoinVerifyQuestion;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupJoinSettingManager;
import com.oimchat.server.general.kernel.work.module.group.base.manager.GroupMemberExtendManager;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;

/**
 * Date 2019-01-20 18:21:27<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
@Transactional
public class GroupJoinSettingService {

	@Autowired
	private GroupJoinSettingDAO groupJoinSettingDAO;
	@Autowired
	private GroupJoinVerifyQuestionDAO groupJoinVerifyQuestionDAO;
	@Autowired
	private GroupJoinSettingManager groupJoinSettingManager;
	@Autowired
	private GroupMemberExtendManager groupMemberManager;

	public GroupJoinSetting getByGroupId(String groupId) {
		GroupJoinSetting data = groupJoinSettingManager.getByGroupId(groupId);
		return data;
	}

	public List<GroupJoinVerifyQuestion> getVerifyQuestionListByGroupId(String groupId) {
		List<GroupJoinVerifyQuestion> list = groupJoinVerifyQuestionDAO.getListByGroupId(groupId);
		return list;
	}

	public Info saveOrUpdate(String ownerUserId, GroupJoinSetting data, List<GroupJoinVerifyQuestion> list) {
		Info info = new Info();
		try {
			String groupId = data.getGroupId();
			boolean isOwner = groupMemberManager.isOwner(groupId, ownerUserId);
			if (!isOwner) {
				info.addWarning("005", "没有权限修改！");
			}

			if (GroupJoinSetting.join_verify_type_answer.equals(data.getJoinType())) {
				if (null == data.getQuestion() || "".equals(data.getQuestion())) {
					info.addWarning("001", "问题不能为空！");
				}
				if (null == data.getAnswer() || "".equals(data.getAnswer())) {
					info.addWarning("002", "答案不能为空！");
				}
			}
			if (GroupJoinSetting.join_verify_type_confirm.equals(data.getJoinType())) {
				if (null == list || list.isEmpty()) {
					info.addWarning("003", "问题不能为空！");
				} else {
					int i = 1;
					for (GroupJoinVerifyQuestion d : list) {
						if (null == d.getQuestion() || "".equals(d.getQuestion())) {
							info.addWarning("004", "第" + i + "问题不能为空！");
						}
						i++;
					}
				}
			}
			if (info.isSuccess()) {
				saveOrUpdate(data);
				saveOrUpdate(data.getGroupId(), list);
			}
		} catch (Exception e) {
			info.addWarning("000", "系统异常");
		}

		return info;
	}

	public void saveOrUpdate(GroupJoinSetting data) {
		String groupId = data.getGroupId();
		String id = groupJoinSettingDAO.getIdByGroupId(groupId);
		if (null == id) {
			data.setCreatedDateTime(DateUtil.getCurrentDateTime());
			groupJoinSettingDAO.save(data);
		} else {
			data.setId(id);
			groupJoinSettingDAO.update(data);
		}
	}

	public void saveOrUpdate(String groupId, List<GroupJoinVerifyQuestion> list) {
		groupJoinVerifyQuestionDAO.deleteByGroupId(groupId);
		for (GroupJoinVerifyQuestion data : list) {
			data.setGroupId(groupId);
			groupJoinVerifyQuestionDAO.save(data);
		}
	}
}
