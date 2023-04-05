
package com.oimchat.client.platform.kernel.work.common.box;

import java.net.URL;

/**
 * Description <br>
 * Date 2021-04-02 16:18:27<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class SystemInformationHeadBox {

	private URL headUrl;

	public SystemInformationHeadBox() {
		headUrl = this.getClass().getResource("/general/resources/common/images/common/head/system/inform.png");
	}

	public URL getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(URL headUrl) {
		this.headUrl = headUrl;
	}
}
