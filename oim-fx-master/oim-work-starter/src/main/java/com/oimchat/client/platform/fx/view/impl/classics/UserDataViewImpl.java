package com.oimchat.client.platform.fx.view.impl.classics;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.oimchat.client.general.common.util.HereStringUtil;
import com.oimchat.client.general.common.util.OimDateUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.platform.common.view.UserDataView;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class UserDataViewImpl extends BaseStageView<ClassicsStage> implements UserDataView {

	BaseInfoPane frame = new BaseInfoPane();;

	public UserDataViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	public void showUserId(String userId) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, user) -> {
			showUser(user);
		});
	}

	public void showUser(User user) {
		String info = getInfo(user);

		FxUtil.invoke(() -> {
			if (null != user) {
				frame.setInfoText(info);
			} else {
				frame.setHeadImage(null);
				frame.setName("");
				frame.setNumber("");
				frame.setInfoText("");
			}
		});
		if (null != user) {
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);
			uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					frame.setHeadImage(img);
				});
			});
		}
	}

	private String getInfo(User userData) {
		// String nickname = userData.getNickname();
		// String account = userData.getAccount();
		String gender = userData.getGender();
		StringBuilder sb = new StringBuilder();

		sb.append("昵称：");
		sb.append(HereStringUtil.value(userData.getNickname()));
		sb.append("\n");
		sb.append("\n");

		sb.append("账号：");
		sb.append(HereStringUtil.value(userData.getAccount()) + "");
		sb.append("\n");
		sb.append("\n");

		String genderText = "保密";
		if ("1".equals(gender)) {
			genderText = "男";
		}
		if ("2".equals(gender)) {
			genderText = "女";
		}
		sb.append("性别：");
		sb.append(genderText);
		sb.append("\n");
		sb.append("\n");

		sb.append("年龄：");
		if (null != userData.getBirthDate()) {
			int y = OimDateUtil.beforePresentYearCount(userData.getBirthDate());
			sb.append(y);
			sb.append("岁");
		}
		sb.append("\n");
		sb.append("\n");

		sb.append("故乡：");
		sb.append(HereStringUtil.value(userData.getHomeAddress()));
		sb.append("\n");
		sb.append("\n");

		sb.append("所在地：");
		sb.append(HereStringUtil.value(userData.getLocationAddress()));
		sb.append("\n");
		sb.append("\n");

		sb.append("个性签名：");
		sb.append(HereStringUtil.value(userData.getSignature()));
		sb.append("\n");

		return (sb.toString());
	}

	@Override
	protected ClassicsStage createStage() {
		return new ClassicsStage();
	}

	@Override
	protected void initStage(ClassicsStage stage) {
		stage.setWidth(380);
		stage.setHeight(520);
		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setCenter(frame);
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub

	}
}
