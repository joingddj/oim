
package com.oimchat.client.platform.fx.module.chat.function;

import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.im.controller.UserChatDataController;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Description <br>
 * Date 2021-03-24 11:02:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserChatItemFunction extends AbstractMaterial {

	public UserChatItemFunction(AppContext appContext) {
		super(appContext);
	}

	public void setEvent(HeadCloseItem head, User user) {
		head.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent e) {
				e.consume();
			}
		});
		head.setOnMouseClicked((MouseEvent me) -> {
			me.consume();
			if (me.getClickCount() == 1) {
				UserChatDataController cs = this.appContext.getObject(UserChatDataController.class);
				cs.readByUser(user.getId());
			}
		});
	}

	public void setInfo(HeadCloseItem head, User user) {
		String userId = user.getId();
		UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);
		String name = UserInfoUtil.getShowName(user);
		head.addAttribute(User.class, user);
		FxUtil.invoke(() -> {
			head.setRemark(name);
			if (UserInfoUtil.isOffline(user.getStatus())) {
				head.setGray(true);
			} else {
				head.setGray(false);
			}
		});
		uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (image) -> {
			FxUtil.invoke(() -> {
				head.setHeadImage(image);
			});
		});
		ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
		cch.getByContactUserId(userId, (info, r) -> {
			if (null != r && StringUtil.isNotBlank(r.getRemark())) {
				FxUtil.invoke(() -> {
					head.setRemark(r.getRemark());
				});
			}
		});
	}
}
