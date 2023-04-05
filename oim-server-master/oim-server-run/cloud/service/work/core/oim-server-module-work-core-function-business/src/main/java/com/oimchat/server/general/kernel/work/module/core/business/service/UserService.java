package com.oimchat.server.general.kernel.work.module.core.business.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oimchat.server.general.kernel.work.define.user.store.UserStore;
import com.oimchat.server.general.kernel.work.module.core.base.dao.UserDAO;
import com.oimchat.server.general.kernel.work.module.core.base.data.dto.UserData;
import com.oimchat.server.general.kernel.work.module.core.base.data.dto.UserDataQuery;
import com.oimchat.server.general.kernel.work.module.core.base.entity.User;
import com.oimchat.server.general.kernel.work.module.core.base.manager.UserExtendManager;
import com.oimchat.server.general.kernel.work.module.core.base.type.UserStatusEnum;
import com.onlyxiahui.common.data.common.data.Page;

/**
 * 
 * Date 2019-01-20 12:35:32<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Service
@Transactional
public class UserService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserStore userStore;
	@Autowired
	private UserExtendManager userExtendManager;

	public User getUser(String userId) {
		return userDAO.get(userId);
	}

	/**
	 * 
	 * Date 2019-01-20 13:07:08<br>
	 * Description 获取用户信息
	 * 
	 * @author XiaHui<br>
	 * @param id
	 * @return
	 * @since 1.0.0
	 */
	public UserData getUserDataById(String id) {
		UserData userData = userDAO.getUserDataById(id);
		return userData;
	}

	/**
	 * 
	 * Date 2019-01-20 13:07:25<br>
	 * Description 条件分页查询
	 * 
	 * @author XiaHui<br>
	 * @param userQuery
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	public List<UserData> queryUserDataList(UserDataQuery userQuery, Page page) {
		List<UserData> userDataList = userDAO.queryUserList(userQuery, page);
		return userDataList;
	}

	public void removeUser(String userId) {
		userStore.putStatus(userId, UserStatusEnum.offline.getCode().toString());
		sendUserStatus(userId, UserStatusEnum.offline.getCode().toString());
	}

	public void setUserStatus(List<UserData> userDataList) {
		if (null != userDataList) {
			for (UserData userData : userDataList) {
				setUserStatus(userData);
			}
		}
	}

	public void setUserStatus(UserData userData) {
		if (null != userData) {
			String status = userStore.getStatus(userData.getId());
			status = (null == status) ? UserStatusEnum.offline.getCode().toString() : status;
			userData.setStatus(status);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////

	public void sendUserStatus(String userId, String status) {
		userExtendManager.updateStatus(userId, status);
	}

	public List<UserData> getUserDataList(List<String> userIds) {
		return userDAO.getUserDataList(userIds);
	}
}
