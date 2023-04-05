
package com.oimchat.client.general.kernel.work.module.core.util;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.type.UserStatus;

/**
 * Description <br>
 * Date 2021-04-01 22:22:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserStatusUtil {

	public static int getStatusCount(List<User> list, String status) {
		int i = 0;
		if (null != list && null != status) {
			for (User u : list) {
				if (status.equals(u.getStatus())) {
					i++;
				}
			}
		}
		return i;
	}

	public static int getOnlineCount(List<User> list) {
		int i = 0;
		if (null != list) {
			for (User u : list) {
				if (!UserStatus.status_invisible.equals(u.getStatus())
						&& !UserStatus.status_offline.equals(u.getStatus())) {
					i++;
				}
			}
		}
		return i;
	}
}
