/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.fx.im.view.component.head;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.oimchat.app.fx.base.component.choose.ChooseGroup;
import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.base.component.icon.IconPane;
import com.oimchat.app.fx.base.component.list.ItemListCell;
import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.oimchat.client.platform.fx.view.common.box.UserStatusImageBox;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author XiaHui
 */
public class HeadCloseItemPaneFrame extends ClassicsStage {

	BorderPane rootPane = new BorderPane();
	ListRootPane componentBox = new ListRootPane();
	ScrollPane sp = new ScrollPane();

	public HeadCloseItemPaneFrame() {
		init();
	}

	private void init() {

		this.setCenter(rootPane);
		this.setTitle("组件测试");
		this.setWidth(380);
		this.setHeight(600);
		this.setRadius(10);
		// this.setBackgroundColor(Color.rgb(192, 120, 100));

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);
		ListView<Node> list = new ListView<>();
		list.setCellFactory((ListView<Node> l) -> new ItemListCell<>());
		sp.setContent(list);

		rootPane.setCenter(componentBox);

		Image image = ImageLoadUtil.getImageByClassPath("/images/head/101_100.gif");
		ChooseGroup chooseGroup = new ChooseGroup();

		new Thread() {
			public void run() {
				Random r = new Random();

				List<Node> nodes = new ArrayList<>();
				for (int j = 0; j < 20; j++) {
					ListNodePane np = new ListNodePane();
					np.setText("分组" + j);

					for (int i = 0; i < 50; i++) {

						boolean gray = i % 2 == 0;

						String text = "好傻水水水水？【】[图片]";

						String name = "恢复大师";

						HeadCloseItem head = new HeadCloseItem();
						head.setHeadImage(image);
						head.setRemark(name);
						head.setNickname("(Nickname)");
						head.setShowText(text);
						head.setTime("12:20");
						head.setGray(gray);
						head.setPulse(true);

						// head.setChooseGroup(chooseGroup);

						head.setOnMouseClicked(m -> {

							boolean red = !head.isRed();
							String redText = red ? "999" : "";
							head.setRed(red);
							head.setRedText(redText);
							head.setPulse(!head.isPulse());
							chooseGroup.selectedChoose(head);
						});

						head.setOnMouseClicked(m -> {

							boolean red = !head.isRed();
							String redText = red ? "888" : "";
							head.setRed(red);
							head.setRedText(redText);
							head.setPulse(!head.isPulse());
							chooseGroup.selectedChoose(head);
						});

						String status = (r.nextInt(7) + 1) + "";

						Image iconImage = UserStatusImageBox.getContactStatusImageIcon(status);

						IconPane iconButton = head.getBusinessIcon("statusLabel");
						if (null == iconButton) {// 状态图标显示组件
							iconButton = new IconPane(iconImage);
							head.addBusinessIcon("statusLabel", iconButton);
						}
						iconButton.setNormalImage(iconImage);

						np.addItem(head);
//						Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
//								
//							}
//						});
					}
					nodes.add(np);
				}

//				for (Node node : nodes) {
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							list.getItems().add(node);
//						}
//					});
//				}

				for (Node node : nodes) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// list.getItems().addAll(nodes);

							componentBox.addNode(node);
						}
					});
				}
			}
		}.start();

	}
}
