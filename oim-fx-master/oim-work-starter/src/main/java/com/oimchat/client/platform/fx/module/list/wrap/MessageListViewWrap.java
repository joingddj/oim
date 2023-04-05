
package com.oimchat.client.platform.fx.module.list.wrap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.app.fx.base.component.choose.ChooseGroup;
import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.client.general.common.inform.box.AllUnreadBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;
import com.onlyxiahui.app.view.fx.component.tab.IconTab;

/**
 * Description <br>
 * Date 2021-03-15 11:19:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageListViewWrap extends AbstractMaterial {

	private final Map<String, HeadItem> itemMap = new ConcurrentHashMap<>();
	private final ListRootPane rootPane = new ListRootPane();
	private final ChooseGroup chooseGroup = new ChooseGroup();
	final IconTab tab;

	public MessageListViewWrap(AppContext appContext) {
		super(appContext);
		FontIconText icon = new FontAwesomeSolidIconText();
		icon.setFontIcon("\uf4ad");
		icon.setIconFontSize(22.2D);
		tab = new IconTab(icon);
		initConfig();
	}

	private void initConfig() {
		AllUnreadBox ub = this.appContext.getObject(AllUnreadBox.class);
		ub.add((count) -> {
			// setUnread(count);
		});
	}

	public ListRootPane getRootPane() {
		return rootPane;
	}

	public IconTab getTab() {
		return tab;
	}

	public void setUnread(long count) {
		boolean red = count > 0;
		String countText = ((count > 99) ? 99 : count) + "";
		FxUtil.invoke(() -> {
			tab.setRed(red);
			tab.setRedText(countText);
		});
	}

	public void addItem(String key, HeadItem head) {
		HeadItem item = itemMap.get(key);
		if (null != item) {
			FxUtil.invoke(() -> {
				rootPane.removeNode(item);
			});
		}
		itemMap.put(key, head);
		head.setChooseGroup(chooseGroup);
		FxUtil.invoke(() -> {
			rootPane.addNode(0, head);
		});
	}

	public HeadItem getItem(String key) {
		return itemMap.get(key);
	}

	public HeadItem removeItem(String key) {
		HeadItem item = itemMap.remove(key);
		FxUtil.invoke(() -> {
			rootPane.removeNode(item);
		});
		return item;
	}

	public boolean hasItem(String key) {
		return null != itemMap.get(key);
	}

	public int getItemSize() {
		return itemMap.size();
	}
}
