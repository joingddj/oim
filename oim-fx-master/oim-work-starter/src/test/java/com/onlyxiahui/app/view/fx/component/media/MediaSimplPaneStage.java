/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.app.view.fx.component.media;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class MediaSimplPaneStage extends Stage {

	BorderPane rootPane = new BorderPane();
	MediaSimplPane pane = new MediaSimplPane();

	Button payButton = new Button("播放");
	Button stopButton = new Button("停止");

	public MediaSimplPaneStage() {
		init();
		initData();
	}

	private void init() {
		this.setScene(new Scene(rootPane, 400, 400));
		this.setTitle("组件测试");

		pane.setMediaSize(320, 240);

		VBox bottomBox = new VBox();
		bottomBox.getChildren().add(payButton);
		bottomBox.getChildren().add(stopButton);

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);

		rootPane.setLeft(pane);

		rootPane.setBottom(bottomBox);

		payButton.setOnAction(a -> {
			pane.play();
		});

		stopButton.setOnAction(a -> {
			pane.stop();
		});
	}

	private void initData() {
		String url = MediaSimplPaneStage.class.getResource("/video/1.mp4").toString();
		pane.setUrl(url);
	}
}
