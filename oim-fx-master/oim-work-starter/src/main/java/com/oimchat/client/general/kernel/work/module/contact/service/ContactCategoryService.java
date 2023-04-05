
package com.oimchat.client.general.kernel.work.module.contact.service;

import com.oimchat.client.general.kernel.work.module.contact.box.ContactCategoryBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactCategoryManager;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactCategoryEventObservable;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactCategoryObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryService extends AbstractMaterial {

	public ContactCategoryService(AppContext appContext) {
		super(appContext);
	}

	public void add(ContactCategory data) {
		ContactCategoryObservable ccl = this.appContext.getObject(ContactCategoryObservable.class);
		ccl.executeAdd(data);
	}

	public void add(String id) {
		ContactCategoryManager manager = this.appContext.getObject(ContactCategoryManager.class);
		manager.loadById(id, (info, data) -> {
			if (null != data) {
				add(data);
			}
		});
	}

	public void update(String id) {
		ContactCategoryObservable ccl = this.appContext.getObject(ContactCategoryObservable.class);
		ContactCategoryManager manager = this.appContext.getObject(ContactCategoryManager.class);
		manager.loadById(id, (info, data) -> {
			if (null != data) {
				ccl.executeUpdate(data);
			}
		});
	}

	public void updateName(String id, String name) {
		ContactCategoryObservable ccl = this.appContext.getObject(ContactCategoryObservable.class);
		ContactCategoryBox box = this.appContext.getObject(ContactCategoryBox.class);
		ContactCategory data = box.get(id);
		if (null != data) {
			data.setName(name);
			ccl.executeUpdate(data);
		}
	}

	public void updateSort() {
		ContactCategoryEventObservable o = this.appContext.getObject(ContactCategoryEventObservable.class);
		o.execute((l) -> {
			l.updateSort();
		});
	}

	public void delete(String id) {
		ContactCategoryBox box = this.appContext.getObject(ContactCategoryBox.class);
		box.remove(id);
		ContactCategoryObservable ccl = this.appContext.getObject(ContactCategoryObservable.class);
		ccl.executeDelete(id);
	}
}
