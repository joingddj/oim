/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.view.component.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.onlyxiahui.common.data.im.content.Content;
import com.onlyxiahui.common.data.im.content.Item;
import com.onlyxiahui.common.data.im.content.Section;
import com.onlyxiahui.common.data.im.content.item.ImageValue;
import com.onlyxiahui.common.data.im.message.MessageContentWrap;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class WebViewPaneStageTest extends Application {
	WebViewPane pane = new WebViewPane();
	NativeBridge out = new NativeBridge();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(320);
		ts.setHeight(240);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		String url = this.getClass().getResource("/general/block/message/chat/read-temp.html").toExternalForm();
//		String url = "file:///E:/Workspaces/Own/gitblit/oim-fx/oim-element-fx-view/src/main/resources/general/block/message/chat/read-temp.html";
		pane.load(url);
		ts.setDoneAction(a -> {
			pane.load(url);
		});
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
