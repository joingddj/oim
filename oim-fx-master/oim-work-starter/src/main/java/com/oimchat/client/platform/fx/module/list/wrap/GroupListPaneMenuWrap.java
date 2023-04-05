package com.oimchat.client.platform.fx.module.list.wrap;

import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupCategoryHandler;
import com.oimchat.client.platform.common.view.FindView;
import com.oimchat.client.platform.common.view.GroupAddView;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * @author XiaHui
 * @date 2017年9月4日 下午5:22:57
 */
public class GroupListPaneMenuWrap extends AbstractMaterial {

	Alert information = new Alert(AlertType.INFORMATION);
	TextInputDialog textInput = new TextInputDialog("");

	private ContextMenu menu = new ContextMenu();

	private OnlyMenuItem refreshListMenuItem = new OnlyMenuItem();

	private OnlyMenuItem addCategoryMenuItem = new OnlyMenuItem();
	private OnlyMenuItem findGroupMenuItem = new OnlyMenuItem();

	private OnlyMenuItem addMenuItem = new OnlyMenuItem();
	
	public GroupListPaneMenuWrap(AppContext appContext) {
		super(appContext);
		initMenu();
		initEvent();
	}

	private void initMenu() {

		information.initModality(Modality.APPLICATION_MODAL);
		information.initOwner(menu);
		information.getDialogPane().setHeaderText(null);

		textInput.setTitle("输入分组");
		textInput.setContentText("名称:");
		textInput.getEditor().setText("");

		refreshListMenuItem.setText("刷新列表");
		addCategoryMenuItem.setText("新建分组");
		findGroupMenuItem.setText("查找群");
		addMenuItem.setText("创建一个群");

		menu.getItems().add(refreshListMenuItem);
		menu.getItems().add(addCategoryMenuItem);
		menu.getItems().add(findGroupMenuItem);
		menu.getItems().add(addMenuItem);
	}

	private void initEvent() {

		addCategoryMenuItem.setOnAction(a -> {
			clearData();
			textInput.showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {

				} else {
					addGroupCategory(response);
				}
			});
		});

		findGroupMenuItem.setOnAction(a -> {
			FindView findView = appContext.getObject(FindView.class);
			findView.selectedTab(1);
			findView.setVisible(true);
		});

		refreshListMenuItem.setOnAction(a -> {
			GroupListViewWrap view = appContext.getObject(GroupListViewWrap.class);
			view.initializeData();
		});
		
		addMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GroupAddView view = appContext.getObject(GroupAddView.class);
				view.setVisible(true);
			}
		});
	}

	public void clearData() {
		FxUtil.invoke(() -> {
			textInput.getEditor().setText("");
		});
	}

	public void addGroupCategory(String name) {
		if (null != name && !"".equals(name)) {

			GroupCategory data = new GroupCategory();
			data.setName(name);

			GroupCategoryHandler uch = this.appContext.getObject(GroupCategoryHandler.class);
			uch.add(data, (info, v) -> {
				if (!info.isSuccess()) {
					showPrompt("新增失败！");
				} else {
					clearData();
				}
			});
		}
	}

	public void showPrompt(String text) {
		FxUtil.invoke(() -> {
			information.getDialogPane().setContentText(text);
			information.showAndWait();
		});
	}

	public void addFront(OnlyMenuItem item) {
		menu.getItems().add(item);
	}

	public void addLast(OnlyMenuItem item) {
		menu.getItems().add(item);
	}

	public void show(Window ownerWindow, double anchorX, double anchorY) {
		menu.show(ownerWindow, anchorX, anchorY);
	}

	public void show(Node anchor, double screenX, double screenY) {
		menu.show(anchor, screenX, screenY);
	}

	public void show(Node anchor, Side side, double dx, double dy) {
		menu.show(anchor, side, dx, dy);
	}
}
