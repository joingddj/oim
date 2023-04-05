/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.chat;

import java.util.ArrayList;

import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.element.icon.ToolIconButton;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatListStage;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.platform.fx.view.common.build.IconButtonBuilder;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeRegularIconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.MaterialDesignIconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.MaterialIconButton;
import com.sun.javafx.webkit.InputMethodClientImpl;
import com.sun.webkit.event.WCInputMethodEvent;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodTextRun;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class ChatListStageViewTest extends Application {

	ChatListStage stage = new ChatListStage();
	ChatPane cp = new ChatPane();

	@Override
	public void start(Stage primaryStage) {
		stage.show();
		stage.getRootPane().getStylesheets().add(this.getClass().getResource("/general/view/theme/classics/css/chat.css").toString());
		// stage.getRootPane().getStylesheets().add(this.getClass().getResource("/multiple/css/main.css").toString());
		initList();
		stage.setChatPane(cp);

		cp.setName("脑门的");
		cp.setText("ddddddddddddsds");

		cp.addTopRightTool(new Button("X"));

		// stage.setBackgroundColor(Color.BLUE);

		FontIconButton aButton = IconButtonBuilder.awesomeSolidIconButton("\uf582");
		cp.addMiddleLeftTool(aButton);

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf0c4");// cut
		cp.addMiddleLeftTool(aButton);

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf03e");
		cp.addMiddleLeftTool(aButton); // img

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf07b");
		cp.addMiddleLeftTool(aButton); // file

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf0f3");
		cp.addMiddleLeftTool(aButton); // vibrate

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf03d");
		cp.addMiddleLeftTool(aButton); // video

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf130");// voice
		cp.addMiddleLeftTool(aButton);

		aButton = IconButtonBuilder.awesomeSolidIconButton("\uf1da");// history
		cp.addMiddleLeftTool(aButton);

		MaterialDesignIconButton mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰙃");// f
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰆐");// i
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰋫");// c
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󱉆");// file
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰕦");// vibrate
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰯜");// video
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		mdButton = new MaterialDesignIconButton();
		mdButton.setFontIcon("󰍬");// a
		mdButton.setIconFontSize(20);
		cp.addMiddleLeftTool(mdButton);

		MaterialIconButton mi = IconButtonBuilder.materialIconButton("\ue87c");// f
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue14e");// cut
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue40b");// img
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue62d");// vibrate
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue2c8");// file
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue04b");// video
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue31d");// voice
		cp.addMiddleLeftTool(mi);

		mi = IconButtonBuilder.materialIconButton("\ue889");// history
		cp.addMiddleLeftTool(mi);

		mi = new MaterialIconButton();
		mi.setIconFontSize(23.5);
		mi.setPadding(new Insets(0, 0, 0, 0));
		mi.setOpacity(0.6);
		mi.setFontIcon("\ue889");// history

		cp.addMiddleRightTool(mi);
	}

	public void initList() {
		Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/head/user/10.jpg");
		for (int i = 0; i < 5; i++) {
			String text = "好傻水水水水？【】[图片]";

			String name = "恢复大师" + i;

			HeadCloseItem head = new HeadCloseItem();
			head.setHeadImage(image);
			head.setRemark(name);
			head.setNickname(name);
			head.setShowText(text);
			head.setTime("12:20");
			String key = i + "";
			head.setOnMouseClicked(m -> {

				boolean red = !head.isRed();
				String redText = red ? "5" : "";
				head.setRed(red);
				head.setRedText(redText);
			});

			head.setOnCloseAction(a -> {
				stage.remove(key);
			});

			head.selectedProperty().addListener((o, ov, nv) -> {
				cp.setText(text);
				cp.setName(name);
			});

			head.setKey(key);

			stage.addItem(head);
			cp.getAreaPane()
					.getWritePane().getEditHandler().setInputTextConverter((s) -> {
						return s;
					});

//			cp.getAreaPane()
//					.getWritePane()
//					.getWebViewPane()
//					.getWebView()
////					.addEventFilter(InputMethodEvent.ANY, (e) -> {
//					.setOnInputMethodTextChanged(e -> {
//						e.consume();
//						int size = e.getComposed().size();
//						String t = e.getCommitted();
//						if (size <= 0) {
//							e.consume();
//							// ObservableList<InputMethodTextRun> composed =
//							// FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(new
//							// ArrayList<>()));
//							InputMethodEvent ie = new InputMethodEvent(
//									e.getSource(),
//									e.getTarget(),
//									e.getEventType(),
//									e.getComposed(),
//									t,
//									e.getCaretPosition());
//
//							WCInputMethodEvent imEvent = InputMethodClientImpl.convertToWCInputMethodEvent(ie);
//							cp.getAreaPane()
//									.getWritePane()
//									.getWebViewPane()
//									.getWebPage().dispatchInputMethodEvent(imEvent);
//							ie.consume();
//							System.out.println("555:" + t);
//							return;
//						} else {
////							int index = size - 1;
////							InputMethodTextRun run = e.getComposed().get(index);
////							System.out.println(run.getText());
////							e.consume();
////							InputMethodEvent ie = new InputMethodEvent(
////									e.getSource(),
////									e.getTarget(),
////									e.getEventType(),
////									e.getComposed(),
////									e.getCommitted(),
////									e.getCaretPosition());
//
//							WCInputMethodEvent imEvent = InputMethodClientImpl.convertToWCInputMethodEvent(e);
//							cp.getAreaPane()
//									.getWritePane()
//									.getWebViewPane()
//									.getWebPage().dispatchInputMethodEvent(imEvent);
//							// ie.consume();
//						}
//					});
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
