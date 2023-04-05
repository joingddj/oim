package com.oimchat.client.general.kernel.work.module.contact.action;

import com.oimchat.client.general.kernel.work.module.contact.service.ContactCategoryService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 联系人分组业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.2.0
 */
@ActionMapping("1.2.002")
public class ContactCategoryAction extends AbstractMaterial {

	public ContactCategoryAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送新增分组<br>
	 * Date 2020-04-10 22:31:30<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void add(
			@Define("body.id") String id) {
		ContactCategoryService service = this.appContext.getObject(ContactCategoryService.class);
		service.add(id);
	}

	/**
	 * 
	 * 推送更新<br>
	 * Date 2020-04-12 20:35:30<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void update(
			@Define("body.id") String id) {
		ContactCategoryService service = this.appContext.getObject(ContactCategoryService.class);
		service.update(id);
	}

	/**
	 * 
	 * 推送修改名称<br>
	 * Date 2020-04-10 22:31:47<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updateName(
			@Define("body.id") String id,
			@Define("body.name") String name) {
		ContactCategoryService service = this.appContext.getObject(ContactCategoryService.class);
		service.updateName(id, name);
	}

	/**
	 * 
	 * 推送修改排序<br>
	 * Date 2020-04-10 22:32:04<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void updateSort() {
		ContactCategoryService service = this.appContext.getObject(ContactCategoryService.class);
		service.updateSort();
	}

	/**
	 * 推送删除分组<br>
	 * Date 2020-04-10 22:32:18<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void delete(
			@Define("body.id") String id) {
		ContactCategoryService service = this.appContext.getObject(ContactCategoryService.class);
		service.delete(id);
	}
}
