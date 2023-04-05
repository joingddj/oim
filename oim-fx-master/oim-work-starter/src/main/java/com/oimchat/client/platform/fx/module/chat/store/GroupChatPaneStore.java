
package com.oimchat.client.platform.fx.module.chat.store;

import javax.swing.event.HyperlinkEvent;

import org.codefx.libfx.control.webview.WebViewHyperlinkListener;
import org.codefx.libfx.control.webview.WebViews;

import com.oimchat.app.awt.desktop.util.LinkOpenUtil;
import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.general.kernel.work.module.common.util.GroupInfoUtil;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupRelationHandler;
import com.oimchat.client.general.kernel.work.module.im.box.GroupSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.service.GroupChatPromptService;
import com.oimchat.client.platform.fx.module.chat.function.ChatPaneComonFunction;
import com.oimchat.client.platform.fx.module.chat.function.ChatPaneGroupFunction;
import com.oimchat.client.platform.fx.module.chat.function.GroupChatItemFunction;
import com.oimchat.client.platform.fx.module.chat.store.impl.GroupMessageReadNativeBridgeImpl;
import com.oimchat.client.platform.fx.module.chat.store.impl.GroupMessageWriteNativeBridgeImpl;
import com.oimchat.client.platform.fx.module.group.function.GroupMemberListFunction;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-22 15:28:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupChatPaneStore extends BaseChatPaneStore<Group> {

	public GroupChatPaneStore(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String type() {
		return "group_chat";
	}

	@Override
	public String getKey(Group data) {
		return getKey(data.getId());
	}

	@Override
	protected void setInfo(ChatPane cp, Group data) {
		String groupId = data.getId();
		String name = GroupInfoUtil.getShowName(data);
		String signature = data.getIntroduce();
		FxUtil.invoke(() -> {
			cp.setText(signature);
			cp.setName(name);
		});
		GroupRelationHandler cch = this.appContext.getObject(GroupRelationHandler.class);
		cch.getByGroupId(groupId, (info, r) -> {
			if (null != r && StringUtil.isNotBlank(r.getRemark())) {
				FxUtil.invoke(() -> {
					cp.setName(r.getRemark());
				});
			}
		});
	}

	@Override
	protected void initialize(ChatPane cp, Group data) {

		String groupId = data.getId();

		GroupMessageReadNativeBridgeImpl out = this.appContext.createObject(GroupMessageReadNativeBridgeImpl.class);
		out.setGroupId(data.getId());
		out.setReadMapper(cp.getAreaPane().getReadPane().getMapper());
		FxUtil.invoke(() -> {
			cp.getAreaPane().getReadPane().getMapper().setNativeBridge(out);
			WebViews.addHyperlinkListener(cp.getAreaPane().getReadPane().getWebViewPane().getWebView(), new WebViewHyperlinkListener() {

				@Override
				public boolean hyperlinkUpdate(HyperlinkEvent event) {
					LinkOpenUtil.open(event.getURL().toString());
					return false;
				}
			}, javax.swing.event.HyperlinkEvent.EventType.ACTIVATED);
		});

		GroupMessageWriteNativeBridgeImpl write = new GroupMessageWriteNativeBridgeImpl(cp.getAreaPane().getWritePane());
		write.setAppContext(appContext);
		write.setGroupId(groupId);

		MessageWriteMapper writeMapper = cp.getAreaPane().getWritePane().getMapper();
		FxUtil.invoke(() -> {
			writeMapper.setNativeBridge(write);
		});

		ChatPaneComonFunction function = this.appContext.getObject(ChatPaneComonFunction.class);
		function.setToolIcon(cp);

		ChatPaneGroupFunction uf = this.appContext.getObject(ChatPaneGroupFunction.class);
		uf.setToolIcon(cp, data);
		uf.setChatSend(cp, data);
		uf.setFileSend(cp, data);

		String key = data.getId();
		GroupSendContentBox box = this.appContext.getObject(GroupSendContentBox.class);
		box.createSend(key);

		GroupMemberListFunction mlf = this.appContext.getObject(GroupMemberListFunction.class);
		mlf.putPane(cp, data);
		mlf.loadList(groupId);
	}

	@Override
	protected void initialize(HeadCloseItem item, Group data) {
		GroupChatItemFunction f = this.appContext.getObject(GroupChatItemFunction.class);
		f.setEvent(item, data);
	}

	@Override
	protected void setInfo(HeadCloseItem item, Group data) {
		GroupChatItemFunction f = this.appContext.getObject(GroupChatItemFunction.class);
		f.setInfo(item, data);
	}

	@Override
	protected Info checkRemove(Group data) {
		return new Info();
	}

	@Override
	protected void onRemove(Group data) {
		String key = data.getId();
		GroupSendContentBox box = this.appContext.getObject(GroupSendContentBox.class);
		box.clearSend(key);

		GroupMemberListFunction fun = this.appContext.getObject(GroupMemberListFunction.class);
		fun.remove(key);
	}

	@Override
	protected void onSelect(Group data) {
		String key = data.getId();
		GroupChatPromptService promptService = this.appContext.getObject(GroupChatPromptService.class);
		promptService.removePrompt(key);
	}
}
