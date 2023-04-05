package com.oimchat.client.general.kernel.work.module.contact.action;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.service.ContactRelationService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 联系人分组关系业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.2.0
 */
@ActionMapping("1.2.003")
public class ContactRelationAction extends AbstractMaterial {

	public ContactRelationAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送新增联系人<br>
	 * Date 2020-04-10 22:13:36<br>
	 * 
	 * @param contactUserId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void add(
			@Define("body.contactUserId") String contactUserId) {
		ContactRelationService service = this.appContext.getObject(ContactRelationService.class);
		service.add(contactUserId);
	}

	/**
	 * 推送修改联系人备注<br>
	 * Date 2020-04-10 22:14:02<br>
	 * 
	 * @param contactUserId
	 * @param remark
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void updateRemark(
			@Define("body.contactUserId") String contactUserId,
			@Define("body.remark") String remark) {
		ContactRelationService service = this.appContext.getObject(ContactRelationService.class);
		service.updateRemark(contactUserId, remark);
	}

	/**
	 * 
	 * 推送联系人移动分组<br>
	 * Date 2020-04-10 22:49:33<br>
	 * 
	 * @param key
	 * @param contactUserIds
	 * @param categoryId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void moveCategory(
			@Define("body.contactUserIds") List<String> contactUserIds,
			@Define("body.categoryId") String categoryId) {
		ContactRelationService service = this.appContext.getObject(ContactRelationService.class);
		service.moveCategory(contactUserIds, categoryId);
	}

	/**
	 * 
	 * 推送删除联系人<br>
	 * Date 2020-04-10 22:50:10<br>
	 * 
	 * @param contactUserId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void delete(
			@Define("body.contactUserId") String contactUserId) {
		ContactRelationService service = this.appContext.getObject(ContactRelationService.class);
		service.delete(contactUserId);
	}

	/**
	 * 
	 * 推送联系人拉入黑名单<br>
	 * Date 2020-04-10 22:50:25<br>
	 * 
	 * @param contactUserId
	 * @param isBlocked
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void updateBlocked(
			@Define("body.contactUserId") String contactUserId,
			@Define("body.isBlocked") String isBlocked) {
		ContactRelationService service = this.appContext.getObject(ContactRelationService.class);
		service.updateBlocked(contactUserId, isBlocked);
	}
}
