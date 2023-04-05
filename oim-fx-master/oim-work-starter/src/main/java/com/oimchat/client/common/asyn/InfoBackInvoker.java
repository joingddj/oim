
package com.oimchat.client.common.asyn;

import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-11 16:30:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class InfoBackInvoker {

	public static void back(InfoBack ib, Info info) {
		if (null != ib) {
			ib.back(info);
		}
	}

	public static void error(InfoBack ib, String code, String text) {
		back(ib, InfoBuilder.error(code, text));
	}

	public static void warning(InfoBack ib, String code, String text) {
		back(ib, InfoBuilder.warning(code, text));
	}

	public static void prompt(InfoBack ib, String code, String text) {
		back(ib, InfoBuilder.prompt(code, text));
	}
}
