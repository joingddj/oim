
package com.oimchat.client.general.kernel.work.module.contact.observer.listener;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddResultData;

/**
 * Description <br>
 * Date 2021-03-30 16:48:28<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface ContactEventListener {

	void onReceiveApplyInfo(ContactAddApplyEntityCase apply);

	void onReceiveApplyResult(ContactAddResultData data);

	void updateBlocked(String contactUserId, String isBlocked);
}
