package com.oimchat.server.general.kernel.work.module.system.manage.data;

import java.util.List;

import com.oimchat.server.general.kernel.work.module.core.base.data.dto.UserData;

/**
 * @author: XiaHui
 */
public class Admin extends UserData {

	private List<UserRoleInfo> userRoleInfos;

	public List<UserRoleInfo> getUserRoleInfos() {
		return userRoleInfos;
	}

	public void setUserRoleInfos(List<UserRoleInfo> userRoleInfos) {
		this.userRoleInfos = userRoleInfos;
	}
}
