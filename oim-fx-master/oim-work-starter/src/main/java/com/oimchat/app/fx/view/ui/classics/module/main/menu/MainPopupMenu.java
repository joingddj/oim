package com.oimchat.app.fx.view.ui.classics.module.main.menu;

import com.onlyxiahui.app.fx.common.component.OnlyContextMenu;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author XiaHui
 * @date 2015年3月13日 上午10:03:25
 */
public class MainPopupMenu extends OnlyContextMenu {

	protected OnlyMenuItem updatePasswordMenuItem = new OnlyMenuItem();
	private OnlyMenuItem updateMenuItem = new OnlyMenuItem();
	private OnlyMenuItem quitMenuItem = new OnlyMenuItem();

	public MainPopupMenu() {
		initMenu();
		initEvent();
	}

	private void initMenu() {
		updateMenuItem.setText("修改资料");
		updatePasswordMenuItem.setText("修改密码");
		quitMenuItem.setText("退出");

		this.getItems().add(updateMenuItem);
		this.getItems().add(updatePasswordMenuItem);
		this.getItems().add(quitMenuItem);

	}

	private void initEvent() {

	}

	public void setQuitAction(EventHandler<ActionEvent> value) {
		quitMenuItem.setOnAction(value);
	}

	public void setUpdateAction(EventHandler<ActionEvent> value) {
		updateMenuItem.setOnAction(value);
	}

	public void setUpdatePasswordAction(EventHandler<ActionEvent> value) {
		this.updatePasswordMenuItem.setOnAction(value);
	}
}
