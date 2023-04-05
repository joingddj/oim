
package com.oimchat.client.general.kernel.work.module.index.controller;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.common.store.AuthStore;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginResult;
import com.oimchat.client.general.kernel.work.module.index.data.dto.LoginUser;
import com.oimchat.client.general.kernel.work.module.index.service.EnterService;
import com.oimchat.client.general.kernel.work.module.index.service.LoginService;
import com.oimchat.client.general.kernel.work.module.index.store.LoginUserStore;
import com.oimchat.client.general.kernel.work.module.server.handler.ServerHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-12 09:35:09<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginController extends AbstractMaterial {

	public LoginController(AppContext appContext) {
		super(appContext);
	}

	public void login(LoginUser loginUser, ValueBack<LoginResult> back) {
		LoginService ls = this.appContext.getObject(LoginService.class);
		ServerHandler sh = this.appContext.getObject(ServerHandler.class);
		sh.load((si) -> {
			if (si.isSuccess()) {
				ls.login(loginUser, back);
			} else {
				back.back(si, null);
			}
		});
	}

	public void auth(String token, User user, InfoBack back) {
		InfoBack authBack = (info) -> {
			back.back(info);
			if (info.isSuccess()) {
				LoginService ls = this.appContext.getObject(LoginService.class);
				ls.authSucceed(token, user);

				EnterService es = appContext.getObject(EnterService.class);
				es.enter();
			}
		};
		LoginService ls = this.appContext.getObject(LoginService.class);
		ls.auth(token, authBack);
	}

	public void relogin() {
		AuthStore as = appContext.getObject(AuthStore.class);
		if (as.isAuth()) {
			LoginUserStore lus = appContext.getObject(LoginUserStore.class);
			if (null != lus.getLoginUser()) {
				login(lus.getLoginUser(), (info, lr) -> {
					if (info.isSuccess()) {
						auth(lr.getToken(), lr.getUser(), (ai) -> {

						});
					}
				});
			}
		}
	}

	public void reauth(InfoBack back) {
		AuthStore as = appContext.getObject(AuthStore.class);
		if (as.isAuth()) {
			String token = as.getToken();
			InfoBack authBack = (info) -> {
				back.back(info);
				if (info.isSuccess()) {
					EnterService es = appContext.getObject(EnterService.class);
					es.enter();
				}
			};
			LoginService ls = this.appContext.getObject(LoginService.class);
			ls.auth(token, authBack);
		}
	}
}
