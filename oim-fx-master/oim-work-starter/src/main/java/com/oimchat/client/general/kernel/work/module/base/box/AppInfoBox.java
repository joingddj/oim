
package com.oimchat.client.general.kernel.work.module.base.box;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.common.app.AppInfo;

/**
 * Description <br>
 * Date 2021-03-11 17:13:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AppInfoBox {

	AppInfo appInfo = new AppInfo();

	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public Client getClient() {
		Client c = new Client();
		BeanUtils.copyProperties(appInfo, c);
		return c;
	}
}
