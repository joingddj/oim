
package com.oimchat.client.general.kernel.work.module.im.observer;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.event.ValueObservable;
import com.oimchat.client.general.kernel.work.module.im.data.vo.GroupMessageData;

/**
 * Description <br>
 * Date 2021-03-22 17:05:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupCahtListener {

	ValueObservable<GroupMessageData> messageBox = new ValueObservable<>();

	public void addMessageValueAcation(ValueAction<GroupMessageData> valueAction) {
		messageBox.add(valueAction);
	}

	public void messageValue(GroupMessageData value) {
		messageBox.execute(value);
	}
}
