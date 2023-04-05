/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.view.ui.classics.module.avatar;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

/**
 *
 * @author Only
 */
public class AvatarUploadStage extends ClassicsStage {
	BorderPane rootPane = new BorderPane();
	AvatarUploadPane pane = new AvatarUploadPane();
	Alert alert;
	HBox topBox = new HBox();
	HBox bottomBox = new HBox();
	Button doneButton = new Button();
	Button cancelButton = new Button();

	Label titleLabel = new Label();

	HBox buttonBox = new HBox();

	public AvatarUploadStage() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setWidth(390);
		this.setHeight(518);
		this.setResizable(false);
		this.setTitlePaneStyle(2);
		this.setRadius(5);
		this.setCenter(rootPane);
		this.setTitle("更换头像");

		buttonBox.setPadding(new Insets(12, 10, 12, 14));
		buttonBox.setSpacing(10);

		titleLabel.setText("更换头像");
		titleLabel.setFont(Font.font("微软雅黑", 14));
		titleLabel.setStyle("-fx-text-fill:rgba(255, 255, 255, 1)");

		topBox.setStyle("-fx-background-color:#2cb1e0");
		topBox.setPrefHeight(30);
		topBox.setPadding(new Insets(5, 10, 5, 10));
		topBox.setSpacing(10);
		topBox.getChildren().add(titleLabel);

		alert = new Alert(AlertType.CONFIRMATION, "");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(this);
		alert.getDialogPane().setContentText("确定选择");
		alert.getDialogPane().setHeaderText(null);

		cancelButton.setText("取消");
		cancelButton.setPrefWidth(80);

		doneButton.setText("确定");
		doneButton.setPrefWidth(80);

		bottomBox.setStyle("-fx-background-color:#c9e1e9");
		bottomBox.setAlignment(Pos.BASELINE_RIGHT);
		bottomBox.setPadding(new Insets(5, 10, 5, 10));
		bottomBox.setSpacing(10);
		bottomBox.getChildren().add(doneButton);
		bottomBox.getChildren().add(cancelButton);

		rootPane.setTop(topBox);
		rootPane.setCenter(pane);
		rootPane.setBottom(bottomBox);

	}

	private void iniEvent() {
		cancelButton.setOnAction(a -> {
			AvatarUploadStage.this.hide();
		});
	}

	public boolean verify() {
		return pane.verify();
	}

	public Image getImage() {
		return pane.getImage();
	}

	public void openImage() {
		pane.openImage(this);
	}

	public void setDoneAction(EventHandler<ActionEvent> value) {
		doneButton.setOnAction(value);
	}

	public void select(ValueEvent<Boolean> a) {
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
			a.value(true);
		});
	}

	public void setTitleText(String value) {
		this.setTitle(value);
		titleLabel.setText(value);
	}
}
