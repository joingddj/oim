
package com.oimchat.client.general.kernel.support.file.upload;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.client.general.common.constant.ServerConstant.ServerType;
import com.oimchat.client.general.kernel.support.file.constant.FileSeverApi;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.kernel.work.module.server.box.ServerBox;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.aware.basic.work.type.net.Protocol;

/**
 * Description <br>
 * Date 2021-03-16 10:11:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserHeadUploader extends AbstractUploader {

	public UserHeadUploader(AppContext appContext) {
		super(appContext);
	}

	@Override
	public Map<String, String> getParameterMap() {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		String userId = pb.getOwnerUserId();
		final Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userId", userId);
		return dataMap;
	}

	@Override
	public String getPath() {
		return FileSeverApi.USER_HEAD_UPLOAD;
	}

	@Override
	public ServerAddressData getServerAddress() {
		ServerBox serverBox = this.appContext.getObject(ServerBox.class);
		ServerAddressData address = serverBox.getAddress(ServerType.file.value(), Protocol.HTTP.value());
		return address;
	}

	@Override
	public String getServerName() {
		return "头像上传服务器";
	}
}
