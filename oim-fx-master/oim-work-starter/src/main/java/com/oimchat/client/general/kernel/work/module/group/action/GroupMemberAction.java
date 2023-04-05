package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.service.GroupMemberService;
import com.oimchat.client.general.kernel.work.module.group.service.PersonalGroupMemberService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群成员业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.004")
public class GroupMemberAction extends AbstractMaterial {

	public GroupMemberAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送新增成员<br>
	 * Date 2020-04-12 17:14:42<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void add(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId) {
		GroupMemberService service = this.appContext.getObject(GroupMemberService.class);
		service.add(groupId, userId);
	}

	/**
	 * 
	 * 推送成员信息更新<br>
	 * Date 2020-04-12 17:14:59<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void update(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId) {
		GroupMemberService service = this.appContext.getObject(GroupMemberService.class);
		service.update(groupId, userId);

		PersonalGroupMemberService pgms = this.appContext.getObject(PersonalGroupMemberService.class);
		pgms.update(groupId, userId);
	}

	/**
	 * 
	 * 推送成员权限变更<br>
	 * Date 2020-04-12 17:15:20<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @param position
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updatePosition(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			@Define("body.position") String position) {
		GroupMemberService service = this.appContext.getObject(GroupMemberService.class);
		service.updatePosition(groupId, userId, position);

		PersonalGroupMemberService pgms = this.appContext.getObject(PersonalGroupMemberService.class);
		pgms.updatePosition(groupId, userId, position);
	}

	/**
	 * 
	 * 推送删除成员<br>
	 * Date 2020-04-12 17:15:41<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void delete(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId) {
		GroupMemberService service = this.appContext.getObject(GroupMemberService.class);
		service.delete(groupId, userId);
	}

	/**
	 * 
	 * 推送信息更新<br>
	 * Date 2020-05-14 19:30:00<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void updateInfo(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId) {
		GroupMemberService service = this.appContext.getObject(GroupMemberService.class);
		service.updateInfo(groupId, userId);

		PersonalGroupMemberService pgms = this.appContext.getObject(PersonalGroupMemberService.class);
		pgms.updateInfo(groupId, userId);
	}
}
