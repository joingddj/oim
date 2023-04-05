
package com.oimchat.client.general.kernel.work.module.im.util;

import com.oimchat.client.general.kernel.work.module.common.util.StringSelectUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * Description <br>
 * Date 2021-03-28 11:42:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatUtil {

	public static String getShowName(User user, String... remarks) {
		String name = StringSelectUtil.select(
				StringSelectUtil.select(remarks),
				user.getNickname(),
				user.getAccount(),
				user.getNumber() + "");

		return name;
	}

	public static String getShowName(String... remarks) {
		return StringSelectUtil.select(remarks);
	}

	public static String getShow(String show1, String show2) {
		String show = StringSelectUtil.select(show1, show2);
		return show;
	}
}
