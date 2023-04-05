package com.oimchat.app.fx.view.ui.classics.module.main.menu;

import com.onlyxiahui.app.fx.common.component.OnlyContextMenu;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author: XiaHui
 * @date: 2016年10月11日 下午3:43:16
 */
public class StatusPopupMenu extends OnlyContextMenu {

	private ValueEvent<String> statusAction;

	public StatusPopupMenu() {
		initMenu();
	}

	private void initMenu() {

	}

	private void updateStatus(String status) {
		if (null != statusAction) {
			statusAction.value(status);
		}
	}

	public void setStatusAction(ValueEvent<String> statusAction) {
		this.statusAction = statusAction;
	}

	public void addSeparator() {
		this.getItems().add(new SeparatorMenuItem());
	}

	public void addItem(String status, String text, Image image) {
		ImageView iv = new ImageView();
		iv.setImage(image);
		OnlyMenuItem menuItem = new OnlyMenuItem(text, iv);
		menuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				updateStatus(status);
			}
		});
		this.getItems().add(menuItem);
	}
}
