/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.notice;

import com.oimchat.app.fx.view.ui.classics.module.notice.NoticeStage;
import com.oimchat.app.fx.view.ui.classics.module.notice.NoticeTextItem;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class NoticeFrameTest extends Application {

	NoticeStage frame = new NoticeStage();
	int size = 10;

	@Override
	public void start(Stage primaryStage) {
		frame.show();

		String text = "马上要开团了点击打开dassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssfsdsdfsdfsdfsdfsdfsdfdsfsdfdfsf";
		for (int i = 0; i < size; i++) {
			NoticeTextItem tni = new NoticeTextItem();
			tni.setTime(DateUtil.getCurrentDateTime());
			tni.setTitle("打团通告");
			tni.setContent(text);
			tni.setOnOpenAction(a -> {
				// HttpUrlUtil.open("www.baidu.com");
			});
			frame.addItem(tni);
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
