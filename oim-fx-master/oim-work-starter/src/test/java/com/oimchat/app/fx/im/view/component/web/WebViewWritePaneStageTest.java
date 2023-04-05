/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.view.component.web;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class WebViewWritePaneStageTest extends Application {
	WriteEditPane pane = new WriteEditPane();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(580);
		ts.setHeight(740);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		ts.setBackgroundImageClassPath("/test/images/wallpaper/default.jpg");
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
