
package com.oimchat.client.general.kernel.work.module.common.util;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.type.UserStatus;

/**
 * Description <br>
 * Date 2021-03-16 16:34:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserInfoUtil {

	public static String getShowName(User user, String remark) {
		String showName = "";
		if (null != user) {
			showName = StringSelectUtil.select(
					StringSelectUtil.value(1, remark),
					StringSelectUtil.value(2, user.getNickname()),
					StringSelectUtil.value(3, user.getAccount()),
					StringSelectUtil.value(4, user.getNumber() + ""),
					StringSelectUtil.value(5, user.getName()));
		} else if (null != remark) {
			showName = remark;
		}
		return showName;
	}

	public static String getShowName(User user) {
		return getShowName(user, null);
	}

	public static boolean isOffline(String status) {
		boolean offline = true;
		if (UserStatus.status_offline.equals(status) || UserStatus.status_invisible.equals(status)) {
			offline = true;
		} else {
			offline = false;
		}
		return offline;
	}
}
