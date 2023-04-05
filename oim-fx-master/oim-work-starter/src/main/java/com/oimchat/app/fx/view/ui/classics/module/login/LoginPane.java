package com.oimchat.app.fx.view.ui.classics.module.login;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Set;

import com.oimchat.app.fx.base.component.combo.ItemRemoveListCell;
import com.oimchat.app.fx.base.component.loading.LoadingPane;
import com.onlyxiahui.app.fx.OnlyPopupOver;
import com.onlyxiahui.app.fx.common.component.OnlyMenuItem;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.app.view.fx.component.image.ImagePane;
import com.onlyxiahui.app.view.fx.component.media.MediaSimplPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author XiaHui
 * @date 2017-12-05 12:09:20
 */
public class LoginPane extends StackPane {

	private OnlyPopupOver popupOver = new OnlyPopupOver();// 账号提示
	private Label overLabel = new Label();

	private DropShadow dropShadow = new DropShadow();// 阴影特效
	/**
	 * 最底层面板
	 */
	private final StackPane rootStackPane = new StackPane();
	// 背景图片显示组件
	private final ImageView backgroundImageView = new ImageView();
	private final ImageView topImageView = new ImageView();

	// 上部显示背景图片，底部白色分割面板
	private final BorderPane separatePane = new BorderPane();

	private final StackPane centerStackPane = new StackPane();
	private final StackPane userStackPane = new StackPane();
	private final LoadingPane waitingPanel = new LoadingPane();

	private final BorderPane userBorderPane = new BorderPane();

	private Button statusButton = new Button();
	private ImageView statusImageView = new ImageView();// 状态显示
	private final ImagePane headImagePane = new ImagePane();

	// 账号输入框
	private ComboBox<String> accountComboBox = new ComboBox<String>();
	private PasswordField passwordField = new PasswordField();
	private LoginButton loginButton = new LoginButton();// 登录按钮

	protected BorderPane checkBoxBorderPane = new BorderPane();
	// 记住密码
	protected LoginCheckBox rememberCheckBox = new LoginCheckBox();
	// 自动登录
	protected LoginCheckBox autoCheckBox = new LoginCheckBox();

	protected Label registerLabel = new Label();
	protected Label forgetLabel = new Label();

	Button accountButton = new Button();
	Button passwordButton = new Button();

	ContextMenu menu = new ContextMenu();

	ValueEvent<String> valueAction;

	public LoginPane() {
		initComponent();
		initContextMenu();
		initEvent();
		initSet();
	}

	private void initComponent() {

		VBox b = new VBox();
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.getChildren().add(statusButton);
		b.setPickOnBounds(false);// 面板不参与计算边界，透明区域无鼠标事件

		statusButton.getStyleClass().remove("button");
		statusButton.getStyleClass().add("status-button");
		statusButton.setPrefHeight(13);
		statusButton.setPrefWidth(13);

		statusButton.setGraphic(statusImageView);

		headImagePane.setImageRadius(65);
		headImagePane.setImageSize(65);

		headImagePane.setEffect(dropShadow);

		StackPane headStackPane = new StackPane();
		headStackPane.getChildren().add(headImagePane);
		headStackPane.getChildren().add(b);

		Button headButton = new Button();
		headButton.getStyleClass().remove("button");
		headButton.setGraphic(headStackPane);

		HBox headShadowHBox = new HBox();
		headShadowHBox.setAlignment(Pos.CENTER);

		StackPane headPane = new StackPane();
		headPane.getChildren().add(headShadowHBox);
		headPane.getChildren().add(headButton);

		HBox headHBox = new HBox();
		headHBox.setAlignment(Pos.CENTER);
		headHBox.getChildren().add(headPane);

		VBox headVBox = new VBox();
		headVBox.setPadding(new Insets(0, 0, 0, 0));
		headVBox.setAlignment(Pos.BOTTOM_CENTER);
		headVBox.setPrefHeight(160);
		headVBox.getChildren().add(headHBox);

		accountComboBox.setEditable(true);
		accountComboBox.setPromptText("账号");
		accountComboBox.setStyle("-fx-prompt-text-fill:#b3b3b3;-fx-text-font-size: 14px;");
//		accountComboBox.setBackground(Background.EMPTY);
//		accountComboBox.setBorder(Border.EMPTY);

//		accountComboBox.getEditor().setBackground(Background.EMPTY);
//		accountComboBox.getEditor().setBorder(Border.EMPTY);
		accountComboBox.getEditor().getStyleClass().add("empty-text-input-field");
		// accountComboBox.getEditor().getStyleClass().remove("text-field");
		// accountComboBox.getTextField().setStyle("-fx-prompt-text-fill:
		// #b3b3b3;-fx-text-font-size: 14px;");
		// accountField.setStyle("-fx-prompt-text-fill: #b3b3b3;");
		accountComboBox.setCellFactory((ListView<String> list) -> new ItemRemoveListCell<String>(v -> {
			if (null != valueAction) {
				valueAction.value(v);
			}
		}));

		passwordField.setPromptText("密码");
		passwordField.getStyleClass().add("empty-text-input-field");
		passwordField.getStyleClass().remove("text-field");
		passwordField.setStyle("-fx-prompt-text-fill: #b3b3b3;-fx-text-font-size: 14px;");

		rememberCheckBox.setText("记住密码");
		autoCheckBox.setText("自动登录");

		rememberCheckBox.setStyle("-fx-text-fill: #b3b3b3;");
		autoCheckBox.setStyle("-fx-text-fill: #b3b3b3;");

		VBox rVBox = new VBox();
		VBox aVBox = new VBox();
		aVBox.setAlignment(Pos.CENTER_LEFT);

		checkBoxBorderPane.setLeft(rVBox);
		checkBoxBorderPane.setRight(aVBox);

		rVBox.getChildren().add(rememberCheckBox);
		aVBox.getChildren().add(autoCheckBox);

		loginButton.setFocusTraversable(false);
		loginButton.setText("登  录");
		loginButton.setPrefHeight(36);
		loginButton.getStyleClass().add("login-button");

		StackPane loginButtonStackPane = new StackPane();
		loginButtonStackPane.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				loginButton.setPrefWidth(loginButtonStackPane.getWidth());
			}
		});
		loginButtonStackPane.getChildren().add(loginButton);
		loginButtonStackPane.setEffect(dropShadow);
		dropShadow.setSpread(0.05);
		dropShadow.setColor(Color.web("#05bafb"));

		accountButton.getStyleClass().remove("button");
		passwordButton.getStyleClass().remove("button");

		accountButton.getStyleClass().add("account-button");
		passwordButton.getStyleClass().add("password-button");

		accountButton.setGraphic(new ImageView());
		passwordButton.setGraphic(new ImageView());

		// accountComboBox.getArrowButton().getStyleClass().add("account-button");
		// accountButton.setPadding(new Insets(0,2,0,0));

		HBox accountBottomHBox = new HBox();
		accountBottomHBox.setMinHeight(1);
		accountBottomHBox.getStyleClass().add("input-bottom");
		// accountBottomHBox.setStyle("-fx-background-color:#05bafb;");

		HBox passwordBottomHBox = new HBox();
		passwordBottomHBox.setMinHeight(1);
		passwordBottomHBox.getStyleClass().add("input-bottom");

		BorderPane accountBorderPane = new BorderPane();
		accountBorderPane.setTop(accountComboBox);
		// accountBorderPane.setRight(accountButton);
		accountBorderPane.setBottom(accountBottomHBox);

		accountComboBox.prefWidthProperty().bind(accountBorderPane.widthProperty());

		BorderPane passwordBorderPane = new BorderPane();
		passwordBorderPane.setCenter(passwordField);
//		passwordBorderPane.setRight(passwordButton);
		passwordBorderPane.setBottom(passwordBottomHBox);

		VBox inputBox = new VBox();
		inputBox.setSpacing(15);

		inputBox.getChildren().add(accountBorderPane);
		inputBox.getChildren().add(passwordBorderPane);

		VBox inputInofBox = new VBox();
		inputInofBox.setSpacing(10);
		inputInofBox.getChildren().add(inputBox);
		inputInofBox.getChildren().add(checkBoxBorderPane);
		inputInofBox.getChildren().add(loginButtonStackPane);

		registerLabel.setText("注册账号");
		forgetLabel.setText("忘记密码");

		registerLabel.setCursor(Cursor.HAND);
		forgetLabel.setCursor(Cursor.HAND);

		registerLabel.setStyle("-fx-text-fill: #b3b3b3;");
		forgetLabel.setStyle("-fx-text-fill: #b3b3b3;");

		VBox registerVBox = new VBox();
		registerVBox.setAlignment(Pos.BOTTOM_CENTER);
		registerVBox.setPadding(new Insets(0, 35, 10, 12));

		VBox forgetVBox = new VBox();
		forgetVBox.setAlignment(Pos.BOTTOM_CENTER);

		registerVBox.getChildren().add(registerLabel);
		forgetVBox.getChildren().add(forgetLabel);
		forgetVBox.setPadding(new Insets(0, 12, 10, 35));

		userBorderPane.setTop(headVBox);
		userBorderPane.setCenter(inputInofBox);

		userBorderPane.setLeft(registerVBox);
		userBorderPane.setRight(forgetVBox);

		userStackPane.getChildren().add(userBorderPane);
		ProgressIndicator loading = new ProgressIndicator();
		loading.setPrefSize(80, 80);

		// Image waitingImage =
		// ImageLoadUtil.getImageByClassPath("/general/view/common/images/loading/loading_circle_118x118.gif");
		// waitingPanel.setLoading("登录中...", waitingImage);
		waitingPanel.setLoading("登录中...", loading);
		waitingPanel.showNode();
		waitingPanel.getChildren().add(userStackPane);

		centerStackPane.getChildren().add(waitingPanel);

		VBox topBox = new VBox();
		topBox.getChildren().add(topImageView);

		VBox bottomBox = new VBox();
		bottomBox.setStyle("-fx-background-color:#ffffff;");
		bottomBox.setPrefHeight(200);

		separatePane.setTop(topBox);
		separatePane.setBottom(bottomBox);

		rootStackPane.setPrefSize(428, 328);

		rootStackPane.getChildren().add(backgroundImageView);
		rootStackPane.getChildren().add(mp);
		rootStackPane.getChildren().add(separatePane);
		rootStackPane.getChildren().add(centerStackPane);

		this.getChildren().add(rootStackPane);

		popupOver.setArrowLocation(OnlyPopupOver.ArrowLocation.TOP_LEFT);

		popupOver.setDetachable(false);
		popupOver.setDetached(false);

		overLabel.setPadding(new Insets(4));
		popupOver.setContentNode(overLabel);

		mp.setPrefWidth(430);
		mp.setPrefHeight(203);

	}

	private void initEvent() {

		passwordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				passwordField.requestFocus();
				event.consume();
			}
		});
		statusButton.setOnMouseClicked((MouseEvent event) -> {
			menu.show(statusButton, event.getScreenX(), event.getScreenY());
		});

		autoCheckBox.setOnAction(a -> {
			boolean selected = autoCheckBox.isSelected();
			if (selected) {
				rememberCheckBox.setSelected(true);
			}
		});

		rememberCheckBox.setOnAction(a -> {
			boolean selected = rememberCheckBox.isSelected();
			if (!selected) {
				autoCheckBox.setSelected(false);
			}
		});
		this.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
			if (k.getCode() == KeyCode.TAB) {
				tab();
			} else if (k.getCode() == KeyCode.ENTER) {
				EventHandler<ActionEvent> loginAction = loginButton.getOnAction();
				if (null != loginAction) {
					ActionEvent event = new ActionEvent(loginButton, loginButton);
					loginAction.handle(event);
				}
			}
		});
	}

	boolean isAccountFocused = true;

	private void tab() {
		if (isAccountFocused) {
			passwordField.requestFocus();
		} else {
			accountComboBox.requestFocus();
		}
		isAccountFocused = !isAccountFocused;
	}

	private void initContextMenu() {

	}

	private void initSet() {
		Image image = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/login/default/1.png");
		setHeadImage(image);
		this.showWaiting(false);
	}

	public void setBackgroundImage(Image image) {
		backgroundImageView.setImage(image);
	}

	public void setTopImage(Image image) {
		topImageView.setImage(image);
	}

	public void showWaiting(boolean show) {
		if (show) {
			waitingPanel.showLoding();
		} else {
			waitingPanel.showNode();
		}
	}

	public void setHeadImage(Image headImage) {
		headImagePane.setImage(headImage);
	}

	public void setHeadSize(double value) {
		headImagePane.setImageSize(value);
	}

	public void setHeadRadius(double value) {
		headImagePane.setImageRadius(value);
	}

	public String getPassword() {
		return passwordField.getText();
	}

	public void setPassword(String value) {
		passwordField.setText(value);
	}

	public String getAccount() {
		return accountComboBox.getEditor().getText();
	}

	public void setAccount(String value) {
		accountComboBox.getSelectionModel().select(value);
	}

	public void setStatusImage(Image image) {
		statusImageView.setImage(image);
	}

	public boolean isAutoLogin() {
		return autoCheckBox.isSelected();
	}

	public void setAutoLogin(boolean autoLogin) {
		autoCheckBox.setSelected(autoLogin);
	}

	public boolean isRememberPassword() {
		return rememberCheckBox.isSelected();
	}

	public void setRememberPassword(boolean rememberPassword) {
		rememberCheckBox.setSelected(rememberPassword);
	}

	public void setOnLoginAction(EventHandler<ActionEvent> value) {
		loginButton.setOnAction(value);
	}

	public void setRegisterOnMouseClicked(EventHandler<MouseEvent> value) {
		registerLabel.setOnMouseClicked(value);
	}

	public void setForgetOnMouseClicked(EventHandler<MouseEvent> value) {
		forgetLabel.setOnMouseClicked(value);
	}

	public boolean verify() {
		String account = this.getAccount();
		String password = this.getPassword();

		if (null == account || "".equals(account)) {
			overLabel.setText("请输入您的账号登录!");
			popupOver.show(accountComboBox);
			accountComboBox.requestFocus();
			return false;
		}

		if (null == password || "".equals(password)) {
			overLabel.setText("请输入您的密码登录!");
			popupOver.show(passwordField);
			passwordField.requestFocus();
			return false;
		}
		return true;
	}

	private MediaSimplPane mp = new MediaSimplPane();

	public void setVideo(File file) {
		try {
			String pathString = file.toURI().toURL().toString();
			mp.setUrl(pathString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		mp.stop();
	}

	public void play() {
		mp.play();
	}

	public Label getRegisterLabel() {
		return registerLabel;
	}

	public Label getForgetLabel() {
		return forgetLabel;
	}

	public void setAccounts(Set<String> accounts) {
		accountComboBox.getItems().clear();
		accountComboBox.getItems().addAll(accounts);
	}

	public void removeAccount(String value) {
		accountComboBox.getItems().remove(value);
	}

	public void setOnAccountRemove(ValueEvent<String> valueAction) {
		this.valueAction = valueAction;
	}

	public void addAccountListener(ChangeListener<? super String> listener) {
		accountComboBox.valueProperty().addListener(listener);
	}

	public void addStatusImage(String status, String text, Image image, ValueEvent<String> valueAction) {
		ImageView iv = new ImageView();
		iv.setImage(image);
		OnlyMenuItem menuItem = new OnlyMenuItem(text, iv);
		menuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				statusImageView.setImage(image);
				if (null != valueAction) {
					valueAction.value(status);
				}
			}
		});
		menu.getItems().add(menuItem);
	}
}
