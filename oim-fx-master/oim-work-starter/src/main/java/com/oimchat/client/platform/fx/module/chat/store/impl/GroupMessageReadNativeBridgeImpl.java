
package com.oimchat.client.platform.fx.module.chat.store.impl;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.im.data.dto.GroupChatData;
import com.oimchat.client.general.kernel.work.module.im.handler.MessageContentWrapHandler;
import com.oimchat.client.general.kernel.work.module.im.manager.GroupChatDataManager;
import com.oimchat.client.general.kernel.work.module.im.util.ChatUtil;
import com.oimchat.client.general.kernel.work.module.im.util.ContentTimeUtil;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.view.operator.GroupChatOperator;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-02 12:10:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMessageReadNativeBridgeImpl extends BaseMessageReadNativeBridgeImpl {

	private String groupId;
	private String lastMessageKey;
	private boolean loadHistory = false;
	private String ownerAvatar;

	public GroupMessageReadNativeBridgeImpl(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void resend(String key) {
		GroupChatOperator co = this.appContext.getObject(GroupChatOperator.class);
		co.resend(groupId, key);
	}

	@Override
	public void onScrollTop(int elementSize) {
		if (!loadHistory) {
			if (elementSize > 300) {
				readMapper.setTextPrompt("更多信息请查看历史记录");
			} else {
				loadHistory = true;
				lastMessageKey = readMapper.getFirstMessageKey();
				loadHistory(groupId, lastMessageKey);
			}
		}
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			laodData();
		});
	}

	public void loadHistory(String groupId, String messageKey) {

		String direction = "before";
		GroupChatDataManager cs = appContext.getObject(GroupChatDataManager.class);

		cs.getListFromStartMessageKey(groupId, messageKey, direction, 30, (info, lb) -> {
			List<GroupChatData> list = (null != lb && null != lb.getItems()) ? lb.getItems() : new ArrayList<>();
			setList(groupId, list);
			if (StringUtil.isNotBlank(lastMessageKey)) {
				FxUtil.invoke(() -> {
					readMapper.updateScrollIntoView(lastMessageKey);
				});
			}
			loadHistory = false;
		});
	}

	private void setList(String groupId, List<GroupChatData> list) {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		String ownerUserId = pb.getOwnerUserId();

		MessageContentWrapHandler wh = this.appContext.getObject(MessageContentWrapHandler.class);
		list.sort((a, b) -> {
			Content contentA = a.getContent();
			Content contentB = b.getContent();
			long timeA = (null == contentA) ? 0L : contentA.getTimestamp();
			long timeB = (null == contentB) ? 0L : contentB.getTimestamp();
			long t = timeB - timeA;
			if (t > 0) {
				return 1;
			} else if (t < 0) {
				return -1;
			} else {
				return 0;
			}
		});

		for (GroupChatData cd : list) {

			int status = 1;

			UserSimple user = cd.getUser();

			String userId = user.getId();
			Content content = cd.getContent();

			boolean isOwn = ownerUserId.equals(userId);

			boolean timeVisible = true;
			String timeText = ContentTimeUtil.getChatShowTime(content.getTimestamp());

			String name = ChatUtil.getShowName(user.getNickname(), user.getRemark());
			wh.neaten(content, (info, c) -> {
				MessageContentWrap contentWrap = wh.wrap(user, c, name, status, timeVisible, timeText, isOwn, true);
				content(contentWrap);
			});
		}
	}

	private void content(MessageContentWrap contentWrap) {

		if (contentWrap.isOwn()) {
			contentWrap.setAvatar(ownerAvatar);
			contentWrap.getUser().setAvatar(ownerAvatar);
			insert(contentWrap);
		} else {
			UserSimple user = contentWrap.getUser();
			if (StringUtil.isBlank(user.getAvatar())) {
				UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
				hi.loadHeadUrl(user.getHead(), (url) -> {
					contentWrap.setAvatar(url.toExternalForm());
					contentWrap.getUser().setAvatar(url.toExternalForm());
					insert(contentWrap);
				});
			} else {
				insert(contentWrap);
			}
		}
	}

	private void insert(MessageContentWrap contentWrap) {
		String json = JsonUtil.toJson(contentWrap);
		FxUtil.invoke(() -> {
			readMapper.insertBefore(json);
		});
	}

	private void laodData() {

		UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User ownerUser = pb.getUser();
		hi.loadAvatarUrl(ownerUser.getHead(), ownerUser.getAvatar(), (url) -> {
			if (null != url) {
				ownerAvatar = url.toExternalForm();
			}
		});
	}
}
