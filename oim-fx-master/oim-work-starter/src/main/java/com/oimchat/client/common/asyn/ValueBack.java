
package com.oimchat.client.common.asyn;

import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-12 14:29:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface ValueBack<T> {

	void back(Info info, T value);
}
