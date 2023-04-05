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
public class GroupCategoryMenuWrap extends AbstractMaterial {

	Alert information = new Alert(AlertType.INFORMATION);
	TextInputDialog textInput = new TextInputDialog("");

	private ContextMenu menu = new ContextMenu();
	private OnlyMenuItem addCategoryMenuItem = new OnlyMenuItem();
	private OnlyMenuItem findGroupMenuItem = new OnlyMenuItem();
	private OnlyMenuItem updateCategoryNameMenuItem = new OnlyMenuItem();

	private OnlyMenuItem addMenuItem = new OnlyMenuItem();

	GroupCategory category;

	public GroupCategoryMenuWrap(AppContext appContext) {
		super(appContext);
		initMenu();
		initEvent();
	}

	private void initMenu() {

		// BaseFrame frame=new BaseFrame();

		information.initModality(Modality.APPLICATION_MODAL);
		information.initOwner(menu);
		information.getDialogPane().setHeaderText(null);
		// information.initOwner(frame);

		// textInput.initOwner(frame);
		textInput.setTitle("输入分组");
		textInput.setContentText("名称:");
		textInput.getEditor().setText("");

		addCategoryMenuItem.setText("新建分组");
		findGroupMenuItem.setText("查找群");
		updateCategoryNameMenuItem.setText("重命名分组");
		addMenuItem.setText("创建一个群");

		menu.getItems().add(addCategoryMenuItem);
		menu.getItems().add(findGroupMenuItem);
		menu.getItems().add(updateCategoryNameMenuItem);
		menu.getItems().add(addMenuItem);
	}

	private void initEvent() {

		addCategoryMenuItem.setOnAction(a -> {
			clearData();
			textInput.showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {
				} else {
					addCategory(response);
				}
			});
		});

		findGroupMenuItem.setOnAction(a -> {
			FindView findView = appContext.getObject(FindView.class);
			findView.selectedTab(1);
			findView.setVisible(true);
		});

		updateCategoryNameMenuItem.setOnAction(a -> {
			textInput.getEditor().setText(null == category ? "" : category.getName());
			textInput.showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {
				} else {
					updateUserCategory(response);
				}
			});
		});
		addMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GroupAddView view = appContext.getObject(GroupAddView.class);
				view.setVisible(true);
			}
		});
	}

	public void setCategory(GroupCategory category) {
		this.category = category;
	}

	public void showPrompt(String text) {
		FxUtil.invoke(() -> {
			information.getDialogPane().setContentText(text);
			information.showAndWait();
		});
	}

	public void clearData() {
		textInput.getEditor().setText("");
	}

	public void addCategory(String name) {
		if (null != name && !"".equals(name)) {
			GroupCategory data = new GroupCategory();
			data.setName(name);
			GroupCategoryHandler handler = this.appContext.getObject(GroupCategoryHandler.class);
			handler.add(data, (info, v) -> {
				if (!info.isSuccess()) {
					showPrompt("添加失败！");
				} else {
					clearData();
				}
			});
		}
	}

	public void updateUserCategory(String name) {
		if (null != category && null != name && !"".equals(name)) {
			String id = category.getId();
			GroupCategoryHandler handler = this.appContext.getObject(GroupCategoryHandler.class);
			handler.updateName(id, name, null, (info) -> {
				if (!info.isSuccess()) {
					showPrompt("修改失败！");
				} else {
					clearData();
				}
			});
		}
	}

	public void show(Window ownerWindow, double anchorX, double anchorY) {
		FxUtil.invoke(() -> {
			menu.show(ownerWindow, anchorX, anchorY);
		});
	}

	public void show(Node anchor, double screenX, double screenY) {
		FxUtil.invoke(() -> {
			menu.show(anchor, screenX, screenY);
		});
	}

	public void show(Node anchor, Side side, double dx, double dy) {
		FxUtil.invoke(() -> {
			menu.show(anchor, side, dx, dy);
		});
	}
}
