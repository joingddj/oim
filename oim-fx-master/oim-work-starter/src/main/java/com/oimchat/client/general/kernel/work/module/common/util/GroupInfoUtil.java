
package com.oimchat.client.general.kernel.work.module.common.util;

import com.oimchat.client.general.kernel.work.module.group.entity.Group;

/**
 * Description <br>
 * Date 2021-03-16 16:40:18<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInfoUtil {
	public static String getShowName(Group group, String remark) {
		String showName = "";
		if (null != group) {
			showName = StringSelectUtil.select(
					StringSelectUtil.value(1, remark),
					StringSelectUtil.value(2, group.getName()),
					StringSelectUtil.value(3, group.getNumber() + ""),
					StringSelectUtil.value(4, group.getId()));
		} else if (null != remark) {
			showName = remark;
		}
		return showName;
	}

	public static String getShowName(Group group) {
		return getShowName(group, null);
	}
}
