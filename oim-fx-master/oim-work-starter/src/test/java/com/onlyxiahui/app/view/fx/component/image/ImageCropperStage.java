/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.app.view.fx.component.image;

import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class ImageCropperStage extends Stage {

	BorderPane rootPane = new BorderPane();
	ImageCropperPane imageCropperPane = new ImageCropperPane();

	StackPane imageStackPane = new StackPane();
	ImageView imageView = new ImageView();

	ScrollPane cropperScrollPane = new ScrollPane();

	Button button = new Button("确定");

	public ImageCropperStage() {
		init();
		initData();
	}

	private void init() {
		this.setScene(new Scene(rootPane, 400, 400));
		this.setTitle("组件测试");

		imageView.setPreserveRatio(true);
		imageStackPane.getChildren().add(imageView);

		cropperScrollPane.setBackground(Background.EMPTY);
		cropperScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setContent(imageStackPane);

		VBox bottomBox = new VBox();
		bottomBox.getChildren().add(button);

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);

		rootPane.setLeft(imageCropperPane);
		// rootPane.setCenter(imageCropperPane);

		rootPane.setRight(cropperScrollPane);
		rootPane.setBottom(bottomBox);

		imageCropperPane.setMaxWidth(350);

		button.setOnAction(a -> {
			Image image = imageCropperPane.getImage();
			if (null != image) {
				imageView.setImage(image);
			}
		});
	}

	private void initData() {
		Image image = ImageLoadUtil.getImageByClassPath("/images/cropper/2.jpg");
		imageCropperPane.setImage(image);
	}
}
