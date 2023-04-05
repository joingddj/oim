
package com.oimchat.client.general.kernel.work.common.call;

import com.oimchat.client.basic.util.PathUtil;

/**
 * Description <br>
 * Date 2021-03-12 11:10:07<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PathBuilder {

	public static String path(String action, String method) {
		return PathUtil.merge(action, method);
	}
}
