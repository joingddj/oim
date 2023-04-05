/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.main;

import java.util.Random;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.icon.IconPane;
import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.main.MainPane;
import com.oimchat.app.fx.view.ui.classics.module.main.MainStage;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class MainStageTest extends Application {

	MainStage stage = new MainStage();
	MainPane mainPane = new MainPane();

	@Override
	public void start(Stage primaryStage) {
		stage.setCenter(mainPane);
		stage.show();
		stage.setBackgroundImageClassPath("/test/images/wallpaper/default.jpg");
		// stage.getRootPane().getStylesheets().add(this.getClass().getResource("/classics/css/main.css").toString());
		initTest();
		initUserList();
	}

	/**
	 * 测试数据
	 */
	private void initTest() {
		/////////////////////////////////////// 用户信息
		Image statusImage = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imonline.png");
		Image headImage = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/head/user/12.jpg");
		mainPane.setHeadImage(headImage);
		mainPane.setStatusImage(statusImage);
		mainPane.setNickname("瓦沙啥");
		mainPane.setText("好多想买的东西，也就只能是想买的东西了。");

		IconPane iconButton = null;

		///////////////////////////////////////////// function
		Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");
		Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");
		Image pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");

		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
		mainPane.addFunctionIcon(iconButton);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools_hover.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools_down.png");

		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
		mainPane.addFunctionIcon(iconButton);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message_highlight.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message_down.png");

		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
		mainPane.addFunctionIcon(iconButton);

//		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/filemanager.png");
//		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/filemanager_hover.png");
//		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/filemanager_down.png");
//
//		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
//		mainPane.addFunctionIcon(iconButton);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/collection_mainpanel.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/collection_mainpanel_hover.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/collection_mainpanel_down.png");

		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
		mainPane.addFunctionIcon(iconButton);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find_hover.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find_down.png");

		iconButton = new IconPane("查找", normalImage, hoverImage, pressedImage);
		mainPane.addFunctionIcon(iconButton);

		///////////////////////////////////////////////////////////////////// app
//		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/appbox_mgr_btn.png");
//		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/appbox_mgr_btn_hover.png");
//		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/appbox_mgr_btn_down.png");
//
//		iconButton = new IconPane(normalImage, hoverImage, pressedImage);
//		mainPane.addRightFunctionIcon(iconButton);
	}

	private void initUserList() {
		Random random = new Random();

		Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_normal.png");
		Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_hover.png");
		Image selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_contacts_selected.png");
		ListRootPane userList = new ListRootPane();
		mainPane.addTab(normalImage, hoverImage, selectedImage, userList);

		ListNodePane[] teamNode = new ListNodePane[5];
		for (int j = 0; j < 5; j++) {
			teamNode[j] = new ListNodePane();
			teamNode[j].setText("我的好友" + j);

			for (int i = 0; i < 5; i++) {
				int index = random.nextInt(20) + 1;
				HeadItem head = new HeadItem();
				Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/head/user/" + index + ".jpg");
				head.setHeadImage(image);
				head.setRemark("女神经" + (j + 1));
				head.setNickname("(哈加额)");
				head.setStatus("[2G]");
				head.setShowText("哈哈哈，又有新闻了");

				Image iconImage = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imonline.png");
				IconPane iconButton = new IconPane(iconImage);
				head.addBusinessIcon("", iconButton);

				head.setOnMouseClicked(m -> {
					if (m.getClickCount() == 2) {
						head.setPulse(!head.isPulse());
					}
				});

				teamNode[j].addItem(head);
			}
			userList.addNode(teamNode[j]);
		}

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_normal.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_hover.png");
		selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_group_selected.png");

		ListRootPane groupRoot = new ListRootPane();
		mainPane.addTab(normalImage, hoverImage, selectedImage, groupRoot);

		ListNodePane[] groupNode = new ListNodePane[5];
		for (int j = 0; j < 5; j++) {
			groupNode[j] = new ListNodePane();
			groupNode[j].setText("我的群" + j);
			groupNode[j].setNumberText("[5]");
			for (int i = 0; i < 5; i++) {

				int index = random.nextInt(20) + 1;
				HeadItem head = new HeadItem();
				Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/head/user/" + index + ".jpg");
				head.setHeadImage(image);

				head.setRemark("女神经" + (j + 1));
				head.setNickname("(哈加额)");
				head.setStatus("[2G]");
				head.setShowText("哈哈哈，又有新闻了");
				Image iconImage = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imonline.png");
				IconPane iconButton = new IconPane(iconImage);
				head.addBusinessIcon("key", iconButton);

				groupNode[j].addItem(head);
			}
			groupRoot.addNode(groupNode[j]);
		}
//
		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_normal.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_hover.png");
		selectedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/tab/icon_last_selected.png");

		ListRootPane lastRoot = new ListRootPane();
		mainPane.addTab(normalImage, hoverImage, selectedImage, lastRoot);

		for (int j = 0; j < 15; j++) {

			HeadItem head = new HeadItem();
			Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/head/user/" + (j + 1) + ".jpg");

			head.setHeadImage(image);

			head.setRemark("女神经" + (j + 1));
			head.setNickname("(哈加额)");
			head.setStatus("[2G]");
			head.setShowText("哈哈哈，又有新闻了");
			Image iconImage = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imonline.png");
			IconPane iconButton = new IconPane(iconImage);
			head.addBusinessIcon("", iconButton);

			lastRoot.addNode(head);
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
