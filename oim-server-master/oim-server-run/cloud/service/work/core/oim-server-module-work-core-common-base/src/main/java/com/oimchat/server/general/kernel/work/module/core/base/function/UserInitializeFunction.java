package com.oimchat.server.general.kernel.work.module.core.base.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oimchat.server.general.kernel.work.define.user.function.UserInitialize;
import com.oimchat.server.general.kernel.work.module.core.base.manager.UserExtendManager;

/**
 * Date 2019-01-20 10:28:51<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Component
public class UserInitializeFunction {

	@Autowired
	UserInitialize userInitialize;

	@Autowired
	public void setUserExtendManager(UserExtendManager manager) {
		manager.addInitializeDataEvent(userId -> {
			userInitialize.initialize(userId);
		});
	}
}
