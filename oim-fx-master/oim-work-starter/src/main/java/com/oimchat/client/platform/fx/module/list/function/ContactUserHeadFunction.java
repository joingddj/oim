
package com.oimchat.client.platform.fx.module.list.function;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.platform.fx.module.chat.interaction.UserChatInteraction;
import com.oimchat.client.platform.fx.module.list.wrap.ContactItemMenuWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Description <br>
 * Date 2021-03-17 16:20:31<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactUserHeadFunction extends AbstractMaterial {

	public ContactUserHeadFunction(AppContext appContext) {
		super(appContext);
	}

	public void setEvent(HeadItem head) {
		head.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent e) {
				String key = head.getKey();
				ContactItemMenuWrap menu = appContext.getObject(ContactItemMenuWrap.class);
				menu.setUserId(key);
				menu.show(head, e.getScreenX(), e.getScreenY());
				e.consume();
			}
		});
		head.setOnMouseClicked((MouseEvent me) -> {
			me.consume();
			if (me.getClickCount() == 2) {
				String key = head.getKey();
				UserChatInteraction cs = this.appContext.getObject(UserChatInteraction.class);
				cs.showChat(key);
			}
		});
	}

	public void setInfo(HeadItem head, User user, ContactRelation data) {
		if (null != user) {
//			UserHeadImageManager uhim = this.appContext.getObject(UserHeadImageManager.class);
//
//			String userId = data.getContactUserId();
//			String nodeKey = data.getCategoryId();
//
//			String itemKey = userId;
//			String remark = data.getRemark();
//
//			boolean hasRemark = StringUtil.isNotBlank(remark);
//			String showRemark = "";
//			String showName = "";
//
//			if (hasRemark) {
//				String name = UserInfoUtil.getShowName(user);
//				showRemark = remark;
//				showName = "(" + name + ")";
//			} else {
//				String name = UserInfoUtil.getShowName(user);
//				showRemark = name;
//				showName = "";
//			}
//
//			String status = user.getStatus();
//			boolean gray = UserInfoUtil.isOffline(status);
//			String signature = user.getSignature();
		}
	}
}
