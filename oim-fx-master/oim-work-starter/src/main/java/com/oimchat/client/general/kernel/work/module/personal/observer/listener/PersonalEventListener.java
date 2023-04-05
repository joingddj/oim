
package com.oimchat.client.general.kernel.work.module.personal.observer.listener;

import com.oimchat.client.general.basic.message.data.Client;

/**
 * Description <br>
 * Date 2021-03-30 15:38:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface PersonalEventListener {

	public void otherOnline(Client client, boolean offline);

	public void updatePassword();
}
