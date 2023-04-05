package com.oimchat.app.fx.view.ui.classics.module.main.menu;

import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.scene.control.ContextMenu;

/**
 * @author XiaHui
 * @date 2015年3月13日 上午10:03:25
 */
public class GroupContextMenu<T> extends ContextMenu {

	private OnlyMenuItem showMenuItem = new OnlyMenuItem();
	private OnlyMenuItem updateMenuItem = new OnlyMenuItem();
	private OnlyMenuItem settingMenuItem = new OnlyMenuItem();

	T group;
	ValueEvent<T> updateEventAction;
	ValueEvent<T> showEventAction;
	ValueEvent<T> settingEventAction;

	public GroupContextMenu() {
		initMenu();
		initEvent();
	}

	private void initMenu() {
		showMenuItem.setText("查看群信息");
		updateMenuItem.setText("修改群信息");
		settingMenuItem.setText("设置");
		this.getItems().add(showMenuItem);
		this.getItems().add(updateMenuItem);
		this.getItems().add(settingMenuItem);
	}

	private void initEvent() {
		updateMenuItem.setOnAction(a -> {
			if (null != updateEventAction) {
				updateEventAction.value(group);
			}
		});
		showMenuItem.setOnAction(a -> {
			if (null != showEventAction) {
				showEventAction.value(group);
			}
		});

		settingMenuItem.setOnAction(a -> {
			if (null != settingEventAction) {
				settingEventAction.value(group);
			}
		});
	}

	public void setUpdateAction(ValueEvent<T> value) {
		updateEventAction = value;
	}

	public void setShowAction(ValueEvent<T> value) {
		showEventAction = value;
	}

	public void setSettingAction(ValueEvent<T> value) {
		settingEventAction = value;
	}

	public void showEdit(boolean show) {
		updateMenuItem.setVisible(show);
		settingMenuItem.setVisible(show);
	}

	public T getGroup() {
		return group;
	}

	public void setGroup(T group) {
		this.group = group;
	}
}
