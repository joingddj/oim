
package com.oimchat.client.general.kernel.work.module.personal.manager;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-15 16:07:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalManager extends AbstractMaterial {

	public PersonalManager(AppContext appContext) {
		super(appContext);
	}

	public void get(ValueBack<User> back) {
		PersonalHandler handler = this.appContext.getObject(PersonalHandler.class);
		handler.get(back);
	}

	public void getOrLoad(ValueBack<User> back) {
		PersonalBox box = this.appContext.getObject(PersonalBox.class);
		User user = box.getUser();
		if (null == user) {
			get(back);
		} else {
			back.back(new Info(), user);
		}
	}
}
