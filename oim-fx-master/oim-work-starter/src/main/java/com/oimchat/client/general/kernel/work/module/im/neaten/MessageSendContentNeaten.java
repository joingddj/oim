
package com.oimchat.client.general.kernel.work.module.im.neaten;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.common.asyn.ValueBack;

/**
 * Description <br>
 * Date 2021-03-22 17:21:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface MessageSendContentNeaten {

	void neaten(Content content, ValueBack<Content> back);
}
