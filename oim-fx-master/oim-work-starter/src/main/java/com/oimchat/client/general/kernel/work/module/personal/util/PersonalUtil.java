
package com.oimchat.client.general.kernel.work.module.personal.util;

import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * Description <br>
 * Date 2021-03-19 23:21:32<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PersonalUtil {

	public static String getShowName(User user) {
		String name = "";
		if (null != user) {
			name = user.getNickname();
			if (null == name || "".equals(name)) {
				name = user.getName();
			}
			if (null == name || "".equals(name)) {
				name = user.getAccount();
			}
			if (null == name || "".equals(name)) {
				name = user.getNumber() + "";
			}
		}
		return name;
	}
}
