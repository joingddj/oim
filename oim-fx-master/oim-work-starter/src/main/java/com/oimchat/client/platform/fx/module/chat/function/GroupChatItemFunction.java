
package com.oimchat.client.platform.fx.module.chat.function;

import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.client.general.kernel.work.module.common.util.GroupInfoUtil;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupRelationHandler;
import com.oimchat.client.general.kernel.work.module.im.service.GroupChatPromptService;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
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

public class GroupChatItemFunction extends AbstractMaterial {

	public GroupChatItemFunction(AppContext appContext) {
		super(appContext);
	}

	public void setEvent(HeadCloseItem head, Group group) {
		head.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent e) {
				e.consume();
			}
		});
		head.setOnMouseClicked((MouseEvent me) -> {
			me.consume();
			if (me.getClickCount() == 1) {
				GroupChatPromptService cs = this.appContext.getObject(GroupChatPromptService.class);
				cs.removePrompt(group.getId());
			}
		});
	}

	public void setInfo(HeadCloseItem head, Group group) {
		String groupId = group.getId();
		GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);
		String name = GroupInfoUtil.getShowName(group);
		head.addAttribute(Group.class, group);
		FxUtil.invoke(() -> {
			head.setRemark(name);
		});
		uhim.loadAvatarImage(group.getHead(), group.getAvatar(), (image) -> {
			FxUtil.invoke(() -> {
				head.setHeadImage(image);
			});
		});
		GroupRelationHandler cch = this.appContext.getObject(GroupRelationHandler.class);
		cch.getByGroupId(groupId, (info, r) -> {
			if (null != r && StringUtil.isNotBlank(r.getRemark())) {
				FxUtil.invoke(() -> {
					head.setRemark(r.getRemark());
				});
			}
		});
	}
}
