package com.oimchat.app.fx.base.component.head;

import com.oimchat.app.fx.base.component.choose.ChoosePane;
import com.onlyxiahui.app.view.fx.component.image.ImageNode;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HeadTextItem extends ChoosePane {

	// private static final String DEFAULT_STYLE_CLASS = "chat-item";

	private final BorderPane borderPane = new BorderPane();

	/** 头像显示组件 **/
	private final ImageNode imageNode = new ImageNode();
	/** 昵称/姓名显示组件 **/
	private final Label nameLabel = new Label();
	private final Label textLabel = new Label();

	private final Label timeLabel = new Label();

	private final Label redLabel = new Label();

	private BooleanProperty red = new SimpleBooleanProperty(false);

	public HeadTextItem() {
		initComponent();
		initEvent();
		initSet();
	}

	private void initComponent() {

		StackPane headStackPane = new StackPane();
		headStackPane.setPadding(new Insets(10, 8, 12, 15));
		headStackPane.getChildren().add(imageNode);

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

		nameLabel.setStyle("-fx-font-size:14px;");

		HBox nameHBox = new HBox();
		nameHBox.getChildren().add(nameLabel);

		timeLabel.setStyle("-fx-text-fill:#666a77;");

		BorderPane nameBorderPane = new BorderPane();
		nameBorderPane.setPadding(new Insets(0, 10, 0, 0));

		nameBorderPane.setCenter(nameHBox);
		nameBorderPane.setRight(timeLabel);

		textLabel.setStyle("-fx-text-fill:#5a5a5a;");

		HBox textHBox = new HBox();
		textHBox.setAlignment(Pos.CENTER_LEFT);
		textHBox.getChildren().add(textLabel);

		VBox centerVBox = new VBox();
		centerVBox.setSpacing(5);
		centerVBox.setPadding(new Insets(10, 10, 0, 0));
		centerVBox.getChildren().add(nameBorderPane);
		centerVBox.getChildren().add(textHBox);

		borderPane.setLeft(leftStackPane);
		borderPane.setCenter(centerVBox);
		this.getChildren().add(borderPane);
	}

	private void initEvent() {
		this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent me) -> {
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, (MouseEvent me) -> {
		});

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
		});

		this.setOnMouseClicked(m -> {
		});

		red.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				updateRed();
			}
		});
	}

	private void initSet() {
		this.setHeadRadius(40);
		this.setHeadSize(40);
		this.updateRed();
	}

	private void updateRed() {
//		Image redImage = ImageBox.getImageClassPath("/general/view/theme/classics/images/chat/item/red-16x16.png");
		redLabel.setVisible(isRed());
	}

	public void setHeadSize(double value) {
		imageNode.setImageSize(value);
	}

	public void setHeadRadius(double value) {
		imageNode.setImageRadius(value);
	}

	public void setHeadImage(Image value) {
		imageNode.setImage(value);
	}

	public void setName(String value) {
		nameLabel.setText(value);
	}

	public void setText(String value) {
		textLabel.setText(value);
	}

	public void setTime(String value) {
		timeLabel.setText(value);
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

	public boolean isGray() {
		return imageNode.isGray();
	}

	public void setGray(boolean gray) {
		imageNode.setGray(gray);
	}
}
