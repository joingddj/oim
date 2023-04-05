/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.find;

import com.oimchat.app.fx.view.ui.classics.module.find.FindPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class FindStageTest extends Application {
	FindPane pane = new FindPane();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(820);
		ts.setHeight(740);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
