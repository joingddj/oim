
package com.oimchat.app.fx.base.stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Description <br>
 * Date 2020-07-02 11:40:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TitleStage extends BaseStage {

	BorderPane rootPane = new BorderPane();

	HBox topBox = new HBox();
	HBox bottomBox = new HBox();
	Button doneButton = new Button();
	Button cancelButton = new Button();
	Label titleLabel = new Label();

	public TitleStage() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setRadius(8);
		super.setCenter(rootPane);

		titleLabel.setText("");
		titleLabel.setFont(Font.font("微软雅黑", 14));
		titleLabel.setStyle("-fx-text-fill: -only-base-theme-text-color");

		topBox.setStyle("-fx-background-color:-fx-accent");
		topBox.setPrefHeight(33);
		topBox.setPadding(new Insets(5, 10, 5, 10));
		topBox.setSpacing(10);
		topBox.getChildren().add(titleLabel);

		cancelButton.setText("取消");
		cancelButton.setPrefWidth(80);

		doneButton.setText("确定");
		doneButton.setPrefWidth(80);

		// bottomBox.setStyle("-fx-background-color:#cac9c9");
		// bottomBox.setStyle("-fx-background-color:-fx-background");
		bottomBox.setStyle("-fx-background-color:-fx-outer-border");
		bottomBox.setAlignment(Pos.BASELINE_RIGHT);
		bottomBox.setPadding(new Insets(5, 10, 5, 10));
		bottomBox.setSpacing(10);
		bottomBox.getChildren().add(doneButton);
		bottomBox.getChildren().add(cancelButton);

		rootPane.setTop(topBox);
		rootPane.setBottom(bottomBox);
	}

	private void iniEvent() {
		cancelButton.setOnAction(a -> {
			TitleStage.this.hide();
		});
		this.titleProperty().addListener((observable, ov, nv) -> {
			titleLabel.setText(nv);
		});
	}

	public void setDoneAction(EventHandler<ActionEvent> value) {
		doneButton.setOnAction(value);
	}

	public void setDoneButtonText(String value) {
		doneButton.setText(value);
	}

	public void setCancelButtonText(String value) {
		cancelButton.setText(value);
	}

	public void showDoneButton(boolean value) {
		doneButton.setVisible(value);
	}

	public void showCancelButton(boolean value) {
		cancelButton.setVisible(value);
	}

	public void addBottomBeforeButton(Button button) {
		bottomBox.getChildren().add(0, button);
	}

	public void addBottomLastButton(Button button) {
		bottomBox.getChildren().add(button);
	}

	@Override
	public void setCenter(Node value) {
		rootPane.setCenter(value);
	}
}
