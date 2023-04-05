
package com.oimchat.client.platform.fx.module.list.wrap;

import java.util.List;
import java.util.Map;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.app.fx.view.ui.classics.module.list.ListPaneMapper;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.step.task.TaskNext;
import com.oimchat.client.general.kernel.work.module.common.util.GroupInfoUtil;
import com.oimchat.client.general.kernel.work.module.group.box.GroupRelationBox;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupRelation;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupCategoryManager;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupManager;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupRelationManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupCategoryObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupRelationObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupUpdateObservable;
import com.oimchat.client.platform.fx.module.list.function.GroupCategoryNodeFunction;
import com.oimchat.client.platform.fx.module.list.function.GroupJoinedHeadFunction;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;
import com.onlyxiahui.app.view.fx.component.tab.IconTab;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.scene.Node;

/**
 * Description <br>
 * Date 2021-03-15 11:19:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupListViewWrap extends AbstractMaterial {

	final ListPaneMapper listPaneMapper = new ListPaneMapper();
	final IconTab tab;

	public GroupListViewWrap(AppContext appContext) {
		super(appContext);
		FontIconText icon = new FontAwesomeSolidIconText();
		icon.setFontIcon("\uf500");
		icon.setIconFontSize(22.2D);
		tab = new IconTab(icon);
		initConfig();
		initEvent();
	}

	private void initConfig() {
		GroupCategoryObservable ccl = this.appContext.getObject(GroupCategoryObservable.class);
		ccl.putAddListener((data) -> {
			addOrUpdateCategory(data);
		});

		ccl.putUpdateListener((data) -> {
			addOrUpdateCategory(data);
		});

		ccl.putDeleteListener((key) -> {
			deleteCategory(key);
		});

		GroupRelationObservable crl = this.appContext.getObject(GroupRelationObservable.class);

		crl.putAddListener((data) -> {
			String nodeKey = data.getCategoryId();
			addOrUpdateItem(data);
			updateCategoryItemSize(nodeKey);
		});

		crl.putUpdateListener((data) -> {
			addOrUpdateItem(data);
		});

		crl.putDeleteListener((data) -> {
			String itemKey = data.getGroupId();
			String nodeKey = data.getCategoryId();
			if (StringUtil.isBlank(nodeKey)) {
				List<String> list = listPaneMapper.getNodeKeysByItemKey(itemKey);
				for (String nk : list) {
					deleteItem(nk, itemKey);
					updateCategoryItemSize(nodeKey);
				}
			} else {
				deleteItem(nodeKey, itemKey);
				updateCategoryItemSize(nodeKey);
			}
		});

		GroupUpdateObservable guo = this.appContext.getObject(GroupUpdateObservable.class);
		guo.addListener((g) -> {

		});
	}

	private void initEvent() {
		listPaneMapper.setRootOnContextMenuRequested(e -> {
			e.consume();
			Object o = e.getSource();
			if (o instanceof Node) {
				Node n = (Node) o;
				GroupListPaneMenuWrap menu = appContext.getObject(GroupListPaneMenuWrap.class);
				menu.show(n, e.getScreenX(), e.getScreenY());
			}
		});
	}

	public ListPaneMapper getListPaneMapper() {
		return listPaneMapper;
	}

	public IconTab getTab() {
		return tab;
	}

	public void initializeData() {
		listPaneMapper.clearAll();
		TaskNext tn = new TaskNext();
		tn.set((n) -> {
			loadCategoryList((info) -> {
				n.next();
			});
		}).set((n) -> {
			loadGroupList();
			n.next();
		});
		tn.next();
	}

	public  void addOrUpdateCategory(GroupCategory data) {
		String key = data.getId();
		String name = data.getName();
		boolean has = null != listPaneMapper.getNode(key);
		ListNodePane node = listPaneMapper.addOrUpdateNode(key, name);
		listPaneMapper.putNodeData(key, GroupCategory.class, data);
		if (!has) {
			GroupCategoryNodeFunction function = this.appContext.getObject(GroupCategoryNodeFunction.class);
			function.setEvent(node);
		}
	}

	public  void updateCategoryItemSize(String key) {
		GroupRelationBox box = this.appContext.getObject(GroupRelationBox.class);
		int size = box.getSizeByCategoryKey(key);// listPaneMapper.getNodeItemSize(key);
		String sizeText = "[" + size + "]";
		listPaneMapper.updateNodeNumberText(key, sizeText);
	}

	public  void updateCategoryItemSize() {
		Map<String, ListNodePane> map = listPaneMapper.getAllNodeMap();
		for (Map.Entry<String, ListNodePane> e : map.entrySet()) {
			updateCategoryItemSize(e.getKey());
		}
	}

	public  void deleteCategory(String key) {
		listPaneMapper.removeNode(key);
	}

	public  void addOrUpdateItem(GroupRelation data) {
		GroupHandler uh = this.appContext.getObject(GroupHandler.class);
		String groupId = data.getGroupId();
		uh.getById(groupId, (info, group) -> {
			addOrUpdateItem(data, group);
		});
	}

	public  void addOrUpdateItem(GroupRelation data, Group group) {

		if (null != data && null != group) {
			GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);

			String groupId = data.getGroupId();
			String nodeKey = data.getCategoryId();

			String itemKey = groupId;
			String remark = data.getRemark();
			String showName = remark;

			if (StringUtil.isBlank(showName)) {
				showName = GroupInfoUtil.getShowName(group);
			}

			String signature = group.getIntroduce();

			boolean has = null != listPaneMapper.getItem(nodeKey, itemKey);
			HeadItem item = listPaneMapper.addOrUpdateItem(nodeKey,
					itemKey,
					showName,
					"",
					"",
					signature, null);

			uhim.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
				listPaneMapper.updateItem(itemKey, img);
			});

			GroupJoinedHeadFunction function = this.appContext.getObject(GroupJoinedHeadFunction.class);
			if (!has) {
				function.setEvent(item, group);
				// function.setInfo(item, group);
			}
		}
	}

	public  void updateItem(String itemKey,
			boolean red,
			String redText) {
		listPaneMapper.updateItem(itemKey, red, redText);
	}

	public  void deleteItem(String nodeKey, String itemKey) {
		listPaneMapper.removeItem(nodeKey, itemKey);
	}

	public void loadGroupList(InfoBack back) {
		GroupManager uh = this.appContext.getObject(GroupManager.class);
		GroupRelationManager crh = this.appContext.getObject(GroupRelationManager.class);
		crh.asynLoadAllList((info, list) -> {
			if (info.isSuccess() && null != list) {
				uh.getOrLoadMapByIds(list, (t) -> {
					return t.getGroupId();
				}, (ui, map) -> {
					for (GroupRelation data : list) {
						String groupId = data.getGroupId();
						Group group = map.get(groupId);
						addOrUpdateItem(data, group);
					}
					updateCategoryItemSize();
				});
			}
			back.back(info);
		});
	}

	public void loadGroupList() {
		loadGroupList((info) -> {
		});
	}

	public void loadCategoryList(InfoBack back) {
		GroupCategoryManager ccs = this.appContext.getObject(GroupCategoryManager.class);
		ccs.asynLoadAllList((info, list) -> {
			if (null != list) {
				list.forEach((data) -> {
					addOrUpdateCategory(data);
				});
			}
			back.back(info);
		});
	}

	public void loadCategoryList() {
		loadCategoryList((info) -> {
		});
	}
}
