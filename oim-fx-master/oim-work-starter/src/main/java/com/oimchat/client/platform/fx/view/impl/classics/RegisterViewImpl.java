package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.module.account.RegisterStage;
import com.oimchat.app.fx.view.ui.classics.module.account.ShowAccountDialog;
import com.oimchat.client.general.common.util.BusinessStringUtil;
import com.oimchat.client.general.kernel.work.module.account.call.RegisterCall;
import com.oimchat.client.general.kernel.work.module.account.data.dto.SecurityQuestion;
import com.oimchat.client.general.kernel.work.module.account.data.dto.UserRegister;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.server.handler.ServerHandler;
import com.oimchat.client.platform.common.view.RegisterView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

import javafx.application.Platform;

public class RegisterViewImpl extends BaseStageView<RegisterStage> implements RegisterView {

	// 注册成功后提示信息
	ShowAccountDialog showAccountDialog;

	public RegisterViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	private void register() {
		boolean v = stage.verify();
		if (!v) {
			return;
		}

		String account = stage.getAccount();
		String nickname = stage.getNickname();
		String password = stage.getPassword();
		String gender = stage.getGender();
		String blood = stage.getBloodType();
		Date birthdate = stage.getBirthday();
		String homeAddress = stage.getHomeAddress();
		String locationAddress = stage.getLocationAddress();
		String mobile = stage.getMobile();
		String email = stage.getEmail();
		String signature = stage.getSignature();
		String introduce = stage.getIntroduce();

		if (!BusinessStringUtil.isAccount(account)) {
			stage.showAccountPopup(BusinessStringUtil.PROMPT_ACCOUNT);
			return;
		}

		UserRegister user = new UserRegister();

		user.setAccount(account);
		user.setPassword(Md5Util.lower32(password));
		user.setNickname(nickname);
		user.setGender(gender);
		user.setBlood(blood);
		user.setBirthDate(birthdate);
		user.setHomeAddress(homeAddress);
		user.setLocationAddress(locationAddress);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setSignature(signature);
		user.setIntroduce(introduce);

		String q = stage.getQuestion();
		String a = stage.getAnswer();

		List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();

		if ((null != q && !"".equals(q)) && (null != a && !"".equals(a))) {

			SecurityQuestion sq = new SecurityQuestion();
			sq.setQuestion(q);
			sq.setAnswer(a);
			list.add(sq);
		} else {

			if (null != q && !"".equals(q)) {
				this.showPrompt("填写了密保问题请填写答案！");
				return;
			}

			if (null != a && !"".equals(a)) {
				this.showPrompt("填写了密保答案请填写问题！");
				return;
			}
		}
		stage.showWaiting(true);
		RegisterCall call = this.appContext.getObject(RegisterCall.class);
		this.execute(() -> {
			ServerHandler sh = appContext.getObject(ServerHandler.class);
			sh.load((i) -> {
				if (i.isSuccess()) {
					call.register(user, list, (info, u) -> {
						stage.showWaiting(false);
						handle(info, u);
					});
				} else {
					stage.showWaiting(false);
					String text = OnlyMessageUtil.getDefaultErrorText(i);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							getStage().showWaitPrompt(text);
						}
					});
				}
			});
		});
	}

	private void handle(Info info, User user) {
		if (info.isSuccess()) {
			String text = "注册成功，你的账号为：" + user.getAccount();

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					showAccountDialog.setText(text);
					showAccountDialog.show();
					stage.clearData();
				}
			});
		} else {
			String text = OnlyMessageUtil.getDefaultErrorText(info);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					getStage().showWaitPrompt(text);
				}
			});
		}
	}

	@Override
	protected RegisterStage createStage() {
		return new RegisterStage();
	}

	@Override
	protected void initStage(RegisterStage stage) {
		// 注册成功后提示信息
		showAccountDialog = new ShowAccountDialog(stage);
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		stage.setOnDoneAction(a -> {
			register();
		});
	}
}
