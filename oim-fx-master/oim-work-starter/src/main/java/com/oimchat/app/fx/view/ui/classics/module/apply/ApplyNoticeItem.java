package com.oimchat.app.fx.view.ui.classics.module.apply;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author XiaHui
 * @date 2017年8月18日 下午3:41:46
 */
public class ApplyNoticeItem extends BorderPane {

	HBox titlePane = new HBox();
	Label timeLabel = new Label();
	Label titleLabel = new Label();

	Label contentLabel = new Label();

	Label imageLabel = new Label();
	ImageView imageView = new ImageView();

	HBox box = new HBox();

	HBox bottomBox = new HBox();
	Button acceptButton = new Button();
	Button refuseButton = new Button();

	public ApplyNoticeItem() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setTop(titlePane);
		this.setCenter(box);
		this.setBottom(bottomBox);
		this.setStyle("-fx-border-color:#e6e6e6;-fx-border-width: 1px;-fx-background-color:rgba(255, 255, 255, 0.9)");

		imageLabel.setGraphic(imageView);

		titlePane.setSpacing(10);
		titlePane.getChildren().add(timeLabel);
		titlePane.getChildren().add(titleLabel);

		contentLabel.setWrapText(true);

		box.getChildren().add(imageLabel);
		box.getChildren().add(contentLabel);
		box.setSpacing(2);
		box.setPadding(new Insets(10, 0, 0, 5));
		// Hyperlink link = new Hyperlink("www.oracle.com");
		// link.setWrapText(true);

		acceptButton.setText("接收");
		acceptButton.setPrefWidth(80);

		refuseButton.setText("拒绝");
		refuseButton.setPrefWidth(80);

		bottomBox.setAlignment(Pos.BASELINE_RIGHT);
		bottomBox.setPadding(new Insets(5, 10, 5, 10));
		bottomBox.setSpacing(10);
		bottomBox.getChildren().add(acceptButton);
		bottomBox.getChildren().add(refuseButton);
	}

	private void iniEvent() {
		// TODO Auto-generated method stub

	}

	public void setImage(Image image) {
		imageView.setImage(image);
	}

	public void setContent(String content) {
		contentLabel.setText(content);
	}

	public void setTime(String time) {
		timeLabel.setText(time);
	}

	public void setTitle(String title) {
		titleLabel.setText(title);
	}

	public void setOnAcceptAction(EventHandler<ActionEvent> value) {
		acceptButton.setOnAction(value);
	}

	public void setOnRefuseAction(EventHandler<ActionEvent> value) {
		refuseButton.setOnAction(value);
	}

	public void setAcceptButton(boolean visible) {
		acceptButton.setVisible(visible);
	}

	public void setRefuseButton(boolean visible) {
		refuseButton.setVisible(visible);
	}
}
