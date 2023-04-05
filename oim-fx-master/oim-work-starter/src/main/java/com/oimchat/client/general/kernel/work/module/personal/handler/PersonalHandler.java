
package com.oimchat.client.general.kernel.work.module.personal.handler;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.entity.UserHead;
import com.oimchat.client.general.kernel.work.module.personal.sender.PersonalSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-15 16:07:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalHandler extends AbstractMaterial {

	public PersonalHandler(AppContext appContext) {
		super(appContext);
	}

	public void get(ValueBack<User> back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.get(new ValueBackActionImpl<User>(back, User.class));
	}

	public void update(User user, InfoBack back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.update(user, new InfoBackActionImpl(back));
	}

	public void updatePassword(
			String oldPassword,
			String newPassword,
			InfoBack back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.updatePassword(oldPassword, newPassword, new InfoBackActionImpl(back));
	}

	public void uploadHead(
			UserHead userHead, ValueBack<UserHead> back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.uploadHead(userHead, new ValueBackActionImpl<UserHead>(back, UserHead.class));
	}

	public void updateSignature(
			String signature, InfoBack back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.updateSignature(signature, new InfoBackActionImpl(back));
	}

	public void updateStatus(
			String status, InfoBack back) {
		PersonalSender sender = this.appContext.getObject(PersonalSender.class);
		sender.updateStatus(status, new InfoBackActionImpl(back));
	}
}
