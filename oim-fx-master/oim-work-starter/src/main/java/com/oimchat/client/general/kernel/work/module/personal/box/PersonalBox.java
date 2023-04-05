package com.oimchat.client.general.kernel.work.module.personal.box;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * @author: XiaHui
 * @date: 2017年6月19日 下午1:48:30
 */
public class PersonalBox extends AbstractMaterial {

	private User user;

	public PersonalBox(AppContext appContext) {
		super(appContext);
	}

	public String getOwnerUserId() {
		User userData = getUser();
		return null == userData ? "" : userData.getId();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return getOwnerUserId();
	}
}
