
package com.oimchat.client.platform.common.config.login.data;

/**
 * Description <br>
 * Date 2021-03-11 10:34:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginSaveData {

	private LoginSettingData setting = new LoginSettingData();
	private LoginUserData user = new LoginUserData();

	public String getAccount() {
		return (null != user) ? user.getAccount() : "";
	}

	public LoginSettingData getSetting() {
		return setting;
	}

	public void setSetting(LoginSettingData setting) {
		this.setting = setting;
	}

	public LoginUserData getUser() {
		return user;
	}

	public void setUser(LoginUserData user) {
		this.user = user;
	}
}
