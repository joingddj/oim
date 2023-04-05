
package com.oimchat.client.platform.fx.module.list.function;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.general.kernel.work.module.common.util.GroupInfoUtil;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.platform.fx.module.chat.interaction.GroupChatInteraction;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
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

public class GroupMessageItemFunction extends AbstractMaterial {

	public GroupMessageItemFunction(AppContext appContext) {
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
				Group group = head.getAttribute(Group.class);
				GroupChatInteraction cs = this.appContext.getObject(GroupChatInteraction.class);
				cs.showChat(group);
			}
		});
	}

	public void setInfo(HeadItem head, Group group, String remark) {

		String showName = GroupInfoUtil.getShowName(group, remark);
		FxUtil.invoke(() -> {
			// 备注名
			head.setRemark(showName);
			head.addAttribute(Group.class, group);
			// 如果用户不是在线状态，则使其头像变灰
		});

		GroupHeadImageHandler him = this.appContext.getObject(GroupHeadImageHandler.class);
		him.loadAvatarImage(group.getHead(), group.getAvatar(), (image) -> {
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
