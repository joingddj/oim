package com.oimchat.app.fx.view.ui.classics.module.login;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.onlyxiahui.app.fx.OnlyDecoratedPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * 
 * Description <br>
 * Date 2020-07-07 17:09:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class LoginStage extends ClassicsStage {

	private Button settingButton = new Button();// 顶部设置按钮
	protected LoginPane loginPane = new LoginPane();

	public LoginStage() {
		initComponent();
		initEvent();
	}

	private void initComponent() {
		this.getScene().getStylesheets().add(this.getClass().getResource("/general/view/theme/classics/css/login.css").toString());
		this.setCenter(loginPane);
		this.setResizable(false);
		this.setTitlePaneStyle(2);
		settingButton.getStyleClass().remove("button");
		settingButton.getStyleClass().add("setting-button");
		settingButton.setGraphic(new ImageView());

		OnlyDecoratedPane titlePane = this.getOnlyDecoratedPane();
		titlePane.getChildren().add(0, settingButton);

		this.setRadius(8);
		// loginPane.setBackgroundImage(ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/login/b1.jpg"));
	}

	private void initEvent() {
		this.setOnHidden(h -> {
			loginPane.stop();
		});
		this.setOnShowing(s -> {
			loginPane.play();
		});
	}

	public void setOnSettingAction(EventHandler<ActionEvent> value) {
		settingButton.setOnAction(value);
	}

	public LoginPane getLoginPane() {
		return loginPane;
	}
}
