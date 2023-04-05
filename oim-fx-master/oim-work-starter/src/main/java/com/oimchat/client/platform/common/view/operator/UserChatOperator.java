
package com.oimchat.client.platform.common.view.operator;

import java.util.List;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.Item;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.net.file.bean.FileData;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.im.controller.UserChatController;
import com.oimchat.client.general.kernel.work.module.im.data.dto.Motion;
import com.oimchat.client.general.kernel.work.module.im.neaten.MessageSendContentNeaten;
import com.oimchat.client.general.kernel.work.module.im.type.MotionTypeEnum;
import com.oimchat.client.platform.kernel.work.module.im.handler.UserContentSendHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-24 14:50:01<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatOperator extends BaseChatOperator {

	public UserChatOperator(AppContext appContext) {
		super(appContext);
	}

	public void send(final String receiveUserId, final FileData fd, InfoBack back) {
		Content content = getFileContent(fd);
		send(receiveUserId, content, back);
	}

	public void send(final String receiveUserId, final Content content, InfoBack back) {
		String text = CoreContentUtil.getAllText(content);
		int itemSize = CoreContentUtil.getItemSize(content);
		Info info = new Info();
		if (text.length() > 10000 || itemSize > 1000) {
			info.addWarning("4.001", "内容过长！");
			back.back(info);
		} else {
			back.back(info);
			UserChatController cc = this.appContext.getObject(UserChatController.class);
			MessageSendContentNeaten n = this.appContext.getObject(MessageSendContentNeaten.class);
			n.neaten(content, (i, c) -> {
				List<Item> imageItems = CoreContentUtil.getImageItemList(content);
				if (!imageItems.isEmpty()) {
					UserContentSendHandler sh = this.appContext.getObject(UserContentSendHandler.class);
					sh.uploadImages(imageItems);
				}
				cc.send(receiveUserId, content, back);
			});
		}
	}

	public void resend(String receiveUserId, String messageKey) {
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			UserChatController cc = this.appContext.getObject(UserChatController.class);
			cc.resend(receiveUserId, messageKey, (i) -> {

			});
		});
	}

	public void shake(String receiveUserId) {
		Motion m = new Motion();
		m.setType(MotionTypeEnum.shake.getCode());

		UserChatController cc = this.appContext.getObject(UserChatController.class);
		cc.send(receiveUserId, m, (i) -> {

		});
	}
}
