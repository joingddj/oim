
package com.oimchat.client.platform.fx.module.group.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.app.fx.base.component.head.HeadSimpleItem;
import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.event.UserObservable;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberUserBox;
import com.oimchat.client.general.kernel.work.module.group.data.vo.GroupMemberUserData;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupMemberUserManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupMemberObservable;
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

public class GroupMemberListFunction extends AbstractMaterial {

	protected Map<String, ListRootPane> paneMap = new ConcurrentHashMap<>();
	protected Map<String, Map<String, HeadSimpleItem>> itemMap = new ConcurrentHashMap<>();

	public GroupMemberListFunction(AppContext appContext) {
		super(appContext);
		init();
	}

	private void init() {
		GroupMemberObservable o = this.appContext.getObject(GroupMemberObservable.class);
		o.putAddListener((m) -> {
			reload(m.getGroupId());
		});
		o.putUpdateListener((m) -> {
			reload(m.getGroupId());
		});
		o.putDeleteListener((m) -> {
			reload(m.getGroupId());
		});

		UserObservable uo = this.appContext.getObject(UserObservable.class);
		uo.addListener((u) -> {
			updateUser(u);
		});
	}

	public void reload(String groupId) {
		ListRootPane pane = paneMap.get(groupId);
		if (null != pane) {
			loadList(groupId);
		}
	}

	public void updateUser(User u) {
		String userId = u.getId();
		List<String> groupIds = new ArrayList<>();
		for (Map.Entry<String, Map<String, HeadSimpleItem>> e : itemMap.entrySet()) {
			String groupId = e.getKey();
			Map<String, HeadSimpleItem> map = e.getValue();
			if (map.containsKey(userId)) {
				groupIds.add(groupId);
			}
		}
		GroupMemberUserBox ubox = this.appContext.getObject(GroupMemberUserBox.class);
		GroupMemberBox box = this.appContext.getObject(GroupMemberBox.class);
		for (String groupId : groupIds) {
			GroupMember gm = box.get(groupId, userId);
			if (null != gm) {
				ubox.put(groupId, userId, u);
				getItem(groupId, gm, u);
			}
		}
	}

	public void putPane(ChatPane cp, Group data) {
		String groupId = data.getId();
		ListRootPane listPane = new ListRootPane();
		listPane.setPrefWidth(165);
		listPane.setStyle("-fx-background-color:rgba(235, 235, 235, 0.95)");

		paneMap.put(groupId, listPane);
		itemMap.put(groupId, new HashMap<>());

		FxUtil.invoke(() -> {
			cp.setRight(listPane);
		});
	}

	public void loadList(String groupId) {
		GroupMemberUserManager manager = this.appContext.getObject(GroupMemberUserManager.class);
		manager.asynLoadAllListByGroupId(groupId, (info, list) -> {
			setList(groupId, list);
		});
	}

	public void remove(String groupId) {
		paneMap.remove(groupId);
		itemMap.remove(groupId);

		GroupMemberUserBox gmubox = this.appContext.getObject(GroupMemberUserBox.class);
		GroupMemberBox gmbox = this.appContext.getObject(GroupMemberBox.class);
		gmubox.removeCategory(groupId);
		gmbox.removeCategory(groupId);
	}

	private void setList(String groupId, List<GroupMemberUserData> list) {
		ListRootPane pane = paneMap.get(groupId);
		if (null != pane) {
			FxUtil.invoke(() -> {
				pane.clearNode();
			});
			for (GroupMemberUserData d : list) {
				GroupMember member = d.getMember();
				User user = d.getUser();
				HeadSimpleItem item = getItem(groupId, member, user);
				if (null != item) {
					FxUtil.invoke(() -> {
						pane.addNode(item);
					});
				}
			}
		}
	}

	private HeadSimpleItem getItem(String groupId, GroupMember groupMember, User user) {
		GroupMemberUserHeadFunction guhf = appContext.getObject(GroupMemberUserHeadFunction.class);
		Map<String, HeadSimpleItem> map = itemMap.get(groupId);

		HeadSimpleItem head = null;
		if (null != map) {
			String userId = user.getId();
			head = map.get(userId);
			if (null == head) {
				head = new HeadSimpleItem();
				map.put(userId, head);
				guhf.setEvent(head);
			}
			guhf.setInfo(head, user, groupMember);
		}
		return head;
	}

	public HeadSimpleItem getItem(String groupId, String userId) {
		Map<String, HeadSimpleItem> map = itemMap.get(groupId);
		HeadSimpleItem head = null;
		if (null != map) {
			head = map.get(userId);
		}
		return head;
	}
}
