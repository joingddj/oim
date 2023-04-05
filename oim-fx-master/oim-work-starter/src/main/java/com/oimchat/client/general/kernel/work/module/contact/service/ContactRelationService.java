
package com.oimchat.client.general.kernel.work.module.contact.service;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.box.ContactRelationBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactRelationObservable;
import com.oimchat.client.general.kernel.work.module.contact.observer.listener.ContactRelationEventListener;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactRelationService extends AbstractMaterial {

	public ContactRelationService(AppContext appContext) {
		super(appContext);
	}

	public void add(String contactUserId) {
		ContactRelationObservable o = this.appContext.getObject(ContactRelationObservable.class);
		ContactRelationManager manager = this.appContext.getObject(ContactRelationManager.class);
		manager.loadByContactUserId(contactUserId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeAdd(data);
			}
		});
	}

	public void updateRemark(String contactUserId, String remark) {
		ContactRelationObservable o = this.appContext.getObject(ContactRelationObservable.class);
		ContactRelationManager manager = this.appContext.getObject(ContactRelationManager.class);
		manager.loadByContactUserId(contactUserId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}

	public void moveCategory(List<String> contactUserIds, String categoryId) {
		ContactRelationManager manager = this.appContext.getObject(ContactRelationManager.class);
		manager.asynLoadAllList((info, list) -> {

		});
		ContactRelationEventListener o = this.appContext.getObject(ContactRelationEventListener.class);
		o.moveCategory(contactUserIds, categoryId);
	}

	public void delete(String contactUserId) {
		ContactRelationObservable o = this.appContext.getObject(ContactRelationObservable.class);
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		List<ContactRelation> list = box.getListByItemKey(contactUserId);
		box.removeItem(contactUserId);
		if (list.isEmpty()) {
			for (ContactRelation data : list) {
				o.executeDelete(data);
			}
		} else {
			ContactRelation data = new ContactRelation();
			data.setContactUserId(contactUserId);
			o.executeDelete(data);
		}
	}

	public void updateBlocked(String contactUserId, String isBlocked) {
		ContactRelationEventListener o = this.appContext.getObject(ContactRelationEventListener.class);
		o.updateBlocked(contactUserId, isBlocked);
	}
}
