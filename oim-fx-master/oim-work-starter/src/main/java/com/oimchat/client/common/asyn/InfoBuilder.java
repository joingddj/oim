
package com.oimchat.client.common.asyn;

import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-11 16:29:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class InfoBuilder {

	public static Info get() {
		Info info = new Info();
		return info;
	}

	public static Info error(String code, String text) {
		Info info = get();
		info.addError(code, text);
		return info;
	}

	public static Info warning(String code, String text) {
		Info info = get();
		info.addWarning(code, text);
		return info;
	}

	public static Info prompt(String code, String text) {
		Info info = get();
		info.addPrompt(code, text);
		return info;
	}
}
