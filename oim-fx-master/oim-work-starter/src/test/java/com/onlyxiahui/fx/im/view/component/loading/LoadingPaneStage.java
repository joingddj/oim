/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.fx.im.view.component.loading;

import com.oimchat.app.fx.base.component.loading.LoadingPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author XiaHui
 */
public class LoadingPaneStage extends ClassicsStage {

	BorderPane rootPane = new BorderPane();
	LoadingPane loadingPane = new LoadingPane();

	public LoadingPaneStage() {
		init();
	}

	private void init() {
		this.setCenter(rootPane);
		this.setTitle("组件测试");
		this.setWidth(380);
		this.setHeight(600);
		this.setRadius(10);

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);

		rootPane.setTop(topBox);
		rootPane.setCenter(loadingPane);

		Image image = ImageLoadUtil.getImageByClassPath("/images/head/101_100.gif");
		BorderPane imagePane = new BorderPane();
		imagePane.setCenter(new ImageView(image));

		loadingPane.getChildren().add(imagePane);

		HBox bBox = new HBox();

		Button b = new Button("主界面");
		bBox.getChildren().add(b);
		b.setOnAction((a) -> {
			loadingPane.showNode();
		});

		b = new Button("加载");
		bBox.getChildren().add(b);
		b.setOnAction((a) -> {
			loadingPane.showLoding();
		});

		b = new Button("成功");
		bBox.getChildren().add(b);
		b.setOnAction((a) -> {
			loadingPane.showSuccess();
		});

		b = new Button("失败");
		bBox.getChildren().add(b);
		b.setOnAction((a) -> {
			loadingPane.showFail();
		});
		rootPane.setBottom(bBox);
	}
}
