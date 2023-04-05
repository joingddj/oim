package com.oimchat.app.fx.view.ui.classics.module.setting;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * @author XiaHui
 * @date 2017-12-08 20:34:10
 */
public class MainSettingPane extends StackPane {
	private final BorderPane borderPane = new BorderPane();
	private final TabPane tabPane = new TabPane();

	public MainSettingPane() {
		initComponent();
		initEvent();
	}

	private void initComponent() {
		this.getChildren().add(borderPane);
		borderPane.setCenter(tabPane);
		tabPane.setSide(Side.TOP);
	}

	private void initEvent() {

	}

	public void addTab(String text, Node node) {
		Tab tab = new Tab();
		tab.setText(text);
		tab.setContent(node);
		tab.setClosable(false);
		tabPane.getTabs().add(tab);
	}
}
