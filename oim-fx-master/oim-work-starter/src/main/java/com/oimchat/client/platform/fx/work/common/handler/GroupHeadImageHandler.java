package com.oimchat.client.platform.fx.work.common.handler;

import java.net.URL;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.resources.head.HeadRepository;
import com.oimchat.client.platform.fx.work.common.box.GroupHeadBox;
import com.oimchat.client.platform.kernel.work.common.app.AppEnvInfo;
import com.onlyxiahui.app.context.AppContext;

/**
 * @author: XiaHui
 * @date: 2018-01-27 14:35:36
 */
public class GroupHeadImageHandler extends BaseHeadImageHandler<GroupHeadBox> {

	public GroupHeadImageHandler(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String rootPath() {

		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User u = pb.getUser();

		StringBuilder sb = new StringBuilder();

		sb.append("/data/");
		sb.append(u.getNumber());
		sb.append("/images/head/group/");

		return env.getPathByAppHome(sb.toString());
	}

	@Override
	public URL getHeadUrl(String head) {
		HeadRepository hr = appContext.getObject(HeadRepository.class);
		URL url = hr.getGroupHeadPath(head);
		if (null == url) {
			url = hr.getGroupHeadPath("1");
		}
		return url;
	}

	@Override
	public GroupHeadBox getBox() {
		GroupHeadBox box = this.appContext.getObject(GroupHeadBox.class);
		return box;
	}
}
