package com.oimchat.app.fx.view.ui.classics.module.main.menu;

import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.scene.control.ContextMenu;

/**
 * @author XiaHui
 * @date 2015年3月13日 上午10:03:25
 */
public class UserContextMenu<T> extends ContextMenu {

	// private OnlyMenuItem updateMenuItem = new OnlyMenuItem();
	private OnlyMenuItem showMenuItem = new OnlyMenuItem();
	T userData;
	ValueEvent<T> updateEventAction;
	ValueEvent<T> showEventAction;

	public UserContextMenu() {
		initMenu();
		initEvent();
	}

	private void initMenu() {
		showMenuItem.setText("查看信息");
		// updateMenuItem.setText("修改群信息");
		this.getItems().add(showMenuItem);
		// this.getItems().add(updateMenuItem);
	}

	private void initEvent() {
//		updateMenuItem.setOnAction(a -> {
//			if (null != updateEventAction) {
//				updateEventAction.execute(userData);
//			}
//		});
		showMenuItem.setOnAction(a -> {
			if (null != showEventAction) {
				showEventAction.value(userData);
			}
		});
	}

//	public void setUpdateAction(EventAction<UserData> value) {
//		updateEventAction = value;
//	}

	public void setShowAction(ValueEvent<T> value) {
		showEventAction = value;
	}

	public T getUser() {
		return userData;
	}

	public void setUser(T userData) {
		this.userData = userData;
	}
}
