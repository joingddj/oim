
package com.oimchat.client.platform.fx.module.list.function;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.im.util.ChatUtil;
import com.oimchat.client.platform.fx.module.chat.interaction.UserChatInteraction;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Description <br>
 * Date 2021-03-29 21:07:32<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserMessageItemFunction extends AbstractMaterial {

	public UserMessageItemFunction(AppContext appContext) {
		super(appContext);
		initialize();
	}

	protected void initialize() {

	}

	public void setEvent(HeadItem head) {
		head.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent e) {
				e.consume();
			}
		});
		head.setOnMouseClicked((MouseEvent me) -> {
			me.consume();
			if (me.getClickCount() == 2) {
				User user = head.getAttribute(User.class);
				UserChatInteraction cs = this.appContext.getObject(UserChatInteraction.class);
				cs.showChat(user);
			}
		});
	}

	public void setInfo(HeadItem head, User user, String remark) {

		String status = user.getStatus();
		String showName = ChatUtil.getShowName(user, remark);
		boolean gray = UserInfoUtil.isOffline(status);
		FxUtil.invoke(() -> {
			// 备注名
			head.setRemark(showName);
			head.addAttribute(User.class, user);
			// 如果用户不是在线状态，则使其头像变灰
			head.setGray(gray);
		});

		UserHeadImageHandler him = this.appContext.getObject(UserHeadImageHandler.class);
		him.loadAvatarImage(user.getHead(), user.getAvatar(), (image) -> {
			FxUtil.invoke(() -> {
				head.setHeadImage(image);
			});
		});
	}

	public void setText(HeadItem head, String text) {
		FxUtil.invoke(() -> {
			head.setShowText(text);
		});
	}
}
