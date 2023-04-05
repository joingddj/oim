
package com.oimchat.client.general.kernel.work.module.contact.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.box.ContactRelationBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.core.box.UserBox;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.util.UserStatusUtil;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 15:59:34<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactRelationUserManager extends AbstractMaterial {

	public ContactRelationUserManager(AppContext appContext) {
		super(appContext);
	}

	public int getOnlineCountByCategoryId(String categoryId) {
		UserBox ub = this.appContext.getObject(UserBox.class);
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		List<ContactRelation> list = box.getListByCategoryKey(categoryId);
		List<String> ids = new ArrayList<>();
		for (ContactRelation r : list) {
			ids.add(r.getContactUserId());
		}
		List<User> users = ub.getList(ids);
		return UserStatusUtil.getOnlineCount(users);
	}
}
