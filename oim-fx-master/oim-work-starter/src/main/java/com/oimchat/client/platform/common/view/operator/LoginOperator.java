
package com.oimchat.client.platform.common.view.operator;

import com.oimchat.client.basic.util.FileLockUtil;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.common.initializer.InitializerHandler;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.index.controller.LoginController;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginResult;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginUser;
import com.oimchat.client.general.kernel.work.module.index.store.LoginUserStore;
import com.oimchat.client.platform.common.config.login.LoginConfigBox;
import com.oimchat.client.platform.common.config.login.data.LoginSaveData;
import com.oimchat.client.platform.common.config.login.data.LoginSettingData;
import com.oimchat.client.platform.common.config.login.data.LoginUserData;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.oimchat.client.platform.kernel.work.common.app.AppEnvInfo;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.io.FileUtil;

/**
 * Description <br>
 * Date 2021-03-13 10:30:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginOperator extends AbstractMaterial {

	public LoginOperator(AppContext appContext) {
		super(appContext);
	}

	public void login(LoginUser user, LoginSettingData setting, InfoBack back) {
		login(user, (info, lr) -> {
			loginBackHanle(info, lr, (bi) -> {
				if (bi.isSuccess()) {
					auth(lr, back);
					saveSettingHandle(lr.getUser(), user, setting);
				} else {
					back.back(bi);
				}
			});
		});
	}

	public void login(LoginUser user, InfoBack back) {
		login(user, (info, lr) -> {
			loginBackHanle(info, lr, (bi) -> {
				if (bi.isSuccess()) {
					auth(lr, back);
				} else {
					back.back(bi);
				}
			});
		});
	}

	private void login(LoginUser user, ValueBack<LoginResult> back) {
		LoginUserStore lus = appContext.getObject(LoginUserStore.class);
		lus.setLoginUser(user);

		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			LoginController lc = this.appContext.getObject(LoginController.class);
			InitializerHandler ih = this.appContext.getObject(InitializerHandler.class);
			while (!ih.isInitialized()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			multipleLoginCheck(user.getAccount(), (mi) -> {
				if (mi.isSuccess()) {
					lc.login(user, back);
				} else {
					releaseLock(user.getAccount());
					back.back(mi, null);
				}
			});
		});
	}

	private void loginBackHanle(Info info, LoginResult lr, InfoBack back) {
		if (info.isSuccess()) {
			multipleLoginCheck(lr.getUser().getNumber() + "", (mi) -> {
				if (!mi.isSuccess()) {
					releaseLock(lr.getUser().getNumber() + "");
				}
				back.back(mi);
			});
		} else {
			back.back(info);
		}
	}

	private void auth(LoginResult lr, InfoBack back) {
		LoginController lc = this.appContext.getObject(LoginController.class);
		lc.auth(lr.getToken(), lr.getUser(), back);
	}

	private void saveSettingHandle(User user, LoginUser lu, LoginSettingData setting) {
		LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
		// 获取登录保存的用户账号数量
		int size = lcb.getSize();
		// 默认只保存20个用户的数量，多于20个，则删除多余的。
		if (size > 19) {
			lcb.remove((size - 19));
		}

		String account = user.getAccount();
		String head = user.getHead();
		String avatar = user.getAvatar();

		// TODO 状态问题
		String status = lu.getStatus();
		LoginSaveData data = new LoginSaveData();

		LoginUserData saveUser = new LoginUserData();
		saveUser.setAccount(account);
		saveUser.setHead(head);
		// saveUser.setAvatar(avatar);
		saveUser.setStatus(status);

		if (setting.isSavePassword() || setting.isAutoLogin()) {
			saveUser.setPassword(lu.getPassword());
		} else {
			saveUser.setPassword("");
		}

		data.setSetting(setting);
		data.setUser(saveUser);

		UserHeadImageHandler im = this.appContext.getObject(UserHeadImageHandler.class);
		im.loadAvatarUrl(avatar, (url) -> {
			if (null != url) {
				saveUser.setAvatar(url.toExternalForm());
			}
			lcb.put(account, data);
		});
	}

	private void multipleLoginCheck(String account, InfoBack back) {
		String accountLock = getLockPath(account);
		FileUtil.checkOrCreateFile(accountLock);
		Info info = new Info();
		if (FileLockUtil.isLock(accountLock)) {
			info.addWarning("4.001", account + "已经登录，不能重复登录!");
		}
		back.back(info);
	}

	public void releaseLock(String account) {
		String accountLock = getLockPath(account);
		FileLockUtil.releaseLock(accountLock);
	}

	public String getLockPath(String account) {
		AppEnvInfo appInfo = appContext.getObject(AppEnvInfo.class);
		StringBuilder lock = new StringBuilder();
		lock.append(appInfo.getAppHomePath());
		lock.append("/lock/");
		lock.append(account);
		lock.append(".lock");
		String accountLock = lock.toString();
		return accountLock;
	}

}
