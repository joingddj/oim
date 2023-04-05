package com.oimchat.client.platform.kernel.work.common.env;

import com.onlyxiahui.app.context.AppContext;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.kernel.work.common.app.AppEnvInfo;
import com.onlyxiahui.app.context.AbstractMaterial;

/**
 * @author XiaHui
 * @date 2017年6月18日 下午9:32:36
 */
public class PathEnv extends AbstractMaterial {

	public PathEnv(AppContext appContext) {
		super(appContext);
	}

	public String getScreenshotSavePath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("screenshot");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getPersonalHeadPath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("head/user");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getUserHeadPath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("head/user");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getGroupHeadPath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("head/group");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getUserChatImagePath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("chat/user");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getGroupChatImagePath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("images");
		sb.append("/");
		sb.append("chat/group");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}

	public String getLogFilePath() {
		AppEnvInfo env = appContext.getObject(AppEnvInfo.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		StringBuilder sb = new StringBuilder();
		sb.append("/data/");
		sb.append(user.getNumber());
		sb.append("/");
		sb.append("logs");
		sb.append("/");
		String path = env.getPathByAppHome(sb.toString());
		return path;
	}
}
