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
import com.oimchat.app.fx.base.component.chat.MessageReadNativeBridge;
import com.oimchat.app.fx.base.component.chat.MessageReadPane;
import com.oimchat.app.fx.im.view.component.chat.MessageReadNativeBridgeImpl;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.onlyxiahui.common.data.im.content.Content;
import com.onlyxiahui.common.data.im.content.Item;
import com.onlyxiahui.common.data.im.content.Section;
import com.onlyxiahui.common.data.im.content.item.AtValue;
import com.onlyxiahui.common.data.im.content.item.FileValue;
import com.onlyxiahui.common.data.im.content.item.ImageValue;
import com.onlyxiahui.common.data.im.message.MessageContentWrap;
import com.onlyxiahui.common.data.im.message.MessagePromptWrap;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class WebViewReadPaneStageTest extends Application {
	MessageReadPane pane = new MessageReadPane();
	MessageReadNativeBridge out = new MessageReadNativeBridgeImpl();
	File file = new File("file/chat/file.txt");
	File audio = new File("file/chat/audio.mp3");
	File video = new File("file/chat/video.mp4");

	@Override
	public void start(Stage primaryStage) {

		String classPath = "/general/block/message/chat/read-demo.html";
		String url = this.getClass().getResource(classPath).toExternalForm();
		// File htmlFile = new
		// File("E:/Workspaces/Own/gitblit/oim-fx/oim-element-fx-view/src/main/resources/general/block/message/chat/read-temp.html");

		// url =htmlFile.getAbsolutePath();

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(580);
		ts.setHeight(740);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		ts.setBackgroundImageClassPath("/test/images/wallpaper/default.jpg");
		pane.getWebViewPane().setBackgroundColor(Color.rgb(255, 255, 255, 0.9));
		pane.getWebViewPane().getWebView().setOnMouseClicked(m -> {
			m.consume();
		});
		url = this.getClass().getResource(classPath).toExternalForm();

		pane.getWebViewPane().load(url);
		Button b = new Button("刷新");
		ts.addBottomBeforeButton(b);
		b.setOnAction(a -> {
			String u = this.getClass().getResource(classPath).toExternalForm();
			pane.getWebViewPane().load(u);
		});
		pane.getWebViewPane().setScriptMember("nativeBridge", out);
		pane.getWebViewPane().setScriptMember("nativeMenu", out);

		Button all = new Button("all");
		ts.addBottomBeforeButton(all);
		all.setOnAction(a -> {
			String imageUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";
			String avatarUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";
			List<MessageContentWrap> items = new ArrayList<>();

			Item item = new Item();
			Section section = new Section();

			MessageContentWrap data = new MessageContentWrap();

			for (int i = 0; i < 10; i++) {
				section = new Section();

				item = new Item();
				item.setType("text");
				item.setValue("你好啊！" + i);
				section.getItems().add(item);

				ImageValue iv = new ImageValue();
				iv.setUrl(imageUrl);

				item = new Item();
				item.setType("image");
				item.setValue(iv);
				section.getItems().add(item);

				AtValue at = new AtValue();
				at.setText("@赵四");
				at.setUserId("sss");

				item = new Item();
				item.setType("at");
				item.setValue(at);
				section.getItems().add(item);

				FileValue fv = new FileValue();
				fv.setExtension("txt");
				fv.setId("100");
				fv.setName(file.getName());
				fv.setSize(file.length());
				fv.setSizeText("1KB");
				fv.setUrl(getPath(file.getAbsolutePath()));

				item = new Item();
				item.setType("file");
				item.setValue(fv);
				section.getItems().add(item);

				FileValue vv = new FileValue();
				vv.setExtension("txt");
				vv.setId("100");
				vv.setName(video.getName());
				vv.setSize(video.length());
				vv.setSizeText("1KB");
				vv.setUrl(getPath(video.getAbsolutePath()));

				item = new Item();
				item.setType("video");
				item.setValue(vv);
				section.getItems().add(item);

				FileValue av = new FileValue();
				av.setExtension("txt");
				av.setId("100");
				av.setName(audio.getName());
				av.setSize(audio.length());
				av.setSizeText("1KB");
				av.setUrl(getPath(audio.getAbsolutePath()));

				item = new Item();
				item.setType("audio");
				item.setValue(av);
				section.getItems().add(item);

				data = new MessageContentWrap();
				data.setAvatar(avatarUrl);
				data.setName("小龙女");
				data.setTimeText("2020-01-01");
				data.setTimeVisible(true);
				data.setNameVisible(true);
				data.setOwn((i % 2 == 0));
				data.setStatus((i % 2 == 0) ? 2 : 1);
				data.setReceive((i % 2 == 0));
				data.setContent(new Content());
				data.getContent().getSections().add(section);

				items.add(data);
			}

			Object o = pane.getWebViewPane().scriptCall("window.webBridge", "setMessages", JSONArray.toJSONString(items));
			System.out.println(o);
		});

		ts.setDoneAction(a -> {
			add();
		});
		Button s = new Button("获取选中内容");
		ts.addBottomBeforeButton(s);
		s.setOnAction(a -> {
			Object selection = pane.getWebViewPane().executeScript("window.getSelection().toString()");
			System.out.println(selection);
		});

		Button p = new Button("提示");
		ts.addBottomBeforeButton(p);
		p.setOnAction(a -> {
			prompt();
		});

	}

	public String getPath(String absolutePath) {
		absolutePath = absolutePath.replace("\\", "/");
		String path = absolutePath;
		if (absolutePath.startsWith("/")) {
			path = ("file:" + absolutePath);
		} else {
			path = ("file:/" + absolutePath);
		}
		return path;
	}

	public void add() {
		String imageUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";
		String avatarUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";

		Item item = new Item();
		Section section = new Section();

		item = new Item();
		item.setType("text");
		item.setValue("你好啊！" + new Random().nextInt(555));
		section.getItems().add(item);

		ImageValue iv = new ImageValue();
		iv.setUrl(imageUrl);

		item = new Item();
		item.setType("image");
		item.setValue(iv);
		section.getItems().add(item);

		AtValue at = new AtValue();
		at.setText("@赵四");
		at.setUserId("sss");

		item = new Item();
		item.setType("at");
		item.setValue(at);
		section.getItems().add(item);

		FileValue fv = new FileValue();
		fv.setExtension("txt");
		fv.setId("100");
		fv.setName(file.getName());
		fv.setSize(file.length());
		fv.setSizeText("1KB");
		fv.setUrl(getPath(file.getAbsolutePath()));

		item = new Item();
		item.setType("file");
		item.setValue(fv);
		section.getItems().add(item);

		FileValue vv = new FileValue();
		vv.setExtension("txt");
		vv.setId("100");
		vv.setName(video.getName());
		vv.setSize(video.length());
		vv.setSizeText("1KB");
		vv.setUrl(getPath(video.getAbsolutePath()));

		item = new Item();
		item.setType("video");
		item.setValue(vv);
		section.getItems().add(item);

		FileValue av = new FileValue();
		av.setExtension("txt");
		av.setId("100");
		av.setName(audio.getName());
		av.setSize(audio.length());
		av.setSizeText("1KB");
		av.setUrl(getPath(audio.getAbsolutePath()));

		item = new Item();
		item.setType("audio");
		item.setValue(av);
		section.getItems().add(item);

		MessageContentWrap data = new MessageContentWrap();

		data.setKey(System.currentTimeMillis() + "");
		data.setAvatar(avatarUrl);
		data.setName("小龙女");
		data.setNameVisible(true);
		data.setType(0);

		data.setTimeText("2020-01-01");
		data.setTimeVisible(true);
		data.setOwn((new Random().nextInt(3) == 0));
		data.setStatus(new Random().nextInt(3));
		data.setReceive((new Random().nextInt(3) == 0));
		data.setContent(new Content());
		data.getContent().getSections().add(section);

		//

		Object o = null;

		// pane.executeScript("window.webBridge.insertLast('" +
		// JSONArray.toJSONString(data) + "');");
		o = pane.getWebViewPane().scriptCall("window.webBridge", "insertLast",
				JSONArray.toJSONString(data));
		System.out.println(o);
	}

	public void prompt() {
		MessagePromptWrap data = new MessagePromptWrap();

		data.setKey(System.currentTimeMillis() + "");
		data.setType(1);

		data.setTimeText("2020-01-01");
		data.setTimeVisible(true);

		data.setText("你已经撤回消息");

		Object o = null;

		// pane.executeScript("window.webBridge.insertLast('" +
		// JSONArray.toJSONString(data) + "');");
		o = pane.getWebViewPane().scriptCall("window.webBridge", "insertLast",
				JSONArray.toJSONString(data));
		System.out.println(o);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
