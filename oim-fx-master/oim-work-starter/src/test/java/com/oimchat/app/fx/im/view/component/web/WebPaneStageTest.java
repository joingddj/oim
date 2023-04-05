/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.view.component.web;

import java.io.File;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class WebPaneStageTest extends Application {
	WebViewPane pane = new WebViewPane();

	@Override
	public void start(Stage primaryStage) {
		// String url =
		// this.getClass().getResource("/general/block/message/html/show.html").toExternalForm();

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(320);
		ts.setHeight(240);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

//		File file = new File("file/html/show.html");
//		String absolutePath = file.getAbsolutePath();
//		absolutePath = absolutePath.replace("\\", "/");
//		if (absolutePath.startsWith("/")) {
//			pane.load("file:" + absolutePath);
//		} else {
//			pane.load("file:/" + absolutePath);
//		}
//		
		String url = "file:///E:/Workspaces/Own/gitblit/oim-fx/oim-element-fx-view/src/main/resources/general/block/message/chat/read-temp.html";
		String classPath = "/general/block/message/chat/read-temp.html";
		// url = this.getClass().getResource(classPath).toExternalForm();
		pane.load(url);

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
