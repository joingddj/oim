/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.add;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.module.add.AddApplyPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class AddApplyStageTest extends Application {
	AddApplyPane pane = new AddApplyPane();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(460);
		ts.setHeight(355);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		BaseInfoPane ip = pane.getInfoPanel();

		Image image = ImageLoadUtil.getImageByClassPath("/images/head/101_100.gif");
		ip.setHeadImage(image);
		ip.setName("哈加额hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		ip.setNumber("10089");

		pane.addCategory("100", "我的好友");
		pane.addCategory("101", "444好友");
		pane.addCategory("102", "154");

		pane.selectCategory(0);
		ts.setDoneAction(d -> {
			System.out.println(pane.getCategoryId());
		});
		pane.setNewCategoryAction(name -> {
			System.out.println(name);
		});
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
