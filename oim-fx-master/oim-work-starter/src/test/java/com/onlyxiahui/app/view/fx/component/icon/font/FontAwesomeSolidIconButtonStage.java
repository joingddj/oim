/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.app.view.fx.component.icon.font;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class FontAwesomeSolidIconButtonStage extends Stage {

	BorderPane rootPane = new BorderPane();
	ScrollPane cropperScrollPane = new ScrollPane();

	VBox box = new VBox();

	Button button = new Button("确定");

	public FontAwesomeSolidIconButtonStage() {
		init();
		initData();
	}

	private void init() {
		this.setScene(new Scene(rootPane, 400, 400));
		this.setTitle("组件测试");

		cropperScrollPane.setBackground(Background.EMPTY);
		cropperScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setContent(box);

		VBox bottomBox = new VBox();
		bottomBox.getChildren().add(button);

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);
		rootPane.setRight(cropperScrollPane);
		rootPane.setBottom(bottomBox);

		FontIconButton i = new FontAwesomeSolidIconButton();
		i.setFontIcon("\uf5cd");
		box.getChildren().add(i);

		i = new FontAwesomeSolidIconButton();
		i.setFontIcon("\uf5cd");
		box.getChildren().add(i);
	}

	private void initData() {
	}
}
