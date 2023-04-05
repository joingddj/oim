
package com.oimchat.client.general.kernel.work.module.im.controller;

import com.oimchat.client.general.kernel.work.module.im.manager.UserChatDataManager;
import com.oimchat.client.general.kernel.work.module.im.service.UserChatPromptService;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-23 18:42:02<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatDataController extends AbstractMaterial {

	public UserChatDataController(AppContext appContext) {
		super(appContext);
	}

	public void readByUser(String sendUserId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownUserId = pb.getOwnerUserId();
		boolean isOwn = sendUserId.equals(ownUserId);
		if (!isOwn) {
			UserChatPromptService promptService = this.appContext.getObject(UserChatPromptService.class);
			promptService.removePrompt(sendUserId);

			UserChatDataManager manager = this.appContext.getObject(UserChatDataManager.class);
			manager.updateToReadBySendUserId(sendUserId, (info) -> {
			});
		}
	}

	public void readByContent(String sendUserId, String contentId) {
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownUserId = pb.getOwnerUserId();
		boolean isOwn = sendUserId.equals(ownUserId);
		if (!isOwn) {
			UserChatPromptService promptService = this.appContext.getObject(UserChatPromptService.class);
			promptService.removePrompt(sendUserId);

			UserChatDataManager manager = this.appContext.getObject(UserChatDataManager.class);
			manager.updateToReadByContentId(sendUserId, contentId, (info) -> {
			});
		}
	}
}
