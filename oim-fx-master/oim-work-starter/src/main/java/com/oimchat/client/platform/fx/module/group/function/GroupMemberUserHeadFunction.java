
package com.oimchat.client.platform.fx.module.group.function;

import com.oimchat.app.fx.base.component.head.HeadSimpleItem;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.im.util.ChatUtil;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * Description <br>
 * Date 2021-03-17 16:20:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMemberUserHeadFunction extends AbstractMaterial {

	public GroupMemberUserHeadFunction(AppContext appContext) {
		super(appContext);
	}

	public void setEvent(HeadSimpleItem head) {
		head.setOnContextMenuRequested(e -> {
			e.consume();
		});
		head.setOnMouseClicked((me) -> {
			me.consume();
			if (me.getClickCount() == 2) {

			}
		});
	}

	public void setInfo(HeadSimpleItem head, User user, GroupMember groupMember) {
		String status = user.getStatus();
		String showName = ChatUtil.getShowName(user, groupMember.getNickname());
		boolean gray = UserInfoUtil.isOffline(status);
		FxUtil.invoke(() -> {
			head.setName(showName);
			head.addAttribute(User.class, user);
			head.addAttribute(GroupMember.class, groupMember);
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
}
