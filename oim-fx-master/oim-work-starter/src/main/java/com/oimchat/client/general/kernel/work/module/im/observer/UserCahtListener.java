
package com.oimchat.client.general.kernel.work.module.im.observer;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.event.ValueObservable;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageData;
import com.oimchat.client.general.kernel.work.module.im.data.vo.UserMessageVO;

/**
 * Description <br>
 * Date 2021-03-22 17:05:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserCahtListener {

	ValueObservable<UserMessageData> messageBox = new ValueObservable<>();
	ValueObservable<UserMessageVO<Motion>> motionBox = new ValueObservable<>();

	public void addMessageValueAcation(ValueAction<UserMessageData> valueAction) {
		messageBox.add(valueAction);
	}

	public void messageValue(UserMessageData value) {
		messageBox.execute(value);
	}

	public void addMotionValueAcation(ValueAction<UserMessageVO<Motion>> valueAction) {
		motionBox.add(valueAction);
	}

	public void motionValue(UserMessageVO<Motion> value) {
		motionBox.execute(value);
	}
}
