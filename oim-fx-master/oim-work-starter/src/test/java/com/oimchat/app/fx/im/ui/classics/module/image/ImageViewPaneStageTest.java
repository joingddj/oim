/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.image;

import com.oimchat.app.fx.view.ui.classics.module.image.ImageViewPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class ImageViewPaneStageTest extends Application {

	ImageViewPane pane = new ImageViewPane();

	@Override
	public void start(Stage primaryStage) {
		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(580);
		ts.setHeight(740);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		String id = "76654a60e249-4a2a-af84-d7468d4ea4b7";
		String html = "<img src=\"http://10.32.5.197:7000/v1/picture/image/download?id=824621015366680580\" name=\"chat-image\" id=\"b7e22527326f-4ee3-a2bb-ce750e5f06f9\" /><img src=\"http://10.32.5.197:7000/v1/picture/image/download?id=824621870006452225\" name=\"chat-image\" id=\"76654a60e249-4a2a-af84-d7468d4ea4b7\" /><img src=\"http://10.32.5.197:7000/v1/picture/image/download?id=824621870119698436\" name=\"chat-image\" id=\"876c2af0df58-499c-973a-c5ea09e6e1db\" />";

		String exe = "webBridge.setImageHtml('" + id + "','" + html + "');";
		pane.getWebViewPane().executeScript(exe);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
