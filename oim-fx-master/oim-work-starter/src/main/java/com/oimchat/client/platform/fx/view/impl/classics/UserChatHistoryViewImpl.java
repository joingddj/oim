package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.HyperlinkEvent;

import org.codefx.libfx.control.webview.WebViewHyperlinkListener;
import org.codefx.libfx.control.webview.WebViews;

import com.oimchat.app.awt.desktop.util.LinkOpenUtil;
import com.oimchat.app.fx.base.component.loading.WaitingPane;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatHistoryStage;
import com.oimchat.client.basic.common.data.im.message.MessageContentWrap;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.UserSimple;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.im.data.dto.UserChatData;
import com.oimchat.client.general.kernel.work.module.im.data.query.UserChatQuery;
import com.oimchat.client.general.kernel.work.module.im.handler.MessageContentWrapHandler;
import com.oimchat.client.general.kernel.work.module.im.manager.UserChatDataManager;
import com.oimchat.client.general.kernel.work.module.im.util.ContentTimeUtil;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.view.UserChatHistoryView;
import com.oimchat.client.platform.fx.module.chat.store.impl.UserMessageHistoryReadNativeBridgeImpl;
import com.oimchat.client.platform.fx.module.chat.store.impl.UserMessageReadNativeBridgeImpl;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.lib.util.json.JsonUtil;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Callback;

/**
 * @author: XiaHui
 * @date: 2017年4月11日 下午12:07:41
 */
public class UserChatHistoryViewImpl extends BaseStageView<ChatHistoryStage> implements UserChatHistoryView {

	private User user;
	private ContactRelation relation;
	private String userAvatar;
	private String ownerAvatar;

	private String userId;

	private UserMessageReadNativeBridgeImpl readNativeBridge;

	public UserChatHistoryViewImpl(AppContext appContext) {
		super(appContext);
		readNativeBridge = this.appContext.createObject(UserMessageHistoryReadNativeBridgeImpl.class);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	protected void initEvent() {
		ChatHistoryStage stage = this.getStage();
		stage.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer index) {
				int pageNumber = (index + 1);
				load(pageNumber);
				showWaiting(true, WaitingPane.show_waiting);
				return new Label("第" + pageNumber + "页");
			}
		});
	}

	public void initPage() {
		ChatHistoryStage stage = this.getStage();
		stage.setPage(0, 1);
	}

	private void setList(Page page, List<UserChatData> list) {
		ChatHistoryStage stage = this.getStage();
		int totalPage = page.getTotalPage();
		stage.clear();
		stage.setTotalPage((totalPage <= 0 ? 1 : totalPage));

		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User ownerUser = pb.getUser();
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
		for (UserChatData cd : list) {

			int status = 1;

			UserSimple sendSimpleUser = cd.getSendUser();
			UserSimple receiveSimpleUser = cd.getReceiveUser();

			String sendUserId = sendSimpleUser.getId();
			// String receiveUserId = sendSimpleUser.getId();

			Content content = cd.getContent();

			boolean isOwn = ownerUserId.equals(sendUserId);

			UserSimple chatSimpleUser = isOwn ? sendSimpleUser : receiveSimpleUser;
			User chatUser = isOwn ? ownerUser : user;

			boolean timeVisible = true;
			String timeText = ContentTimeUtil.getChatShowTime(content.getTimestamp());

			String name = UserInfoUtil.getShowName(chatUser);
			wh.neaten(content, (info, c) -> {
				MessageContentWrap contentWrap = wh.wrap(chatSimpleUser, c, name, status, timeVisible, timeText, isOwn, true);
				content(contentWrap);
			});
		}
	}

	private void content(MessageContentWrap contentWrap) {
		if (contentWrap.isOwn()) {
			contentWrap.setAvatar(ownerAvatar);
			contentWrap.getUser().setAvatar(ownerAvatar);
		} else {
			contentWrap.setAvatar(userAvatar);
			contentWrap.getUser().setAvatar(userAvatar);
		}
		String json = JsonUtil.toJson(contentWrap);
		this.getStage().insertBefore(json);
	}

	private void load(int pageNumber) {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		String sendUserId = (userId == null || "".equals(userId)) ? "00000" : userId;
		String receiveUserId = pb.getOwnerUserId();

		UserChatQuery query = new UserChatQuery();
		query.setSendUserId(sendUserId);
		query.setReceiveUserId(receiveUserId);

		Page page = new Page();
		page.setSize(30);
		page.setNumber(pageNumber);
		UserChatDataManager cs = appContext.getObject(UserChatDataManager.class);
		cs.list(query, page, (info, pr) -> {
			if (info.isSuccess()) {
				showWaiting(false, WaitingPane.show_waiting);
			} else {
				showWaiting(true, WaitingPane.show_result);
			}
			List<UserChatData> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
			Page p = (null != pr && null != pr.getPage()) ? pr.getPage() : new Page();
			setList(p, list);
		});
	}

	public void showWaiting(boolean show, String key) {
		FxUtil.invoke(() -> {
			this.getStage().showWaiting(show, key);
		});
	}

	@Override
	protected ChatHistoryStage createStage() {
		return new ChatHistoryStage();
	}

	@Override
	protected void initStage(ChatHistoryStage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initComponent() {
		readNativeBridge.setReadMapper(this.getStage().getReadPane().getMapper());
		FxUtil.invoke(() -> {
			this.getStage().getReadPane().getMapper().setNativeBridge(readNativeBridge);
			WebViews.addHyperlinkListener(this.getStage().getReadPane().getWebViewPane().getWebView(), new WebViewHyperlinkListener() {

				@Override
				public boolean hyperlinkUpdate(HyperlinkEvent event) {
					LinkOpenUtil.open(event.getURL().toString());
					return true;
				}
			}, javax.swing.event.HyperlinkEvent.EventType.ACTIVATED);
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContactRelation getRelation() {
		return relation;
	}

	public void setRelation(ContactRelation relation) {
		this.relation = relation;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
		readNativeBridge.setUserId(userId);
		clearData();
		laodData();
		initPage();
	}

	private void clearData() {
		user = null;
		relation = null;
		userAvatar = null;
		ownerAvatar = null;
	}

	private void laodData() {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, user) -> {
			setUser(user);
			loadAvatar(user);
		});

		ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
		cch.getByContactUserId(userId, (info, relation) -> {
			setRelation(relation);
		});
	}

	private void loadAvatar(User user) {
		UserHeadImageHandler hi = this.appContext.getObject(UserHeadImageHandler.class);
		if (null != user) {
			hi.loadAvatarUrl(user.getHead(), user.getAvatar(), (url) -> {
				if (null != url) {
					userAvatar = url.toExternalForm();
				}
			});
		}

		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User ownerUser = pb.getUser();
		hi.loadAvatarUrl(ownerUser.getHead(), ownerUser.getAvatar(), (url) -> {
			if (null != url) {
				ownerAvatar = url.toExternalForm();
			}
		});
	}
}
