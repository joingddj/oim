
package com.oimchat.client.platform.fx.module.list.function;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.view.ui.classics.module.main.menu.GroupContextMenu;
import com.oimchat.client.general.kernel.work.module.group.box.PersonalGroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.platform.common.view.GroupDataView;
import com.oimchat.client.platform.common.view.GroupEditView;
import com.oimchat.client.platform.fx.module.chat.interaction.GroupChatInteraction;
import com.oimchat.client.platform.fx.module.group.wrap.GroupSettingViewWrap;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Description <br>
 * Date 2021-03-17 16:19:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupJoinedHeadFunction extends AbstractMaterial {
	protected GroupContextMenu<Group> gcm = new GroupContextMenu<>();

	public GroupJoinedHeadFunction(AppContext appContext) {
		super(appContext);
		initialize();
	}

	protected void initialize() {
		gcm.setUpdateAction(g -> {
			GroupEditView view = appContext.getObject(GroupEditView.class);
			view.setGroupId(g.getId());
			view.setVisible(true);
		});

		gcm.setShowAction(g -> {
			GroupDataView v = appContext.getObject(GroupDataView.class);
			v.setGroupId(g.getId());
			v.setVisible(true);
		});

		gcm.setSettingAction(g -> {
			GroupSettingViewWrap v = appContext.getObject(GroupSettingViewWrap.class);
			v.setGroupId(g.getId());
			v.setVisible(true);
		});
	}

	public void setEvent(HeadItem head, Group group) {
		head.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent e) {
				PersonalGroupMemberBox pb = appContext.getObject(PersonalGroupMemberBox.class);
				boolean isOwner = pb.isOwner(group.getId());
				gcm.showEdit(isOwner);
				gcm.setGroup(group);
				gcm.show(head, e.getScreenX(), e.getScreenY());
				e.consume();
			}
		});
		head.setOnMouseClicked((MouseEvent me) -> {
			if (me.getClickCount() == 2) {
				GroupChatInteraction cs = appContext.getObject(GroupChatInteraction.class);
				cs.showChat(group);
			}
			me.consume();
		});
	}

	public void setInfo(HeadItem head, Group group) {
		head.addAttribute(Group.class, group);
		FxUtil.invoke(() -> {
			head.setRemark(group.getName());
			head.setNickname("(" + group.getNumber() + ")");
			head.setShowText(group.getIntroduce());
		});
		GroupHeadImageHandler him = this.appContext.getObject(GroupHeadImageHandler.class);
		him.loadAvatarImage(group.getHead(), group.getAvatar(), (image) -> {
			FxUtil.invoke(() -> {
				head.setHeadImage(image);
			});
		});
	}
}
