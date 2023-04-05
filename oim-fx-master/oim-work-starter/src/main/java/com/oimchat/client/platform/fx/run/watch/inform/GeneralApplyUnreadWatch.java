
package com.oimchat.client.platform.fx.run.watch.inform;

import com.oimchat.client.platform.fx.common.watch.AbstractWatch;
import com.oimchat.client.platform.fx.module.list.store.GeneralApplyMessageListItemStore;
import com.oimchat.client.platform.kernel.work.module.inform.box.GeneralApplyUnreadBox;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-29 20:09:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyUnreadWatch extends AbstractWatch {

	public GeneralApplyUnreadWatch(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void initialize() {
		GeneralApplyUnreadBox unreadBox = this.appContext.getObject(GeneralApplyUnreadBox.class);
		unreadBox.add((id, count) -> {
			setUnread(id, count);
		});
	}

	private void setUnread(int id, long unreadCount) {
		GeneralApplyMessageListItemStore store = this.appContext.getObject(GeneralApplyMessageListItemStore.class);

		boolean red = unreadCount > 0;
		String redText = (unreadCount > 99) ? "99" : unreadCount + "";
		store.updateItemRed(id + "", red, redText);
	}
}
