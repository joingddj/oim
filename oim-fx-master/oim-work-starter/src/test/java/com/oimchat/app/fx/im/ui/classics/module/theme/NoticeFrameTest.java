/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.theme;

import com.oimchat.app.fx.view.ui.classics.module.theme.ThemeStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class NoticeFrameTest extends Application {

	ThemeStage frame = new ThemeStage();
	int size = 10;

	@Override
	public void start(Stage primaryStage) {
		frame.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
