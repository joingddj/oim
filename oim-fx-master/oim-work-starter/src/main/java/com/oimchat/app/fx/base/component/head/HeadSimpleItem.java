package com.oimchat.app.fx.base.component.head;

import com.oimchat.app.fx.base.component.choose.ChoosePane;
import com.onlyxiahui.app.view.fx.component.image.ImageNode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * 
 * Description <br>
 * Date 2020-07-05 18:13:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class HeadSimpleItem extends ChoosePane {

	private final BorderPane borderPane = new BorderPane();
	/** 头像显示组件 **/
	private final ImageNode imagePane = new ImageNode();
	/** 头像圆角修剪 **/
	/** 昵称/姓名显示组件 **/
	private final Label nameLabel = new Label();

	public HeadSimpleItem() {
		initComponent();
		initEvent();
		initSet();
	}

	private void initComponent() {

		StackPane headStackPane = new StackPane();
		headStackPane.setPadding(new Insets(5, 5, 5, 10));
		headStackPane.getChildren().add(imagePane);

		StackPane leftStackPane = new StackPane();
		leftStackPane.getChildren().add(headStackPane);

		nameLabel.setStyle("-fx-font-size: 14px;");

		HBox nameHBox = new HBox();
		nameHBox.setAlignment(Pos.CENTER_LEFT);
		nameHBox.getChildren().add(nameLabel);

		VBox centerVBox = new VBox();
		centerVBox.setAlignment(Pos.CENTER_LEFT);
		centerVBox.getChildren().add(nameHBox);

		borderPane.setLeft(leftStackPane);
		borderPane.setCenter(centerVBox);
		// this.getStyleClass().add("chat-item-pane");
		this.getChildren().add(borderPane);
	}

	private void initEvent() {
		this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent me) -> {

		});
		this.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, (MouseEvent me) -> {

		});
		this.setOnMouseClicked(m -> {
			// setSelected(!isSelected());
			// setRed(!isRed());
		});
	}

	private void initSet() {
		this.setHeadRadius(20);
		this.setHeadSize(20);
	}

	public void setHeadSize(double value) {
		imagePane.setImageSize(value);
	}

	public void setHeadRadius(double value) {
		imagePane.setImageRadius(value);
	}

	public void setHeadImage(Image value) {
		imagePane.setImage(value);
	}

	public Image getHeadImage() {
		return imagePane.getImage();
	}

	public void setName(String value) {
		nameLabel.setText(value);
	}

	public boolean isGray() {
		return imagePane.isGray();
	}

	public void setGray(boolean gray) {
		imagePane.setGray(gray);
	}
}
