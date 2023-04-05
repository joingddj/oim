package com.oimchat.server.general.kernel.work.module.group.manage.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.oimchat.server.basic.common.work.app.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.oimchat.server.general.kernel.work.module.group.base.data.query.GroupQuery;
import com.oimchat.server.general.kernel.work.module.group.manage.service.GroupManageService;

import com.onlyxiahui.aware.basic.work.business.error.ErrorCode;
import com.onlyxiahui.aware.common.auth.annotation.PermissionMapping;
import com.onlyxiahui.aware.common.auth.type.PermissionType;
import com.onlyxiahui.common.action.description.annotation.DocTitle;
import com.onlyxiahui.common.action.description.annotation.DocModule;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.result.ResultBodyMessage;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
@DocTitle("群管理")
@DocModule(superKey = "manage")
@RestController
public class GroupController {

	@Autowired
	GroupManageService groupManageService;

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param page
	 * @return PageResult<List<Group>>
	 * @since 1.0.0
	 */
	@DocTitle("分页查询")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/list")
	public PageResult<List<Group>> list(
			@Define("body.query") GroupQuery query,
			@Define("body.page") Page page) {

		List<Group> list = groupManageService.queryList(page, query);
		return new PageResult<List<Group>>(page, list);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return Group
	 * @since 1.0.0
	 */
	@DocTitle("根据id查询")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/get.by.id")
	public Group get(@Validated @Define("body.id") String id) {
		Group data = groupManageService.getById(id);
		return (data);
	}

	/**
	 * 新增
	 * 
	 * @param data
	 * @return ResultBodyMessage<Group>
	 * @since 1.0.0
	 */
	@DocTitle("新增")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/add")
	public ResultBodyMessage<Group> add(
			@Validated @Define("body") Group data,
			UserSession us) {

		data.setId(null);
		data.setCreatedTimestamp(null);
		data.setCreatedDateTime(null);
		data.setUpdatedDateTime(null);
		data.setUpdatedTimestamp(null);
		groupManageService.add(data);
		ResultBodyMessage<Group> rm = new ResultBodyMessage<>(data);
		return rm;
	}

	/**
	 * 修改
	 * 
	 * @param data
	 * @return ResultBodyMessage<Group>
	 * @since 1.0.0
	 */
	@DocTitle("修改")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/update.by.id")
	public ResultBodyMessage<Group> update(
			@Validated @Define("body") Group data,
			UserSession us) {

		data.setCreatedTimestamp(null);
		data.setCreatedDateTime(null);
		data.setUpdatedDateTime(null);
		data.setUpdatedTimestamp(null);
		int result = groupManageService.update(data);
		ResultBodyMessage<Group> rm = new ResultBodyMessage<>(data);
		if (result < 0) {
			rm.addWarning(ErrorCode.business.code("001"), "修改失败！");
		}
		return rm;
	}

	/**
	 * 新增或者修改
	 * 
	 * @param data
	 * @return ResultBodyMessage<Group>
	 * @since 1.0.0
	 */
	@DocTitle("新增或者修改")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/add.or.update")
	public ResultBodyMessage<Group> addOrUpdate(
			@Validated @Define("body") Group data,
			UserSession us) {

		data.setCreatedTimestamp(null);
		data.setCreatedDateTime(null);
		data.setUpdatedDateTime(null);
		data.setUpdatedTimestamp(null);
		groupManageService.addOrUpdate(data);
		ResultBodyMessage<Group> rm = new ResultBodyMessage<>(data);
		return rm;
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return Info
	 */
	@DocTitle("根据id删除")
	@PermissionMapping(type = PermissionType.grant)
	@ActionMapping("/manage/v1/group/group/delete.by.id")
	public Info delete(
			@Valid @NotEmpty @Define("body.id") String id,
			UserSession us) {
		Info info = groupManageService.deleteById(id, us.getUserId());
		return info;
	}
}