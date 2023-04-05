
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.box.GroupCategoryBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupCategoryManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupCategoryEventObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupCategoryObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupCategoryService extends AbstractMaterial {

	public GroupCategoryService(AppContext appContext) {
		super(appContext);
	}

	public void add(GroupCategory data) {
		GroupCategoryObservable ccl = this.appContext.getObject(GroupCategoryObservable.class);
		ccl.executeAdd(data);
	}

	public void add(String id) {
		GroupCategoryManager manager = this.appContext.getObject(GroupCategoryManager.class);
		manager.loadById(id, (info, data) -> {
			if (null != data) {
				add(data);
			}
		});
	}

	public void updateName(String id, String name) {
		GroupCategoryObservable ccl = this.appContext.getObject(GroupCategoryObservable.class);
		GroupCategoryBox box = this.appContext.getObject(GroupCategoryBox.class);
		GroupCategory data = box.get(id);
		if (null != data) {
			data.setName(name);
			ccl.executeUpdate(data);
		}
	}

	public void updateSort() {
		GroupCategoryEventObservable o = this.appContext.getObject(GroupCategoryEventObservable.class);
		o.execute((l) -> {
			l.updateSort();
		});
	}

	public void delete(String id) {
		GroupCategoryBox box = this.appContext.getObject(GroupCategoryBox.class);
		box.remove(id);

		GroupCategoryObservable ccl = this.appContext.getObject(GroupCategoryObservable.class);
		ccl.executeDelete(id);
	}
}
