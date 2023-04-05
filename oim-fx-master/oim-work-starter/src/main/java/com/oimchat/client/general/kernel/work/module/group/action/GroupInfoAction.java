package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.service.GroupInfoService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群信息业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.001")
public class GroupInfoAction extends AbstractMaterial {

	public GroupInfoAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送群更新<br>
	 * Date 2020-04-11 15:27:32<br>
	 * 
	 * @param id 群id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void update(
			@Define("body.id") String id) {
		GroupInfoService service = this.appContext.getObject(GroupInfoService.class);
		service.update(id);
	}

	/**
	 * 推送群修改头像<br>
	 * Date 2020-04-11 15:27:48<br>
	 * 
	 * @param id 群id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void updateHead(
			@Define("body.id") String id) {
		GroupInfoService service = this.appContext.getObject(GroupInfoService.class);
		service.updateHead(id);
	}
}
