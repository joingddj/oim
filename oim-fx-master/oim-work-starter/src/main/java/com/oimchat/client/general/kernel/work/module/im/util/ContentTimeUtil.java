
package com.oimchat.client.general.kernel.work.module.im.util;

import java.util.Date;

import com.onlyxiahui.common.utils.base.util.time.DateUtil;

/**
 * Description <br>
 * Date 2021-03-23 09:52:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContentTimeUtil {

	public static String getChatShowTime(long timestamp) {
		String time = "";
		Date date = new Date(timestamp);
		long dateTimestamp = System.currentTimeMillis();
		long temp = (dateTimestamp - timestamp);
		boolean isOverDay = temp > (1000 * 60 * 60 * 12);
		boolean isOverYear = !DateUtil.getCurrentYear().equals(DateUtil.format(date, "yyyy"));
		if (isOverYear) {
			time = DateUtil.format(date, "yyyy-MM-dd hh:mm:ss");
		} else if (isOverDay) {
			time = DateUtil.format(date, "MM-dd hh:mm:ss");
		} else {
			time = DateUtil.format(date, "hh:mm:ss");
		}
		return time;
	}
}
