package com.oimchat.app.fx.base.component.head;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.app.fx.base.component.choose.ChoosePane;
import com.onlyxiahui.app.view.fx.component.image.ImageNode;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author XiaHui
 */
public class HeadItem extends ChoosePane {

	private final BorderPane borderPane = new BorderPane();
	private final StackPane headStackPane = new StackPane();

	private final ImageNode headPane = new ImageNode();

	private final AnchorPane headShowPane = new AnchorPane();

	private final VBox textBaseVBox = new VBox();
	private final HBox textHBox = new HBox();

	private final StackPane infoStackPane = new StackPane();
	private final HBox timeHBox = new HBox();
	private final HBox infoHBox = new HBox();
	private final HBox businessHBox = new HBox();

	private final Label timeLabel = new Label();

	private final Label remarkLabel = new Label();
	private final Label nicknameLabel = new Label();
	private final Label numberLabel = new Label();
	private final Label statusLabel = new Label();

	private final Label lightLabel = new Label();
	private final Label textLabel = new Label();

	private String remark;
	private String nickname;
	private String status;
	private String showText;
	private final Timeline animation = new Timeline();
	boolean pulse = false;

	private final Label redLabel = new Label();

	private BooleanProperty red = new SimpleBooleanProperty(false);

	private Map<String, Node> businessIconMap = new HashMap<>();

	public HeadItem() {
		initComponent();
		iniEvent();
		initSet();
	}

	private void initComponent() {

		this.getChildren().add(borderPane);
		this.getStyleClass().add("head-item");

		headPane.setLayoutX(10);
		headPane.setLayoutY(10);

		headShowPane.setPrefWidth(60);
		headShowPane.setPrefHeight(60);
		headShowPane.getChildren().add(headPane);

		headPane.setImageSize(40);
		headPane.setImageRadius(40);

		// StackPane headStackPane = new StackPane();
		// headStackPane.setPadding(new Insets(10, 8, 12, 15));
		headStackPane.getChildren().add(headShowPane);

		redLabel.setStyle("-fx-text-fill:rgba(255, 255, 255, 1);-fx-background-color: red;-fx-background-radius: 50%;");
		redLabel.setMinSize(16, 16);
		redLabel.setAlignment(Pos.CENTER);

		StackPane redStackPane = new StackPane();
		redStackPane.getChildren().add(redLabel);

		HBox redHBox = new HBox();
		redHBox.setAlignment(Pos.TOP_RIGHT);
		redHBox.getChildren().add(redStackPane);
		redHBox.setPadding(new Insets(5, 5, 0, 0));

		VBox redVBox = new VBox();
		redVBox.setAlignment(Pos.TOP_RIGHT);
		redVBox.getChildren().add(redHBox);

		StackPane leftStackPane = new StackPane();

		leftStackPane.getChildren().add(headStackPane);
		leftStackPane.getChildren().add(redVBox);

		borderPane.setLeft(leftStackPane);
		borderPane.setCenter(textBaseVBox);

		timeHBox.setPadding(new Insets(0, 20, 0, 0));
		timeHBox.setAlignment(Pos.CENTER_RIGHT);
		timeHBox.getChildren().add(timeLabel);

		infoHBox.getChildren().add(remarkLabel);
		infoHBox.getChildren().add(nicknameLabel);
		infoHBox.getChildren().add(numberLabel);
		infoHBox.getChildren().add(statusLabel);

		infoStackPane.getChildren().add(infoHBox);
		infoStackPane.getChildren().add(timeHBox);

		textHBox.getChildren().add(businessHBox);
		textHBox.getChildren().add(lightLabel);
		textHBox.getChildren().add(textLabel);

		textBaseVBox.setAlignment(Pos.CENTER_LEFT);
		textBaseVBox.setPadding(new Insets(0, 0, 0, 0));
		textBaseVBox.getChildren().add(infoStackPane);
		textBaseVBox.getChildren().add(textHBox);

		timeLabel.setStyle("-fx-font-size:13px;-fx-text-fill:derive(-fx-text-inner-color,70%);");

		remarkLabel.setStyle("-fx-font-size:13px;");
		nicknameLabel.setStyle("-fx-font-size:13px;-fx-text-fill:derive(-fx-text-inner-color,70%);");
		numberLabel.setStyle("-fx-font-size:13px;-fx-text-fill:derive(-fx-text-inner-color,70%);");
		statusLabel.setStyle("-fx-font-size:13px;-fx-text-fill:derive(-fx-text-inner-color,70%);");

		textLabel.setStyle("-fx-font-size:13px;-fx-text-fill:derive(-fx-text-inner-color,70%);");
		lightLabel.setStyle("-fx-font-size:13px;-fx-text-fill: red;");
	}

	private void iniEvent() {

		// animation.setAutoReverse(true);
		animation.setCycleCount(Animation.INDEFINITE);

		KeyValue kx1 = new KeyValue(headPane.layoutXProperty(), headPane.getLayoutX() + 1);
		KeyValue ky1 = new KeyValue(headPane.layoutYProperty(), headPane.getLayoutY() - 1);

		KeyValue kx2 = new KeyValue(headPane.layoutXProperty(), headPane.getLayoutX() + 1);
		KeyValue ky2 = new KeyValue(headPane.layoutYProperty(), headPane.getLayoutY() + 1);

		KeyValue kx3 = new KeyValue(headPane.layoutXProperty(), headPane.getLayoutX() - 1);
		KeyValue ky3 = new KeyValue(headPane.layoutYProperty(), headPane.getLayoutY() - 1);

		KeyValue kx4 = new KeyValue(headPane.layoutXProperty(), headPane.getLayoutX() - 1);
		KeyValue ky4 = new KeyValue(headPane.layoutYProperty(), headPane.getLayoutY() + 1);

		KeyFrame kfx1 = new KeyFrame(new Duration(160), kx1);
		KeyFrame kfy1 = new KeyFrame(new Duration(160), ky1);
		KeyFrame kfx2 = new KeyFrame(new Duration(320), kx2);
		KeyFrame kfy2 = new KeyFrame(new Duration(320), ky2);
		KeyFrame kfx3 = new KeyFrame(new Duration(480), kx3);
		KeyFrame kfy3 = new KeyFrame(new Duration(480), ky3);
		KeyFrame kfx4 = new KeyFrame(new Duration(640), kx4);
		KeyFrame kfy4 = new KeyFrame(new Duration(640), ky4);

		animation.getKeyFrames().add(kfx1);
		animation.getKeyFrames().add(kfy1);
		animation.getKeyFrames().add(kfx2);
		animation.getKeyFrames().add(kfy2);
		animation.getKeyFrames().add(kfx3);
		animation.getKeyFrames().add(kfy3);
		animation.getKeyFrames().add(kfx4);
		animation.getKeyFrames().add(kfy4);

		red.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				updateRed();
			}
		});

	}

	private void initSet() {
		this.updateRed();
	}

	private void updateRed() {
		redLabel.setVisible(isRed());
	}

	public void setHeadImage(Image image) {
		headPane.setImage(image);
	}

	public boolean isGray() {
		return headPane.isGray();
	}

	public void setGray(boolean gray) {
		headPane.setGray(gray);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		remarkLabel.setText(remark);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		nicknameLabel.setText(nickname);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		statusLabel.setText(status);
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
		textLabel.setText(showText);
	}

	public void setTime(String time) {
		this.timeLabel.setText(time);
	}

	public void setPulse(boolean pulse) {
		this.pulse = pulse;
		if (!pulse) {
			if (animation.getStatus() == Animation.Status.RUNNING) {
				animation.stop();
			}
		} else {
			animation.play();
		}
	}

	public boolean isPulse() {
		return pulse;
	}

	public void setHeadSize(double value) {
		headPane.setImageSize(value);
		headPane.setImageRadius(value);
	}

	public AnchorPane getHeadShowPane() {
		return headShowPane;
	}

	public ImageNode getHeadPane() {
		return headPane;
	}

	public void setRed(boolean red) {
		this.red.set(red);
	}

	public boolean isRed() {
		return this.red.get();
	}

	public void setRedText(String value) {
		redLabel.setText(value);
	}

	public <T extends Node> void addBusinessIcon(String key, T node) {
		Node o = businessIconMap.get(key);
		if (null != o) {
			businessHBox.getChildren().remove(o);
		}
		businessHBox.getChildren().add(node);
		businessIconMap.put(key, node);
	}

	@SuppressWarnings("unchecked")
	public <T extends Node> T removeBusinessIcon(String key) {
		T o = (T) businessIconMap.remove(key);
		if (null != o) {
			businessHBox.getChildren().remove(o);
		}
		return o;
	}

	@SuppressWarnings("unchecked")
	public <T extends Node> T getBusinessIcon(String key) {
		T o = (T) businessIconMap.get(key);
		return o;
	}

	public HBox getBusinessBox() {
		return businessHBox;
	}

	public void setRight(Node node) {
		borderPane.setRight(node);
	}
}
