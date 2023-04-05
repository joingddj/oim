
package com.oimchat.client.platform.kernel.work.module.im.handler;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.kernel.work.common.app.AppEnvInfo;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-24 17:00:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupContentSendHandler extends BaseContentSendHandler {

	public GroupContentSendHandler(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String localPath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User u = pb.getUser();

		StringBuilder sb = new StringBuilder();

		sb.append("/data/");
		sb.append(u.getNumber());
		sb.append("/images/chat/group/");

		return env.getPathByAppHome(sb.toString());
	}
}
