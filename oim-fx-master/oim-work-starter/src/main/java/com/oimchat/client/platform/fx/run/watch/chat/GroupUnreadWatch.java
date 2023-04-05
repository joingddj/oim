
package com.oimchat.client.platform.fx.run.watch.chat;

import com.oimchat.client.general.kernel.work.module.im.inform.GroupUnreadBox;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.module.chat.store.GroupChatPaneStore;
import com.oimchat.client.platform.fx.module.list.wrap.GroupListViewWrap;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 20:09:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupUnreadWatch extends AbstractWatch {

	public GroupUnreadWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		GroupUnreadBox ub = this.appContext.getObject(GroupUnreadBox.class);
		ub.add((id, count) -> {
			setUnread(id, count);
		});
	}

	private void setUnread(String id, long count) {
		boolean red = count > 0;
		String redText = (count > 99 ? 99 : count) + "";
		GroupListViewWrap listItem = this.appContext.getObject(GroupListViewWrap.class);
		listItem.updateItem(id, red, redText);

		GroupChatPaneStore store = this.appContext.getObject(GroupChatPaneStore.class);
		store.updateItemRed(id, red, redText);

		if (!red) {
			String key = store.getKey(id);
			PromptDataBox pdbox = this.appContext.getObject(PromptDataBox.class);
			pdbox.remove(key);
		}
	}
}
