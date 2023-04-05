
package com.oimchat.client.platform.fx.module.list.wrap;

import java.util.List;
import java.util.Map;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.icon.IconPane;
import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.app.fx.view.ui.classics.module.list.ListPaneMapper;
import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.step.task.TaskNext;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.box.ContactRelationBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactCategoryManager;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationUserManager;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactCategoryObservable;
import com.oimchat.client.general.kernel.work.module.contact.observer.ContactRelationObservable;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.event.UserStatusObservable;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.core.manager.UserManager;
import com.oimchat.client.platform.fx.module.list.function.ContactCategoryNodeFunction;
import com.oimchat.client.platform.fx.module.list.function.ContactUserHeadFunction;
import com.oimchat.client.platform.fx.view.common.box.UserStatusImageBox;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;
import com.onlyxiahui.app.view.fx.component.tab.IconTab;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Description <br>
 * Date 2021-03-15 11:19:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactListViewWrap extends AbstractMaterial {

	final ListPaneMapper listPaneMapper = new ListPaneMapper();
	final IconTab tab;

	public ContactListViewWrap(AppContext appContext) {
		super(appContext);
		FontIconText icon = new FontAwesomeSolidIconText();
		icon.setFontIcon("\uf007");
		icon.setIconFontSize(22.2D);
		tab = new IconTab(icon);
		initConfig();
		initEvent();
	}

	private void initConfig() {
		ContactCategoryObservable ccl = this.appContext.getObject(ContactCategoryObservable.class);

		ccl.putAddListener((data) -> {
			addOrUpdateCategory(data);
		});

		ccl.putUpdateListener((data) -> {
			addOrUpdateCategory(data);
		});

		ccl.putDeleteListener((key) -> {
			deleteCategory(key);
		});

		ContactRelationObservable crl = this.appContext.getObject(ContactRelationObservable.class);
		crl.putAddListener((data) -> {
			// TODO 待解决nodeKey空问题
			String nodeKey = data.getCategoryId();
			addOrUpdateItem(data);
			updateCategoryItemSize(nodeKey);
		});

		crl.putUpdateListener((data) -> {
			addOrUpdateItem(data);
		});

		crl.putDeleteListener((data) -> {
			String itemKey = data.getContactUserId();
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

		UserStatusObservable ul = this.appContext.getObject(UserStatusObservable.class);
		ul.addListener((id, status) -> {
			updateStatus(id, status);
		});
	}

	private void initEvent() {
		listPaneMapper.setRootOnContextMenuRequested(e -> {
			e.consume();
			Object o = e.getSource();
			if (o instanceof Node) {
				Node n = (Node) o;
				ContactListPaneMenuWrap menu = appContext.getObject(ContactListPaneMenuWrap.class);
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
			loadContactList();
			n.next();
		});
		tn.next();
	}

	public void addOrUpdateCategory(ContactCategory data) {
		String key = data.getId();
		String name = data.getName();
		boolean has = null != listPaneMapper.getNode(key);
		ListNodePane node = listPaneMapper.addOrUpdateNode(key, name);
		listPaneMapper.putNodeData(key, ContactCategory.class, data);
		if (!has) {
			ContactCategoryNodeFunction f = this.appContext.getObject(ContactCategoryNodeFunction.class);
			f.setEvent(node);
		}
	}

	public void updateCategoryItemSize(String key) {
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		ContactRelationUserManager crum = this.appContext.getObject(ContactRelationUserManager.class);
		int onlineCount = crum.getOnlineCountByCategoryId(key);
		int allCount = box.getSizeByCategoryKey(key);// listPaneMapper.getNodeItemSize(key);
		String sizeText = "[" + onlineCount + "/" + allCount + "]";
		listPaneMapper.updateNodeNumberText(key, sizeText);
	}

	public void updateCategoryItemSize() {
		Map<String, ListNodePane> map = listPaneMapper.getAllNodeMap();
		for (Map.Entry<String, ListNodePane> e : map.entrySet()) {
			updateCategoryItemSize(e.getKey());
		}
	}

	public void deleteCategory(String key) {
		listPaneMapper.removeNode(key);
	}

	public void addOrUpdateItem(ContactRelation data) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		String userId = data.getContactUserId();
		uh.getById(userId, (info, user) -> {
			addOrUpdateItem(data, user);
		});
	}

	public void addOrUpdateItem(ContactRelation data, User user) {

		if (null != data && null != user) {
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);

			String userId = data.getContactUserId();
			String nodeKey = data.getCategoryId();

			String itemKey = userId;
			String remark = data.getRemark();

			boolean hasRemark = StringUtil.isNotBlank(remark);
			String showRemark = "";
			String showName = "";

			if (hasRemark) {
				String name = UserInfoUtil.getShowName(user);
				showRemark = remark;
				showName = "(" + name + ")";
			} else {
				String name = UserInfoUtil.getShowName(user);
				showRemark = name;
				showName = "";
			}

			String status = user.getStatus();
			boolean gray = UserInfoUtil.isOffline(status);
			String signature = user.getSignature();

			boolean has = null != listPaneMapper.getItem(nodeKey, itemKey);
			HeadItem item = listPaneMapper.addOrUpdateItem(nodeKey,
					itemKey,
					showRemark,
					showName,
					"",
					signature, null);
			if (!has) {
				ContactUserHeadFunction f = this.appContext.getObject(ContactUserHeadFunction.class);
				f.setEvent(item);
			}

			listPaneMapper.updateItemGray(itemKey, gray);

			Image iconImage = UserStatusImageBox.getContactStatusImageIcon(status);

			IconPane iconButton = item.getBusinessIcon("statusLabel");
			if (null == iconButton) {// 状态图标显示组件
				iconButton = new IconPane(iconImage);
				listPaneMapper.updateItemBusinessIcon(itemKey, "statusLabel", iconButton);
			}
			
			iconButton.setNormalImage(iconImage);

			uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
				listPaneMapper.updateItem(itemKey, img);
			});
		}
	}

	public void updateItem(String itemKey,
			boolean red,
			String redText) {
		listPaneMapper.updateItem(itemKey, red, redText);
	}

	public void deleteItem(String nodeKey, String itemKey) {
		listPaneMapper.removeItem(nodeKey, itemKey);
	}

	public void deleteItem(String itemKey) {
		listPaneMapper.removeItemByKey(itemKey);
	}

	private void updateStatus(String id, String status) {
		List<HeadItem> items = listPaneMapper.getItemsByItemKey(id);
		if (null != items) {
			for (HeadItem item : items) {
				if (null != item) {
					boolean gray = UserInfoUtil.isOffline(status);
					FxUtil.invoke(() -> {
						item.setGray(gray);

						Image iconImage = UserStatusImageBox.getContactStatusImageIcon(status);

						IconPane iconButton = item.getBusinessIcon("statusLabel");
						if (null == iconButton) {// 状态图标显示组件
							iconButton = new IconPane(iconImage);
							item.addBusinessIcon("statusLabel", iconButton);
						}
						iconButton.setNormalImage(iconImage);
					});
				}
			}
		}

		List<String> nodeKeys = listPaneMapper.getNodeKeysByItemKey(id);
		if (null != nodeKeys) {
			for (String nodeKey : nodeKeys) {
				updateCategoryItemSize(nodeKey);
			}
		}
	}

	public void loadContactList(InfoBack back) {
		UserManager uh = this.appContext.getObject(UserManager.class);
		ContactRelationManager crh = this.appContext.getObject(ContactRelationManager.class);
		crh.asynLoadAllList((info, list) -> {
			if (info.isSuccess() && null != list) {
				uh.getOrLoadMapByIds(list, (t) -> {
					return t.getContactUserId();
				}, (ui, map) -> {
					for (ContactRelation data : list) {
						String userId = data.getContactUserId();
						User user = map.get(userId);
						addOrUpdateItem(data, user);
					}
					updateCategoryItemSize();
					System.out.println(JsonUtil.toJson(map));
				});
			}
			back.back(info);
		});
	}

	public void loadContactList() {
		loadContactList(info -> {
		});
	}

	public void loadCategoryList(InfoBack back) {
		ContactCategoryManager ccs = this.appContext.getObject(ContactCategoryManager.class);
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
		loadCategoryList(info -> {
		});
	}
}
