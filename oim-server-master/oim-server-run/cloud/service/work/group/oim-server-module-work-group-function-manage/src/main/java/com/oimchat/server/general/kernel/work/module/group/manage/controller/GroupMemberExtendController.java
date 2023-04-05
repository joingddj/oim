package com.oimchat.server.general.kernel.work.module.group.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.oimchat.server.basic.common.work.app.UserSession;
import com.oimchat.server.general.kernel.work.module.group.manage.service.GroupMemberManageExtendService;
import com.onlyxiahui.aware.common.auth.annotation.PermissionMapping;
import com.onlyxiahui.aware.common.auth.type.PermissionType;
import com.onlyxiahui.common.action.description.annotation.DocModule;
import com.onlyxiahui.common.action.description.annotation.DocTitle;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
@DocTitle("群成员管理")
@DocModule(superKey = "manage")
@RestController
public class GroupMemberExtendController {

	@Autowired
	GroupMemberManageExtendService groupMemberManageExtendService;

	@DocTitle("新增")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group.member/add.by.user.ids")
	public Info addByMutualId(
			@Define("body.groupId") String groupId,
			@Define("body.userIds") List<String> userIds,
			UserSession us) {
		Info info = groupMemberManageExtendService.add(groupId, userIds);
		return info;
	}

	@DocTitle("删除")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group.member/delete.by.user.ids")
	public Info deleteByMutualId(
			@Define("body.groupId") String groupId,
			@Define("body.userIds") List<String> userIds,
			UserSession us) {
		Info info = groupMemberManageExtendService.delete(groupId, userIds);
		return info;
	}
}