
package com.oimchat.client.general.kernel.work.module.index.service;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.asyn.ValueBackInvoker;
import com.oimchat.client.common.net.back.ValueBackActionAdapter;
import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.kernel.work.module.base.box.AppInfoBox;
import com.oimchat.client.general.kernel.work.module.common.store.AuthStore;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.index.cell.LoginCall;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginData;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginResult;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginUser;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.kernel.work.module.system.function.SystemNetFunction;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-13 22:17:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginService extends AbstractMaterial {

	public LoginService(AppContext appContext) {
		super(appContext);
	}

	public void login(LoginUser loginUser, ValueBack<LoginResult> back) {
		AppInfoBox box = this.appContext.getObject(AppInfoBox.class);
		LoginCall call = this.appContext.getObject(LoginCall.class);
		Client client = box.getClient();
		LoginData loginData = new LoginData(loginUser, client);
		call.login(loginData, new ValueBackActionAdapter<LoginResult>(back) {
			@Back
			public void back(@Define("info") Info info, @Define("body") LoginResult data) {
				if (info.isSuccess()) {
					loginBack(data.getUser());
				}
				ValueBackInvoker.back(back, info, data);
			}
		});
	}

	public void connect(InfoBack back) {
		SystemNetFunction sns = appContext.getObject(SystemNetFunction.class);
		sns.connect(back);
	}

	public void auth(String token, InfoBack back) {
		SystemNetFunction sns = appContext.getObject(SystemNetFunction.class);
		InfoBack connectBack = (info) -> {
			if (info.isSuccess()) {
				sns.auth(token, back);
			} else {
				back.back(info);
			}
		};
		connect(connectBack);
	}

	public void authSucceed(String token, User user) {
		AuthStore as = appContext.getObject(AuthStore.class);
		as.setAuth(true);
		as.setToken(token);
		as.setLogin(true);
		as.setUserId(user.getId());
	}

	public void loginBack(User user) {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		pb.setUser(user);
	}
}
