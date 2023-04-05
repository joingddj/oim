package com.oimchat.client.platform.fx.view.impl.classics;

import com.oimchat.app.fx.view.ui.classics.module.account.UpdatePasswordStage;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.oimchat.client.platform.common.view.MainView;
import com.oimchat.client.platform.common.view.UpdatePasswordView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年2月10日 下午4:34:09
 * @version 0.0.1
 */
public class UpdatePasswordViewImpl extends BaseStageView<UpdatePasswordStage> implements UpdatePasswordView {

	public UpdatePasswordViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	private void updatePassword() {
		String oldPassword = stage.getOldPassword();
		String newPassword = stage.getNewPassword();
		String verifyPassword = stage.getConfirmPassword();

		if (null == oldPassword || "".equals(oldPassword.trim())) {
			showPrompt("请输入原来的密码！");
			return;
		}
		if (null == newPassword || "".equals(newPassword.trim())) {
			showPrompt("请输新密码！");
			return;
		}
		if (null == verifyPassword || "".equals(verifyPassword.trim())) {
			showPrompt("请再一次输入新密码！");
			return;
		}
		if (!newPassword.equals(verifyPassword.trim())) {
			showPrompt("两次输入密码不一致！");
			return;
		}

		PersonalHandler ph = this.appContext.getObject(PersonalHandler.class);
		ph.updatePassword(Md5Util.lower32(oldPassword), Md5Util.lower32(newPassword), (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
				MainView mv = appContext.getObject(MainView.class);
				mv.showPrompt("修改成功。");
			} else {
				String text = OnlyMessageUtil.getDefaultErrorText(info);
				if (null == text) {
					text = "修改失败。";
				}
				showPrompt(text);
			}
		});
	}

	@Override
	protected UpdatePasswordStage createStage() {
		return new UpdatePasswordStage();
	}

	@Override
	protected void initStage(UpdatePasswordStage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		getStage().setOnDoneAction(a -> {
			updatePassword();
		});
	}
}
