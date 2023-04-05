package com.oimchat.client.general.kernel.work.module.contact.action;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddResultData;
import com.oimchat.client.general.kernel.work.module.contact.service.ContactService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 联系人业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.2.0
 */
@ActionMapping("1.2.001")
public class ContactAction extends AbstractMaterial {

	public ContactAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送收到请求被添加为联系人消息<br>
	 * Date 2019-01-27 19:24:21<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void onReceiveApplyInfo(
			@Define("body.applyId") String applyId) {
		ContactService service = this.appContext.getObject(ContactService.class);
		service.onReceiveApplyInfo(applyId);
	}

	/**
	 * 
	 * 推送请求添加为联系人，被添加人处理后推送给请求人 <br>
	 * Date 2019-05-12 15:29:07<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void onReceiveApplyResult(
			@Define("body") ContactAddResultData data) {
		ContactService service = this.appContext.getObject(ContactService.class);
		service.onReceiveApplyResult(data);
	}

	/**
	 * 
	 * 推送被拉黑/移除黑名单<br>
	 * Date 2020-04-10 22:44:30<br>
	 * 
	 * @param contactUserId 联系人
	 * @param isBlocked     是否被拉黑
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updateBlocked(
			@Define("body.contactUserId") String contactUserId,
			@Define("body.isBlocked") String isBlocked) {
		ContactService service = this.appContext.getObject(ContactService.class);
		service.updateBlocked(contactUserId, isBlocked);
	}
}
