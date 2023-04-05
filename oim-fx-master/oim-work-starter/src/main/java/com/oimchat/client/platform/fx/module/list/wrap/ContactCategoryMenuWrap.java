
package com.oimchat.client.platform.fx.module.list.wrap;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactCategoryHandler;
import com.oimchat.client.platform.common.view.FindView;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * Description <br>
 * Date 2021-03-17 16:23:28<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryMenuWrap extends AbstractMaterial {

	Alert information = new Alert(AlertType.INFORMATION);
	TextInputDialog textInput = new TextInputDialog("");

	private ContextMenu menu = new ContextMenu();
	private OnlyMenuItem addCategoryMenuItem = new OnlyMenuItem();
	private OnlyMenuItem findUserMenuItem = new OnlyMenuItem();
	private OnlyMenuItem updateCategoryNameMenuItem = new OnlyMenuItem();

	ContactCategory category;

	public ContactCategoryMenuWrap(AppContext appContext) {
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

		addCategoryMenuItem.setText("新建分组");
		findUserMenuItem.setText("查找用户");
		updateCategoryNameMenuItem.setText("重命名分组");

		menu.getItems().add(addCategoryMenuItem);
		menu.getItems().add(findUserMenuItem);
		menu.getItems().add(updateCategoryNameMenuItem);
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

		findUserMenuItem.setOnAction(a -> {
			FindView findView = appContext.getObject(FindView.class);
			findView.selectedTab(0);
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

	}

	public void setCategory(ContactCategory category) {
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
			ContactCategory data = new ContactCategory();
			data.setName(name);

			ContactCategoryHandler uch = this.appContext.getObject(ContactCategoryHandler.class);
			uch.add(data, (info, v) -> {
				if (!info.isSuccess()) {
					showPrompt("新增失败！");
				} else {
					clearData();
				}
			});
		}
	}

	public void updateUserCategory(String name) {
		if (null != category && null != name && !"".equals(name)) {
			String id = category.getId();
			ContactCategoryHandler uch = this.appContext.getObject(ContactCategoryHandler.class);
			uch.updateName(id, name, (info) -> {
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
