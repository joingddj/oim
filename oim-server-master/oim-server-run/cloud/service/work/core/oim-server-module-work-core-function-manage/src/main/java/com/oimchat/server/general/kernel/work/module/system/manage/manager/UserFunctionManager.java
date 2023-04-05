package com.oimchat.server.general.kernel.work.module.system.manage.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.system.manage.dao.FunctionDAO;
import com.oimchat.server.general.kernel.work.module.system.manage.dao.RoleDAO;
import com.oimchat.server.general.kernel.work.module.system.manage.dao.RoleFunctionDAO;
import com.oimchat.server.general.kernel.work.module.system.manage.dao.UserRoleDAO;
import com.oimchat.server.general.kernel.work.module.system.manage.entity.Function;
import com.oimchat.server.general.kernel.work.module.system.manage.entity.Role;
import com.oimchat.server.general.kernel.work.module.system.manage.entity.RoleFunction;
import com.oimchat.server.general.kernel.work.module.system.manage.entity.UserRole;
import com.onlyxiahui.aware.basic.work.type.lang.BooleanEnum;

/**
 * 
 * <br>
 * Date <br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]
 * @since 1.0.0
 */
@Service
public class UserFunctionManager {

	@Autowired
	FunctionDAO functionDAO;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	RoleFunctionDAO roleFunctionDAO;
	@Autowired
	UserRoleDAO userRoleDAO;

	public List<Function> getFunctionListByUserId(String userId) {
		List<Function> list = new ArrayList<>();
		List<String> roleIds = new ArrayList<>();
		List<String> functionIds = new ArrayList<>();

		List<UserRole> urs = userRoleDAO.getListByUserId(userId);
		for (UserRole ur : urs) {
			roleIds.add(ur.getRoleId());
		}

		List<Role> roles = roleDAO.getListByIds(roleIds);
		roleIds.clear();
		for (Role r : roles) {
			if (BooleanEnum.FALSE.isEquals(r.getIsDisable())) {
				roleIds.add(r.getId());
			}
		}

		List<RoleFunction> rms = roleFunctionDAO.getListByRoleIds(roleIds);
		for (RoleFunction rm : rms) {
			functionIds.add(rm.getFunctionId());
		}

		List<Function> fs = functionDAO.getListByIds(functionIds);
		for (Function f : fs) {
			if (BooleanEnum.FALSE.isEquals(f.getIsDisable())) {
				list.add(f);
			}
		}
		return list;
	}

	public List<Function> getAllFunctionList() {
		return functionDAO.allList();
	}
}