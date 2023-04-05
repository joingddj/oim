package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oimchat.app.fx.view.ui.classics.module.login.LoginPane;
import com.oimchat.app.fx.view.ui.classics.module.login.LoginStage;
import com.oimchat.client.general.kernel.work.module.core.type.UserStatusTypeEnum;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginUser;
import com.oimchat.client.general.unit.SystemUnit;
import com.oimchat.client.platform.common.config.login.LoginConfigBox;
import com.oimchat.client.platform.common.config.login.data.LoginSaveData;
import com.oimchat.client.platform.common.config.login.data.LoginSettingData;
import com.oimchat.client.platform.common.config.login.data.LoginUserData;
import com.oimchat.client.platform.common.view.ForgetPasswordView;
import com.oimchat.client.platform.common.view.LoginView;
import com.oimchat.client.platform.common.view.MainView;
import com.oimchat.client.platform.common.view.NetSettingView;
import com.oimchat.client.platform.common.view.RegisterView;
import com.oimchat.client.platform.common.view.operator.LoginOperator;
import com.oimchat.client.platform.fx.view.common.box.UserStatusImageBox;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;

/**
 * @author: XiaHui
 * @date: 2016年10月10日 上午11:04:46
 */
public class LoginViewImpl extends BaseStageView<LoginStage> implements LoginView {

	protected String tempPassword = "!@#$%^&*()";
	protected ContextMenu menu = new ContextMenu();
	protected String status = UserStatusTypeEnum.online.getCode();

	public LoginViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
		FxUtil.invoke(() -> {
			initEvent();
			initContextMenu();
		});
		this.execute(() -> {
			initData();
		});
	}

	private void initData() {
		LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
		List<LoginSaveData> list = lcb.allList();
		Set<String> accounts = new HashSet<>();
		for (LoginSaveData sd : list) {
			accounts.add(sd.getAccount());
		}

		FxUtil.invoke(() -> {
			getLoginPane().setAccounts(accounts);
			LoginSaveData data = null;
			if (!list.isEmpty()) {
				data = list.get(0);
			}
			if (null != data) {
				setLoginUser(data);
			}
		});

		for (LoginSaveData ud : list) {
			if (null != ud.getSetting() && ud.getSetting().isAutoLogin()) {
				login(ud.getUser());
				break;
			}
		}
	}

	protected void login() {
		LoginPane lp = getLoginPane();
		if (lp.verify()) {
			execute(() -> {
				doLogin();
			});
		}
	}

	protected void doLogin() {
		LoginPane lp = getLoginPane();
		boolean autoLogin = lp.isAutoLogin();
		boolean savePassword = lp.isRememberPassword();

		String account = lp.getAccount();
		String password = lp.getPassword();
		String sp = Md5Util.lower32(password);

		if (tempPassword.equals(password)) {
			LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
			LoginSaveData ls = lcb.get(account);
			if (null != ls && null != ls.getUser()) {
				String localPassword = ls.getUser().getPassword();
				if (StringUtil.isNotBlank(localPassword)) {
					sp = localPassword;
				}
			}
		}
		LoginUser lu = new LoginUser();
		lu.setAccount(account);
		lu.setPassword(sp);
		lu.setStatus(status);
		LoginSettingData setting = new LoginSettingData();
		setting.setAutoLogin(autoLogin);
		setting.setSavePassword(savePassword);

		LoginOperator lo = appContext.getObject(LoginOperator.class);
		lp.showWaiting(true);
		lo.login(lu, setting, (info) -> {

			if (info.isSuccess()) {
				setVisible(false);
				// 去主界面
				MainView mv = appContext.getObject(MainView.class);
				mv.setVisible(true);
			} else {
				String errorText = OnlyMessageUtil.getDefaultErrorText(info);
				showPrompt(errorText);
			}
			lp.showWaiting(false);
		});
	}

	protected void login(LoginUserData ld) {
		if (null != ld) {
			LoginPane lp = getLoginPane();

			String account = ld.getAccount();
			String password = ld.getPassword();

			LoginUser lu = new LoginUser();
			lu.setAccount(account);
			lu.setPassword(password);

			LoginOperator lo = appContext.getObject(LoginOperator.class);
			lp.showWaiting(true);
			lo.login(lu, (info) -> {

				if (info.isSuccess()) {
					setVisible(false);
					// 去主界面
					MainView mv = appContext.getObject(MainView.class);
					mv.setVisible(true);
				} else {
					String errorText = OnlyMessageUtil.getDefaultErrorText(info);
					showPrompt(errorText);
				}
				lp.showWaiting(false);
			});
		}
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		if (stage.isShowing()) {
			FxUtil.invoke(() -> {
				stage.requestFocus();
			});
		}
	}

	@Override
	public boolean isSavePassword() {
		return getStage().getLoginPane().isRememberPassword();
	}

	@Override
	public void showWaiting(boolean show) {
	}

	protected LoginPane getLoginPane() {
		return getStage().getLoginPane();
	}

	private void removeLoginUser(String account) {
		LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
		lcb.remove(account);

		LoginPane lp = getLoginPane();
		lp.removeAccount(account);
	}

	private void setLoginUser(String account) {
		LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
		LoginSaveData saveData = lcb.get(account);
		setLoginUser(saveData);
	}

	private void setLoginUser(LoginSaveData saveData) {
		LoginPane lp = getLoginPane();
		if (null != saveData && null != saveData.getUser()) {
			LoginSettingData setting = saveData.getSetting();
			LoginUserData user = saveData.getUser();

			String account = user.getAccount();
			String password = user.getPassword();
			String status = user.getStatus();
			String head = user.getHead();
			String avatar = user.getAvatar();

			boolean autoLogin = false;
			boolean rememberPassword = false;
			if (null != setting) {
				autoLogin = setting.isAutoLogin();
				rememberPassword = setting.isSavePassword();
			}

			if (rememberPassword) {
				password = tempPassword;
			} else {
				password = "";
			}

			UserHeadImageHandler him = appContext.getObject(UserHeadImageHandler.class);
			if (StringUtil.isNotBlank(avatar)) {
				Image image = ImageLoadUtil.getImageByUrl(avatar);
				lp.setHeadImage(image);
			} else {
				him.loadHeadImage(head, (img) -> {
					FxUtil.invoke(() -> {
						lp.setHeadImage(img);
					});
				});
			}

			lp.setAccount(account);
			lp.setPassword(password);
			lp.setAutoLogin(autoLogin);
			lp.setRememberPassword(rememberPassword);
			FxUtil.invoke(() -> {
				lp.setStatusImage(UserStatusImageBox.getStatusImageIcon(status));
				setStatus(status);
			});
		} else {

			lp.setPassword("");
			lp.setAutoLogin(false);
			lp.setRememberPassword(false);

			lp.setStatusImage(UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.online.getCode()));
			setStatus(UserStatusTypeEnum.online.getCode());
		}
	}

	protected void initContextMenu() {

		LoginPane lp = getLoginPane();

		lp.setStatusImage(UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.online.getCode()));
		this.status = UserStatusTypeEnum.online.getCode();

		lp.addStatusImage(
				UserStatusTypeEnum.online.getCode(),
				UserStatusTypeEnum.online.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.online.getCode()), (v) -> {
					setStatus(v);
				});
		lp.addStatusImage(
				UserStatusTypeEnum.call_me.getCode(),
				UserStatusTypeEnum.call_me.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.call_me.getCode()), (v) -> {
					setStatus(v);
				});
		lp.addStatusImage(
				UserStatusTypeEnum.away.getCode(),
				UserStatusTypeEnum.away.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.away.getCode()), (v) -> {
					setStatus(v);
				});
		lp.addStatusImage(
				UserStatusTypeEnum.busy.getCode(),
				UserStatusTypeEnum.busy.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.busy.getCode()), (v) -> {
					setStatus(v);
				});
		lp.addStatusImage(
				UserStatusTypeEnum.mute.getCode(),
				UserStatusTypeEnum.mute.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.mute.getCode()), (v) -> {
					setStatus(v);
				});
		lp.addStatusImage(
				UserStatusTypeEnum.invisible.getCode(),
				UserStatusTypeEnum.invisible.getText(),
				UserStatusImageBox.getStatusImageIcon(UserStatusTypeEnum.invisible.getCode()), (v) -> {
					setStatus(v);
				});

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected LoginStage createStage() {
		return new LoginStage();
	}

	@Override
	protected void initStage(LoginStage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {

		getStage().getOnlyDecoratedPane().addOnCloseAction(a -> {
			SystemUnit sm = appContext.getObject(SystemUnit.class);
			sm.exit();
		});
		getStage().setOnSettingAction((a) -> {
			NetSettingView netSettingView = appContext.getObject(NetSettingView.class);
			netSettingView.setVisible(true);
		});

		getLoginPane().setOnLoginAction(a -> {
			login();
		});

		getLoginPane().setRegisterOnMouseClicked((a) -> {
			RegisterView registerView = appContext.getObject(RegisterView.class);
			registerView.setVisible(true);
		});
		getLoginPane().setForgetOnMouseClicked((a) -> {
			ForgetPasswordView view = appContext.getObject(ForgetPasswordView.class);
			view.setVisible(true);
		});

		getLoginPane().addAccountListener((o, ov, nv) -> {
			setLoginUser(nv);
		});
		getLoginPane().setOnAccountRemove((v) -> {
			removeLoginUser(v);
		});
	}
}
