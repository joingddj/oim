/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.view.ui.classics.module.image;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Only
 */
public class ImagePreviewStage extends ClassicsStage {

	BorderPane rootPane = new BorderPane();
	ImagePreviewPane previewPane = new ImagePreviewPane();

	HBox topBox = new HBox();
	HBox bottomBox = new HBox();
	Button button = new Button();

	public ImagePreviewStage() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setWidth(380);
		this.setHeight(520);
		// this.setResizable(false);
		// this.setTitlePaneStyle(2);
		this.setRadius(5);
		this.setCenter(rootPane);
		this.setTitle("");

		previewPane.setStyle("-fx-background-color:#e8f0f3");

		button.setText("关闭");
		button.setPrefWidth(80);

		bottomBox.setStyle("-fx-background-color:#c9e1e9");
		bottomBox.setAlignment(Pos.BASELINE_RIGHT);
		bottomBox.setPadding(new Insets(5, 10, 5, 10));
		bottomBox.getChildren().add(button);

		rootPane.setCenter(previewPane);
		rootPane.setBottom(bottomBox);
	}

	private void iniEvent() {
		button.setOnAction(a -> {
			ImagePreviewStage.this.hide();
		});
	}

	public void setImage(Image value) {
		previewPane.setImage(value);
	}
}
