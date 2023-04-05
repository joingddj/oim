/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.app.view.fx.component.tab;

import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class TabPaneIconStage extends Stage {

	BorderPane rootPane = new BorderPane();

	BorderPane nodePane = new BorderPane();

	public TabPaneIconStage() {
		init();
		initData();
	}

	private void init() {
		this.setScene(new Scene(rootPane, 400, 400));
		this.setTitle("组件测试");

		VBox bottomBox = new VBox();
		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);
		rootPane.setBottom(bottomBox);
		rootPane.setCenter(nodePane);

		TabPane tabPane = new TabPane();
		FontIconText i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add(i, new VBox());

		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add("哈哈", i, new VBox());

		tabPane.setStyle("-fx-background-color:rgba(194, 194, 173, 1)");
		tabPane.setSide(Side.TOP);
		nodePane.setTop(tabPane);

		tabPane = new TabPane();
		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add(i, new VBox());

		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add("哈哈", i, new VBox());

		tabPane.setStyle("-fx-background-color:rgba(194, 194, 173, 1)");
		tabPane.setSide(Side.RIGHT);
		nodePane.setRight(tabPane);

		tabPane = new TabPane();
		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add(i, new VBox());

		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add("哈哈", i, new VBox());

		tabPane.setStyle("-fx-background-color:rgba(194, 194, 173, 1)");
		tabPane.setSide(Side.BOTTOM);
		nodePane.setBottom(tabPane);

		tabPane = new TabPane();
		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add(i, new VBox());

		i = new FontAwesomeSolidIconText();
		i.setFontIcon("\uf5cd");
		i.setIconFontSize(28.2D);
		tabPane.add("哈哈", i, new VBox());

		tabPane.setStyle("-fx-background-color:rgba(194, 194, 173, 1)");
		tabPane.setSide(Side.LEFT);
		nodePane.setLeft(tabPane);
	}

	private void initData() {
	}
}
