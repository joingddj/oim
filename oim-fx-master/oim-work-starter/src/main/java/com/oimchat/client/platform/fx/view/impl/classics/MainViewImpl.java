
package com.oimchat.client.platform.fx.view.impl.classics;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.main.MainStage;
import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalEventObservable;
import com.oimchat.client.general.kernel.work.module.personal.observer.listener.PersonalEventListener;
import com.oimchat.client.general.unit.SystemUnit;
import com.oimchat.client.platform.common.view.MainView;
import com.oimchat.client.platform.common.view.ThemeView;
import com.oimchat.client.platform.fx.module.list.wrap.ContactListViewWrap;
import com.oimchat.client.platform.fx.module.list.wrap.GroupListViewWrap;
import com.oimchat.client.platform.fx.module.list.wrap.MessageListViewWrap;
import com.oimchat.client.platform.fx.module.main.wrap.MainPaneViewWrap;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.fx.OnlyDecoratedPane;
import com.onlyxiahui.app.fx.OnlyWindowAutoHide;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.app.view.fx.component.icon.IconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;
import com.onlyxiahui.app.view.fx.component.tab.IconTab;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * Description <br>
 * Date 2021-03-15 09:17:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MainViewImpl extends BaseStageView<MainStage> implements MainView {

	public MainViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
		initConfig();
	}

	private void initConfig() {
		MainPaneViewWrap mpvs = this.appContext.getObject(MainPaneViewWrap.class);

		ContactListViewWrap cvs = appContext.getObject(ContactListViewWrap.class);
		ListRootPane contactListPane = cvs.getListPaneMapper().getRootPane();
		IconTab contactTab = cvs.getTab();

		GroupListViewWrap gvs = appContext.getObject(GroupListViewWrap.class);
		ListRootPane groupListPane = gvs.getListPaneMapper().getRootPane();
		IconTab groupTab = gvs.getTab();

		MessageListViewWrap mlvw = this.appContext.getObject(MessageListViewWrap.class);
		ListRootPane messageListPane = mlvw.getRootPane();
		IconTab messageTab = mlvw.getTab();

		FxUtil.invoke(() -> {
//			Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_normal.png");
//			Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_hover.png");
//			Image selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_selected.png");

			FontIconText contactIcon = new FontAwesomeSolidIconText();
			contactIcon.setFontIcon("\uf0c0");
			contactIcon.setIconFontSize(38.2D);
			mpvs.getMainPane().addTab(contactTab, contactListPane);
		});

		FxUtil.invoke(() -> {
//			Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_normal.png");
//			Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_hover.png");
//			Image selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_selected.png");
			mpvs.getMainPane().addTab(groupTab, groupListPane);
		});

		FxUtil.invoke(() -> {
//			Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_normal.png");
//			Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_hover.png");
//			Image selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_selected.png");
			mpvs.getMainPane().addTab(messageTab, messageListPane);
		});

		PersonalEventObservable peo = this.appContext.getObject(PersonalEventObservable.class);
		peo.addListener(new PersonalEventListener() {

			@Override
			public void updatePassword() {
				shwoUpdatePassword();

			}

			@Override
			public void otherOnline(Client client, boolean offline) {
				if (offline) {
					shwoDifferentLogin();
				}
			}
		});
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		FxUtil.invoke(() -> {
			if (visible) {
				if (stage.isShowing()) {
					stage.requestFocus();
				}
			}
		});
	}

	@Override
	public void initializeData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected MainStage createStage() {
		return new MainStage();
	}

	@Override
	protected void initStage(MainStage stage) {
		new OnlyWindowAutoHide(stage);
	}

	@Override
	protected void initComponent() {
		MainPaneViewWrap mpvs = this.appContext.getObject(MainPaneViewWrap.class);
		MainStage stage = getStage();
		putStage(stage);
		FxUtil.invoke(() -> {
			stage.setCenter(mpvs.getMainPane());
		});

		Image businessImage = null;

		businessImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/title/btn_Skin_normal_2.png");
		IconButton themeIconButton = new IconButton(businessImage);
		themeIconButton.setPrefSize(30, 27);
		themeIconButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ThemeView view = appContext.getObject(ThemeView.class);
				view.setVisible(true);
			}
		});
		OnlyDecoratedPane titlePane = stage.getOnlyDecoratedPane();
		titlePane.getChildren().add(0, themeIconButton);
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
	}

	protected Alert createAlert(String text) {
		Alert alert = new Alert(AlertType.INFORMATION, "");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(getStage());
		alert.getDialogPane().setContentText(text);
		alert.getDialogPane().setHeaderText(null);
		return alert;
	}

	public void shwoDifferentLogin() {
		SystemUnit sm = appContext.getObject(SystemUnit.class);
		FxUtil.invoke(() -> {
			createAlert("你的帐号在其他的地方登录！")
					.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> sm.exit());
		});
	}

	public void shwoUpdatePassword() {
		SystemUnit sm = appContext.getObject(SystemUnit.class);
		FxUtil.invoke(() -> {
			createAlert("密码已修改，请重新登录！")
					.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> sm.exit());
		});
	}
}
