package com.oimchat.client.general.kernel.work.module.group.action;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.service.GroupRelationService;
import com.oimchat.client.general.kernel.work.module.group.service.PersonalGroupMemberService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群分组关系业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.003")
public class GroupRelationAction extends AbstractMaterial {

	public GroupRelationAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送成功添加群<br>
	 * Date 2020-04-12 21:09:45<br>
	 * 
	 * @param groupId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void add(
			@Define("body.groupId") String groupId) {
		GroupRelationService service = this.appContext.getObject(GroupRelationService.class);
		service.add(groupId);

		PersonalGroupMemberService pgms = this.appContext.getObject(PersonalGroupMemberService.class);
		pgms.add(groupId);
	}

	/**
	 * 
	 * 推送修改备注<br>
	 * Date 2020-04-12 21:10:10<br>
	 * 
	 * @param groupId
	 * @param remark
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void updateRemark(
			@Define("body.groupId") String groupId,
			@Define("body.remark") String remark) {
		GroupRelationService service = this.appContext.getObject(GroupRelationService.class);
		service.updateRemark(groupId, remark);
	}

	/**
	 * 
	 * 推送移动分组<br>
	 * Date 2020-04-12 21:10:21<br>
	 * 
	 * @param groupIds
	 * @param categoryId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void moveCategory(
			@Define("body.groupIds") List<String> groupIds,
			@Define("body.categoryId") String categoryId) {
		GroupRelationService service = this.appContext.getObject(GroupRelationService.class);
		service.moveCategory(groupIds, categoryId);
	}

	/**
	 * 
	 * 推送删除<br>
	 * 解散群的时候批量推送给用户/退出群的时候推送给不同客户端<br>
	 * Date 2020-04-12 21:10:38<br>
	 * 
	 * @param groupId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void delete(
			@Define("body.groupId") String groupId) {
		GroupRelationService service = this.appContext.getObject(GroupRelationService.class);
		service.delete(groupId);
	}
}
