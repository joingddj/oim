package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinResultData;
import com.oimchat.client.general.kernel.work.module.group.service.GroupJoinService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 加入群业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.007")
public class GroupJoinAction extends AbstractMaterial {

	public GroupJoinAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送加入申请(推送给群主/管理员)<br>
	 * Date 2020-04-11 15:30:42<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void onReceiveJoinApply(
			@Define("body.groupId") String groupId,
			@Define("body.applyId") String applyId) {
		GroupJoinService service = this.appContext.getObject(GroupJoinService.class);
		service.onReceiveJoinApply(groupId, applyId);
	}

	/**
	 * 
	 * 推送申请处理结果(推送给申请者) <br>
	 * Date 2020-04-11 15:31:58<br>
	 * 
	 * @param result
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void onReceiveJoinResult(
			@Define("body") GroupJoinResultData result) {
		GroupJoinService service = this.appContext.getObject(GroupJoinService.class);
		service.onReceiveJoinResult(result);
	}
}
