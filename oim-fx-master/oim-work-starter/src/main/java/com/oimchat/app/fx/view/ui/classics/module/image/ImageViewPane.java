package com.oimchat.app.fx.view.ui.classics.module.image;

import com.oimchat.app.fx.base.component.web.WebViewPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * 
 * Description <br>
 * Date 2021-03-05 20:25:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ImageViewPane extends StackPane {

	final WebViewPane webViewPane = new WebViewPane();

	public ImageViewPane() {
		initComponent();
	}

	private void initComponent() {
		this.getChildren().add(webViewPane);
		String classPath = "/general/block/picture/view/index.html";
		String url = this.getClass().getResource(classPath).toExternalForm();
		webViewPane.load(url);
		webViewPane.getWebPage().setEditable(false);
		webViewPane.getWebView().setFocusTraversable(false);
		webViewPane.getWebView().setOnDragDropped(a -> {

		});
		BorderPane pane = new BorderPane();
		HBox box = new HBox();

		box.setPrefHeight(35);
		pane.setTop(box);
		pane.setPickOnBounds(false);
		this.getChildren().add(pane);
	}

	public WebViewPane getWebViewPane() {
		return webViewPane;
	}
}
