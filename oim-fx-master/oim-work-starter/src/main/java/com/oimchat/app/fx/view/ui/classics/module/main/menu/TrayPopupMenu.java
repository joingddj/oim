package com.oimchat.app.fx.view.ui.classics.module.main.menu;

import com.onlyxiahui.app.fx.common.component.OnlyContextMenu;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author XiaHui
 * @date 2015年3月13日 上午10:03:25
 */
public class TrayPopupMenu extends OnlyContextMenu {

	private OnlyMenuItem openMainMenuItem = new OnlyMenuItem();
	private OnlyMenuItem quitMenuItem = new OnlyMenuItem();

	public TrayPopupMenu() {
		initMenu();
		initEvent();
	}

	private void initMenu() {

		openMainMenuItem.setText("打开主面板");
		quitMenuItem.setText("退出");

		this.getItems().add(openMainMenuItem);
		this.getItems().add(quitMenuItem);

	}

	private void initEvent() {

	}

	public void showAll(boolean show) {
	}

	public void setOnOpenMainAction(EventHandler<ActionEvent> value) {
		openMainMenuItem.setOnAction(value);
	}

	public void setOnExitAction(EventHandler<ActionEvent> value) {
		quitMenuItem.setOnAction(value);
	}
}
