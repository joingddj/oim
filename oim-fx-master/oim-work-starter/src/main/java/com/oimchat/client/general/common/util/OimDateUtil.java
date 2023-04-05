package com.oimchat.client.general.common.util;

import java.util.Date;

import com.onlyxiahui.common.utils.base.util.time.DateUtil;

/**
 * @author XiaHui
 * @date 2017年6月7日 下午2:00:24
 */
public class OimDateUtil {

	/**
	 * 距今多少年
	 * 
	 * @author XiaHui
	 * @date 2017年6月7日 下午2:03:42
	 * @param date
	 * @return
	 */
	public static int beforePresentYearCount(Date date) {
		Date cd = new Date();
		int days = DateUtil.getBetweenDays(date, cd);
		int year = (days / 365);
		return year;
	}

	public static void main(String[] arg) {
		Date date = DateUtil.parse("1989-09-24");
		System.out.println(beforePresentYearCount(date));
	}
}
