package com.oimchat.server.general.kernel.work.module.core.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.oimchat.server.general.kernel.work.module.core.base.data.dto.UserData;
import com.oimchat.server.general.kernel.work.module.core.base.data.query.UserQuery;
import com.oimchat.server.general.kernel.work.module.core.manage.service.UserService;
import com.onlyxiahui.aware.common.auth.annotation.PermissionMapping;
import com.onlyxiahui.aware.common.auth.type.PermissionType;
import com.onlyxiahui.common.action.description.annotation.DocModule;
import com.onlyxiahui.common.action.description.annotation.DocTitle;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 用户管理<br>
 * date 2018-07-19 09:29:16<br>
 * 
 * @author XiaHui<br>
 * @since
 */
@DocTitle("")
@DocModule(superKey = "manage")
@Controller
@ActionMapping("/manage")
public class UserExtendController {
	@Resource
	UserService userService;

	/**
	 * 列表<br>
	 * Date 2020-06-07 11:44:51<br>
	 * 
	 * @param query
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/v1/user/info/list.by.ids")
	public List<UserData> list(
			@Define("body.ids") List<String> ids) {
		List<UserData> list = userService.getUserDataListByIds(ids);
		return list;
	}
	
	
	/**
	 * 全部列表分页<br>
	 * Date 2020-06-07 11:44:51<br>
	 * 
	 * @param query
	 * @param page
	 * @return
	 * @since 1.0.0
	 */
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/v1/user/info/query.list")
	public PageResult<List<UserData>> list(
			@Define("body.query") UserQuery query,
			@Define("body.page") Page page) {
		List<UserData> list = userService.queryUserDataList(query, page);
		return new PageResult<>(page, list);
	}
}
