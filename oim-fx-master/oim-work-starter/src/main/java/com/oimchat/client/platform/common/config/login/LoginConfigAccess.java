
package com.oimchat.client.platform.common.config.login;

import com.oimchat.client.platform.basic.constant.AppConstant;
import com.oimchat.client.platform.common.config.base.BaseConfigAccess;
import com.oimchat.client.platform.common.config.login.data.LoginConfig;

/**
 * Description <br>
 * Date 2021-03-10 17:35:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginConfigAccess extends BaseConfigAccess<LoginConfig> {

	String path = "/config/login/LoginConfig.json";

	@Override
	public String getFilePath() {
		return AppConstant.getAppHomePath() + path;
	}

	@Override
	public boolean isCache() {
		return false;
	}
}
