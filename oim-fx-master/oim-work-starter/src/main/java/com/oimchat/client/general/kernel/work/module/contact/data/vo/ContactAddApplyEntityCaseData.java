
package com.oimchat.client.general.kernel.work.module.contact.data.vo;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * Description <br>
 * Date 2021-04-02 14:49:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactAddApplyEntityCaseData extends ContactAddApplyEntityCase {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
