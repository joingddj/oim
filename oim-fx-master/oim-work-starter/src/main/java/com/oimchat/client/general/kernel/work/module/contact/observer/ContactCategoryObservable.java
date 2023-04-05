
package com.oimchat.client.general.kernel.work.module.contact.observer;

import com.oimchat.client.general.kernel.work.common.observer.BaseDataObservable;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:08:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryObservable extends BaseDataObservable<ContactCategory, ContactCategory, String> {

	public ContactCategoryObservable(AppContext appContext) {
		super(appContext);
	}
}
