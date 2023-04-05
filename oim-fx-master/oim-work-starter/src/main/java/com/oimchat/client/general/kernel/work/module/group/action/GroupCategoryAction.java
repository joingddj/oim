package com.oimchat.client.general.kernel.work.module.group.action;

import com.oimchat.client.general.kernel.work.module.group.service.GroupCategoryService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 群分组业务推送<br>
 * Date 2019-01-27 21:43:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@ActionMapping("1.3.002")
public class GroupCategoryAction extends AbstractMaterial {

	public GroupCategoryAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 推送新增分组<br>
	 * Date 2020-04-11 15:24:45<br>
	 * 
	 * @param id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void add(
			@Define("body.id") String id) {
		GroupCategoryService service = this.appContext.getObject(GroupCategoryService.class);
		service.add(id);
	}

	/**
	 * 推送修改名称<br>
	 * Date 2020-04-11 15:25:14<br>
	 * 
	 * @param id
	 * @param name
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updateName(
			@Define("body.id") String id,
			@Define("body.name") String name) {
		GroupCategoryService service = this.appContext.getObject(GroupCategoryService.class);
		service.updateName(id, name);
	}

	/**
	 * 推送更新排序<br>
	 * Date 2020-04-11 15:25:31<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void updateSort() {
		GroupCategoryService service = this.appContext.getObject(GroupCategoryService.class);
		service.updateSort();
	}

	/**
	 * 推送删除分组<br>
	 * Date 2020-04-11 15:27:10<br>
	 * 
	 * @param id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void delete(@Define("body.id") String id) {
		GroupCategoryService service = this.appContext.getObject(GroupCategoryService.class);
		service.delete(id);
	}
}
