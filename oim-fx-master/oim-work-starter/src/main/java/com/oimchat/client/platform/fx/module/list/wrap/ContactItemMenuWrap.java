package com.oimchat.client.platform.fx.module.list.wrap;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.platform.common.view.UserDataView;
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
 * @author XiaHui
 * @date 2017年9月4日 下午5:22:57
 */
public class ContactItemMenuWrap extends AbstractMaterial {

	Alert information = new Alert(AlertType.INFORMATION);
	TextInputDialog textInput = new TextInputDialog("");

	private ContextMenu menu = new ContextMenu();
	private OnlyMenuItem showMenuItem = new OnlyMenuItem();
	private OnlyMenuItem updateRemarkMenuItem = new OnlyMenuItem();
	private OnlyMenuItem deleteMenuItem = new OnlyMenuItem();

	private User user;
	private ContactRelation relation;
	private String userId;

	public ContactItemMenuWrap(AppContext appContext) {
		super(appContext);
		initMenu();
		initEvent();
	}

	private void initMenu() {

		information.initModality(Modality.APPLICATION_MODAL);
		information.initOwner(menu);
		information.getDialogPane().setHeaderText(null);

		textInput.setTitle("输入备注");
		textInput.setContentText("备注:");
		textInput.getEditor().setText("");

		showMenuItem.setText("查看信息");
		updateRemarkMenuItem.setText("修改备注");
		deleteMenuItem.setText("删除好友");

		menu.getItems().add(showMenuItem);
		menu.getItems().add(updateRemarkMenuItem);
		menu.getItems().add(deleteMenuItem);
	}

	private void initEvent() {

		showMenuItem.setOnAction(a -> {
			UserDataView v = appContext.getObject(UserDataView.class);
			v.showUserId(userId);
			v.setVisible(true);
		});
		updateRemarkMenuItem.setOnAction(a -> {
			textInput.getEditor().setText(null == relation ? "" : relation.getRemark());
			textInput.showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {
				} else {
					updateUserRemark(response);
				}
			});
		});
		deleteMenuItem.setOnAction(a -> {
			deleteUser();
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContactRelation getRelation() {
		return relation;
	}

	public void setRelation(ContactRelation relation) {
		this.relation = relation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		laodData();
	}

	private void laodData() {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, user) -> {
			setUser(user);
		});

		ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
		cch.getByContactUserId(userId, (info, relation) -> {
			setRelation(relation);
		});
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

	public void updateUserRemark(String remark) {
		if (null != userId && null != remark && !"".equals(remark)) {
			ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
			cch.updateRemark(remark, remark, (info) -> {
				if (info.isSuccess()) {

				} else {
					showPrompt("修改失败！");
				}
			});
		}
	}

	public void deleteUser() {
		if (null != userId) {
			ContactRelationHandler cch = this.appContext.getObject(ContactRelationHandler.class);
			cch.deleteByContactUserId(userId, (info) -> {
				if (info.isSuccess()) {
				} else {
					showPrompt("删除失败！");
				}
			});
		}
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
