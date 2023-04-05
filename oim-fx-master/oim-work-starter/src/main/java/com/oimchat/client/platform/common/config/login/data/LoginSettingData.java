
package com.oimchat.client.platform.common.config.login.data;

/**
 * Description <br>
 * Date 2021-03-11 10:34:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginSettingData {

	private boolean autoLogin = false;
	private boolean savePassword = false;

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public boolean isSavePassword() {
		return savePassword;
	}

	public void setSavePassword(boolean savePassword) {
		this.savePassword = savePassword;
	}
}
