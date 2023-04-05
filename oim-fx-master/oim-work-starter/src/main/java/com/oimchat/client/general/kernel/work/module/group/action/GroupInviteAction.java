package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.service.GroupInviteService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群邀请业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.008")
public class GroupInviteAction extends AbstractMaterial {

	public GroupInviteAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送邀请申请(群主/管理员)<br>
	 * Date 2020-04-11 15:28:24<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void onReceiveInviteApply(
			@Define("body.groupId") String groupId,
			@Define("body.applyId") String applyId) {
		GroupInviteService service = this.appContext.getObject(GroupInviteService.class);
		service.onReceiveInviteApply(groupId, applyId);
	}

	/**
	 * 
	 * 推送邀请(被邀请者)<br>
	 * Date 2020-04-12 21:24:03<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void onReceiveInviteInfo(
			@Define("body.groupId") String groupId,
			@Define("body.applyId") String applyId) {
		GroupInviteService service = this.appContext.getObject(GroupInviteService.class);
		service.onReceiveInviteInfo(groupId, applyId);
	}
}
