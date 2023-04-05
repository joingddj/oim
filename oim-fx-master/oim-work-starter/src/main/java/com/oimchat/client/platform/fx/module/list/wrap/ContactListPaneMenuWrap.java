
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
 * Date 2021-03-27 21:28:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactListPaneMenuWrap extends AbstractMaterial {

	Alert information = new Alert(AlertType.INFORMATION);
	TextInputDialog textInput = new TextInputDialog("");

	private ContextMenu menu = new ContextMenu();

	private OnlyMenuItem refreshListMenuItem = new OnlyMenuItem();

	private OnlyMenuItem addCategoryMenuItem = new OnlyMenuItem();
	private OnlyMenuItem findUserMenuItem = new OnlyMenuItem();

	public ContactListPaneMenuWrap(AppContext appContext) {
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
		findUserMenuItem.setText("查找用户");

		menu.getItems().add(refreshListMenuItem);
		menu.getItems().add(addCategoryMenuItem);
		menu.getItems().add(findUserMenuItem);
	}

	private void initEvent() {

		addCategoryMenuItem.setOnAction(a -> {
			clearData();
			textInput.showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {

				} else {
					addUserCategory(response);
				}
			});
		});

		findUserMenuItem.setOnAction(a -> {
			FindView findView = appContext.getObject(FindView.class);
			findView.selectedTab(0);
			findView.setVisible(true);
		});

		refreshListMenuItem.setOnAction(a -> {
			ContactListViewWrap view = appContext.getObject(ContactListViewWrap.class);
			view.initializeData();
		});
	}

	public void clearData() {
		FxUtil.invoke(() -> {
			textInput.getEditor().setText("");
		});
	}

	public void addUserCategory(String name) {
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
