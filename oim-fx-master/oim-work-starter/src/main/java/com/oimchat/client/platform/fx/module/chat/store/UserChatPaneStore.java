
package com.oimchat.client.platform.fx.module.chat.store;

import javax.swing.event.HyperlinkEvent;

import org.codefx.libfx.control.webview.WebViewHyperlinkListener;
import org.codefx.libfx.control.webview.WebViews;

import com.oimchat.app.awt.desktop.util.LinkOpenUtil;
import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.im.box.UserSendContentBox;
import com.oimchat.client.general.kernel.work.module.im.controller.UserChatDataController;
import com.oimchat.client.platform.fx.module.chat.function.ChatPaneComonFunction;
import com.oimchat.client.platform.fx.module.chat.function.ChatPaneUserFunction;
import com.oimchat.client.platform.fx.module.chat.function.UserChatItemFunction;
import com.oimchat.client.platform.fx.module.chat.store.impl.UserMessageReadNativeBridgeImpl;
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

public class UserChatPaneStore extends BaseChatPaneStore<User> {

	public UserChatPaneStore(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String type() {
		return "user_chat";
	}

	@Override
	public String getKey(User data) {
		return getKey(data.getId());
	}

	@Override
	protected void setInfo(ChatPane cp, User data) {
		String userId = data.getId();
		String name = UserInfoUtil.getShowName(data);
		String signature = data.getSignature();
		FxUtil.invoke(() -> {
			cp.setText(signature);
			cp.setName(name);
		});
		ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
		cch.getByContactUserId(userId, (info, r) -> {
			if (null != r && StringUtil.isNotBlank(r.getRemark())) {
				FxUtil.invoke(() -> {
					cp.setName(r.getRemark());
				});
			}
		});
	}

	@Override
	protected void initialize(ChatPane cp, User data) {
		UserMessageReadNativeBridgeImpl out = this.appContext.createObject(UserMessageReadNativeBridgeImpl.class);
		out.setUserId(data.getId());
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

		ChatPaneComonFunction function = this.appContext.getObject(ChatPaneComonFunction.class);
		function.setToolIcon(cp);

		ChatPaneUserFunction uf = this.appContext.getObject(ChatPaneUserFunction.class);
		uf.setToolIcon(cp, data);
		uf.setChatSend(cp, data);
		uf.setFileSend(cp, data);

		String key = data.getId();
		UserSendContentBox box = this.appContext.getObject(UserSendContentBox.class);
		box.createSend(key);
	}

	@Override
	protected void initialize(HeadCloseItem item, User data) {
		UserChatItemFunction f = this.appContext.getObject(UserChatItemFunction.class);
		f.setEvent(item, data);
	}

	@Override
	protected void setInfo(HeadCloseItem item, User data) {
		UserChatItemFunction f = this.appContext.getObject(UserChatItemFunction.class);
		f.setInfo(item, data);
	}

	@Override
	protected Info checkRemove(User data) {
		return new Info();
	}

	@Override
	protected void onRemove(User data) {
		String key = data.getId();
		UserSendContentBox box = this.appContext.getObject(UserSendContentBox.class);
		box.clearSend(key);
	}

	@Override
	protected void onSelect(User data) {
		String key = data.getId();
		UserChatDataController cs = this.appContext.getObject(UserChatDataController.class);
		cs.readByUser(key);
	}
}
