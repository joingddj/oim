
package com.oimchat.client.platform.fx.work.inform.service;

import com.oimchat.client.general.kernel.work.module.im.util.ContentTimeUtil;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.common.inform.type.SoundType;
import com.oimchat.client.platform.fx.module.list.interaction.GeneralApplyMessageInteraction;
import com.oimchat.client.platform.fx.module.list.store.GeneralApplyMessageListItemStore;
import com.oimchat.client.platform.kernel.work.common.box.SystemInformationHeadBox;
import com.oimchat.client.platform.kernel.work.module.inform.box.GeneralApplyUnreadBox;
import com.oimchat.client.platform.kernel.work.module.inform.hanlder.SoundHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-02 16:03:36<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyInformationService extends AbstractMaterial {

	public GeneralApplyInformationService(AppContext appContext) {
		super(appContext);
	}

	public void inform(int type, long count, boolean add) {
		if (count < 0) {
			return;
		}
		String id = type + "";

		String showTime = ContentTimeUtil.getChatShowTime(System.currentTimeMillis());

		GeneralApplyMessageListItemStore store = this.appContext.getObject(GeneralApplyMessageListItemStore.class);
		store.addOrUpdateItem(type);
		store.updateItemTimeInfo(id, showTime);

		SystemInformationHeadBox headBox = this.appContext.getObject(SystemInformationHeadBox.class);

		String key = store.getKeyById(id);

		GeneralApplyUnreadBox unreadBox = this.appContext.getObject(GeneralApplyUnreadBox.class);
		if (add) {
			unreadBox.plusUnreadCount(type, count);
		} else {
			unreadBox.setUnreadCount(type, count);
		}

		PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
		pdbox.put(key, headBox.getHeadUrl(), () -> {

			GeneralApplyMessageInteraction apply = appContext.getObject(GeneralApplyMessageInteraction.class);
			apply.show(type);
		});

		SoundHandler soundHandler = this.appContext.getObject(SoundHandler.class);
		soundHandler.put(SoundType.sound_type_system);
	}

	public void inform(int type) {
		inform(type, 1, true);
	}
	
	public void removePrompt(int type) {

		GeneralApplyMessageListItemStore store = appContext.getObject(GeneralApplyMessageListItemStore.class);
		String key = store.getKeyById(type + "");

		PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
		pdbox.remove(key);

		GeneralApplyUnreadBox unreadBox = this.appContext.getObject(GeneralApplyUnreadBox.class);
		unreadBox.setUnreadCount(type, 0);
	}
}
