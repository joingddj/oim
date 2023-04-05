
package com.oimchat.client.general.kernel.work.module.core.service;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.core.box.UserBox;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.event.UserDeleteObservable;
import com.oimchat.client.general.kernel.work.module.core.event.UserObservable;
import com.oimchat.client.general.kernel.work.module.core.event.UserSignatureObservable;
import com.oimchat.client.general.kernel.work.module.core.event.UserStatusObservable;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 21:38:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserService extends AbstractMaterial {

	public UserService(AppContext appContext) {
		super(appContext);
	}

	public void getById(String id, ValueBack<User> back) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(id, back);
	}

	public void loadById(String id, ValueBack<User> back) {
		UserManager manager = this.appContext.getObject(UserManager.class);
		manager.loadById(id, back);
	}

	public void update(String userId) {
		UserManager manager = this.appContext.getObject(UserManager.class);
		UserObservable ul = this.appContext.getObject(UserObservable.class);
		manager.loadById(userId, (info, user) -> {
			if (info.isSuccess()) {
				ul.execute(user);
			}
		});
	}

	public void updateHead(String userId) {
		UserManager manager = this.appContext.getObject(UserManager.class);
		UserObservable ul = this.appContext.getObject(UserObservable.class);
		manager.loadById(userId, (info, user) -> {
			if (info.isSuccess()) {
				ul.execute(user);
			}
		});
	}

	public void updateSignature(String userId, String signature) {
		UserBox box = this.appContext.getObject(UserBox.class);
		User user = box.get(userId);
		if (null != user) {
			user.setSignature(signature);
		}
		UserSignatureObservable ul = this.appContext.getObject(UserSignatureObservable.class);
		ul.execute(userId, signature);
	}

	public void updateStatus(String userId, String status) {
		UserBox box = this.appContext.getObject(UserBox.class);
		User user = box.get(userId);
		if (null != user) {
			user.setStatus(status);
		}
		UserStatusObservable ul = this.appContext.getObject(UserStatusObservable.class);
		ul.execute(userId, status);
	}

	public void delete(String userId) {
		UserDeleteObservable ul = this.appContext.getObject(UserDeleteObservable.class);
		ul.execute(userId);
	}
}
