
package com.oimchat.app.fx.view.ui.classics.module.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.app.fx.base.component.choose.ChooseGroup;
import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;

/**
 * Description <br>
 * Date 2021-03-13 19:05:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ListPaneMapper {

	private final ListRootPane rootPane = new ListRootPane();

	// <itemKey,<nodeKey,item>>
	private final Map<String, Map<String, HeadItem>> allItemNodeMap = new ConcurrentHashMap<>();

	// <nodeKey,<itemKey,item>>
	private final Map<String, Map<String, HeadItem>> allNodeItemMap = new ConcurrentHashMap<>();

	private final Map<String, ListNodePane> allNodeMap = new ConcurrentHashMap<>();

	private final ChooseGroup chooseGroup = new ChooseGroup();

	public ListRootPane getRootPane() {
		return rootPane;
	}

	private void handleNodeItem(String key, ListNodePane node) {
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(key);
		if (null != nodeItemMap) {
			for (Map.Entry<String, HeadItem> e : nodeItemMap.entrySet()) {
				// String itemKey = e.getKey();
				HeadItem item = e.getValue();

				if (!node.getBox().getChildren().contains(item)) {
					FxUtil.invoke(() -> {
						node.addItem(item);
					});
				}
			}
		}
	}

	private void addNode(String key, ListNodePane node) {
		ListNodePane o = allNodeMap.get(key);
		if (null != o) {
			FxUtil.invoke(() -> {
				rootPane.removeNode(node);
			});
		}
		allNodeMap.put(key, node);
		FxUtil.invoke(() -> {
			rootPane.addNode(node);
		});
		handleNodeItem(key, node);
	}

	public ListNodePane addOrUpdateNode(String key, String text) {
		ListNodePane n = allNodeMap.get(key);
		if (null == n) {
			n = new ListNodePane();
			n.setUserData(key);
			addNode(key, n);
		}
		n.setUserData(key);
		update(n, text);
		return n;
	}

	private void update(ListNodePane n, String text) {
		FxUtil.invoke(() -> {
			if (null != n) {
				n.setText(text);
			}
		});
	}

	public void updateNode(String key, String text, String numberText) {
		ListNodePane n = allNodeMap.get(key);
		if (null != n) {
			FxUtil.invoke(() -> {
				n.setText(text);
				n.setNumberText(numberText);
			});
		}
	}

	public <T> void putNodeData(String nodeKey, Object key, T data) {
		ListNodePane n = allNodeMap.get(nodeKey);
		if (null != n) {
			n.addAttribute(key, data);
		}
	}

	public <T> T getNodeData(String nodeKey, Object key) {
		T data = null;
		ListNodePane n = allNodeMap.get(nodeKey);
		if (null != n) {
			data = n.getAttribute(key);
		}
		return data;
	}

	public void updateNodeNumberText(String key, String numberText) {
		ListNodePane n = allNodeMap.get(key);
		if (null != n) {
			FxUtil.invoke(() -> {
				n.setNumberText(numberText);
			});
		}
	}

	public void updateNodeText(String key, String text) {
		ListNodePane n = allNodeMap.get(key);
		if (null != n) {
			FxUtil.invoke(() -> {
				n.setText(text);
			});
		}
	}

	public int getNodeItemSize(String key) {
		int size = 0;
		ListNodePane n = allNodeMap.get(key);
		if (null != n) {
			size = n.itemSize();
		}
		return size;
	}

	public List<ListNodePane> getNodeList() {
		List<ListNodePane> list = new ArrayList<>(allNodeMap.values());
		return list;
	}

	public Map<String, ListNodePane> getAllNodeMap() {
		return allNodeMap;
	}

	public void removeNode(String key) {
		ListNodePane n = allNodeMap.remove(key);
		if (null != n) {
			FxUtil.invoke(() -> {
				n.clearItem();
			});
		}
	}

	public ListNodePane getNode(String key) {
		ListNodePane n = allNodeMap.get(key);
		return n;
	}

	public void setRootOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
		FxUtil.invoke(() -> {
			rootPane.setOnContextMenuRequested(value);
		});
	}

	private void handleNodeItem(String nodeKey, HeadItem item) {
		ListNodePane node = allNodeMap.get(nodeKey);
		if (null != node) {
			if (!node.getBox().getChildren().contains(item)) {
				FxUtil.invoke(() -> {
					node.addItem(item);
				});
			}
		}
	}

	private List<HeadItem> getItems(String itemKey) {
		List<HeadItem> items = new ArrayList<>();
		Map<String, HeadItem> itemNodeMap = allItemNodeMap.get(itemKey);
		if (null != itemNodeMap) {
			items.addAll(itemNodeMap.values());
		}
		return items;
	}

	public HeadItem getItem(
			String nodeKey,
			String itemKey) {
		HeadItem back = null;
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
		if (null != nodeItemMap) {
			back = nodeItemMap.get(itemKey);
		}
		return back;
	}

	public List<HeadItem> getItemsByItemKey(String itemKey) {
		List<HeadItem> list = new ArrayList<>();
		Map<String, HeadItem> nodeItemMap = allItemNodeMap.get(itemKey);
		if (null != nodeItemMap) {
			list.addAll(nodeItemMap.values());
		}
		return list;
	}

	public HeadItem addOrUpdateItem(
			String nodeKey,
			String itemKey,
			String remark,
			String nickname,
			String status,
			String showText,
			Map<String, Node> businessIconMap) {
		HeadItem item = getOrCreateHeadItem(nodeKey, itemKey);
		FxUtil.invoke(() -> {
			item.setRemark(remark);
			item.setNickname(nickname);
			item.setStatus(status);
			item.setShowText(showText);
			if (null != businessIconMap) {
				for (Map.Entry<String, Node> e : businessIconMap.entrySet()) {
					item.addBusinessIcon(e.getKey(), e.getValue());
				}
			}
//			item.setOnContextMenuRequested(e -> {
//				if (null != itemContextMenuEvent) {
//					itemContextMenuEvent.handle(e);
//				}
//			});
		});
		item.setChooseGroup(chooseGroup);
		handleNodeItem(nodeKey, item);
		return item;
	}

	private HeadItem getOrCreateHeadItem(String nodeKey, String itemKey) {
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
		if (null == nodeItemMap) {
			nodeItemMap = new HashMap<>();
			allNodeItemMap.put(nodeKey, nodeItemMap);
		}

		Map<String, HeadItem> itemNodeMap = allItemNodeMap.get(itemKey);
		if (null == itemNodeMap) {
			itemNodeMap = new HashMap<>();
			allItemNodeMap.put(itemKey, itemNodeMap);
		}

		HeadItem item = nodeItemMap.get(itemKey);
		if (null == item) {
			item = new HeadItem();
			item.setKey(itemKey);

			nodeItemMap.put(itemKey, item);

			itemNodeMap.put(nodeKey, item);
		}
		return item;
	}

	public void updateItem(
			String itemKey,
			Image image,
			String remark,
			String nickname,
			String status,
			String showText,
			Map<String, Node> businessIconMap) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setHeadImage(image);
				item.setRemark(remark);
				item.setNickname(nickname);
				item.setStatus(status);
				item.setShowText(showText);
				if (null != businessIconMap) {
					for (Map.Entry<String, Node> e : businessIconMap.entrySet()) {
						item.addBusinessIcon(e.getKey(), e.getValue());
					}
				}
			});
		}
	}

	public void updateItem(
			String itemKey,
			String remark,
			String nickname,
			String status,
			String showText) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setRemark(remark);
				item.setNickname(nickname);
				item.setStatus(status);
				item.setShowText(showText);
			});
		}
	}

	public void updateItem(
			String itemKey,
			Image image) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setHeadImage(image);
			});
		}
	}

	public void updateItemGray(
			String itemKey,
			boolean gray) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setGray(gray);
			});
		}
	}

	public void updateItem(
			String itemKey,
			boolean red,
			String redText) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setRed(red);
				item.setRedText(redText);
			});
		}
	}

	public void updateItem(
			String itemKey,
			Map<String, Node> businessIconMap) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			if (null != businessIconMap) {
				for (Map.Entry<String, Node> e : businessIconMap.entrySet()) {
					FxUtil.invoke(() -> {
						item.addBusinessIcon(e.getKey(), e.getValue());
					});
				}
			}
		}
	}

	public void updateItemShowText(
			String itemKey,
			String showText) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.setShowText(showText);
			});
		}
	}

	public void updateItemBusinessIcon(String itemKey, String key, Node icon) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.addBusinessIcon(key, icon);
			});
		}
	}

	public void clearItemBusinessIcon(String itemKey) {
		List<HeadItem> items = getItems(itemKey);
		for (HeadItem item : items) {
			FxUtil.invoke(() -> {
				item.getBusinessBox().getChildren().clear();
			});
		}
	}

	public void clearAll() {
		allNodeItemMap.clear();
		allItemNodeMap.clear();
		allNodeMap.clear();
		FxUtil.invoke(() -> {
			rootPane.clearNode();
		});
	}

	public HeadItem removeItem(
			String nodeKey,
			String itemKey) {

		HeadItem item = null;
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
		if (null != nodeItemMap) {
			item = nodeItemMap.remove(itemKey);

			if (nodeItemMap.isEmpty()) {
				allNodeItemMap.remove(nodeKey);
			}
		}

		Map<String, HeadItem> itemNodeMap = allItemNodeMap.get(itemKey);
		if (null != itemNodeMap) {
			itemNodeMap.remove(nodeKey);

			if (itemNodeMap.isEmpty()) {
				allItemNodeMap.remove(itemKey);
			}
		}

		ListNodePane node = allNodeMap.get(nodeKey);
		removeItem(node, item);
		return item;
	}

	public List<String> getNodeKeysByItemKey(String itemKey) {
		List<String> items = new ArrayList<>();
		Map<String, HeadItem> itemNodeMap = allItemNodeMap.get(itemKey);
		if (null != itemNodeMap) {
			items.addAll(itemNodeMap.keySet());
		}
		return items;
	}

	public List<HeadItem> removeItemByKey(
			String itemKey) {
		List<HeadItem> items = new ArrayList<>();
		Map<String, HeadItem> itemNodeMap = allItemNodeMap.remove(itemKey);
		if (null != itemNodeMap) {
			items.addAll(itemNodeMap.values());

			for (Map.Entry<String, HeadItem> e : itemNodeMap.entrySet()) {
				String nodeKey = e.getKey();
				HeadItem item = e.getValue();
				Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
				if (null != nodeItemMap) {
					nodeItemMap.remove(itemKey);

					if (nodeItemMap.isEmpty()) {
						allNodeItemMap.remove(nodeKey);
					}
				}
				ListNodePane node = allNodeMap.get(nodeKey);
				removeItem(node, item);
			}
		}
		return items;
	}

	public <T> void putItemData(
			String nodeKey,
			String itemKey,
			Object key,
			T data) {
		HeadItem item = null;
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
		if (null != nodeItemMap) {
			item = nodeItemMap.get(itemKey);
		}
		if (null != item) {
			item.addAttribute(key, data);
		}
	}

	public <T> T gettItemData(
			String nodeKey,
			String itemKey,
			Object key) {
		T data = null;
		Map<String, HeadItem> nodeItemMap = allNodeItemMap.get(nodeKey);
		HeadItem item = null;
		if (null != nodeItemMap) {
			item = nodeItemMap.get(itemKey);
		}
		if (null != item) {
			data = item.getAttribute(key);
		}
		return data;
	}

	private void removeItem(ListNodePane node, HeadItem item) {
		if (null != node && null != item) {
			FxUtil.invoke(() -> {
				node.removeItem(item);
			});
		}
	}
}
