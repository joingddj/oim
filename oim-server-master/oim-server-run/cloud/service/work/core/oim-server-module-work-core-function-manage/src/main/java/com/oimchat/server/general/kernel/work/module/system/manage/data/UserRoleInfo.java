/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.server.general.kernel.work.module.system.manage.data;

import com.oimchat.server.general.kernel.work.module.system.manage.entity.UserRole;

/**
 * 
 * @author xiahui
 */
public class UserRoleInfo extends UserRole{

	private String roleName;// 角色Id

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
