package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.service.GroupBusinessService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.005")
public class GroupBusinessAction extends AbstractMaterial {

	public GroupBusinessAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送解散群<br>
	 * Date 2020-04-11 13:42:13<br>
	 * 
	 * @param groupId
	 * @param ownerUserId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void disband(
			@Define("body.groupId") String groupId,
			@Define("body.ownerUserId") String ownerUserId) {
		GroupBusinessService serivce = this.appContext.getObject(GroupBusinessService.class);
		serivce.disband(groupId, ownerUserId);
	}

	/**
	 * 
	 * 推送退出群(通知管理员和群主)<br>
	 * Date 2020-04-11 13:43:17<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void quit(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId) {
		GroupBusinessService serivce = this.appContext.getObject(GroupBusinessService.class);
		serivce.quit(groupId, userId);
	}
}
