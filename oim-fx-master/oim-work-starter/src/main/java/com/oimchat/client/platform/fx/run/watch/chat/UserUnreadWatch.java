
package com.oimchat.client.platform.fx.run.watch.chat;

import com.oimchat.client.general.kernel.work.module.im.inform.UserUnreadBox;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.module.chat.store.UserChatPaneStore;
import com.oimchat.client.platform.fx.module.list.store.UserMessageListItemStore;
import com.oimchat.client.platform.fx.module.list.wrap.ContactListViewWrap;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 20:09:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserUnreadWatch extends AbstractWatch {

	public UserUnreadWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		UserUnreadBox ub = this.appContext.getObject(UserUnreadBox.class);
		ub.add((id, count) -> {
			setUnread(id, count);
		});
	}

	private void setUnread(String id, long count) {
		boolean red = count > 0;
		String redText = (count > 99 ? 99 : count) + "";
		ContactListViewWrap listItem = this.appContext.getObject(ContactListViewWrap.class);
		listItem.updateItem(id, red, redText);

		UserChatPaneStore store = this.appContext.getObject(UserChatPaneStore.class);
		store.updateItemRed(id, red, redText);

		UserMessageListItemStore mls = this.appContext.getObject(UserMessageListItemStore.class);
		mls.updateItemRed(id, red, redText);

		if (!red) {
			String key = store.getKey(id);
			PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
			pdbox.remove(key);
		}
	}
}
