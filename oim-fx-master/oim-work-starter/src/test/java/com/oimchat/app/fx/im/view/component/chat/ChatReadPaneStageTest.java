/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.view.component.chat;

import java.net.URL;
import java.util.Random;

import javax.swing.event.HyperlinkEvent;

import org.codefx.libfx.control.webview.WebViewHyperlinkListener;
import org.codefx.libfx.control.webview.WebViews;

import com.alibaba.fastjson.JSONObject;
import com.oimchat.app.awt.desktop.util.LinkOpenUtil;
import com.oimchat.app.fx.base.component.chat.MessageAreaPane;
import com.oimchat.app.fx.base.component.chat.MessageReadMapper;
import com.oimchat.app.fx.base.component.chat.MessageReadNativeBridge;
import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.basic.util.TextFileUtil;
import com.onlyxiahui.common.data.im.content.Content;
import com.onlyxiahui.common.data.im.message.MessageContentWrap;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class ChatReadPaneStageTest extends Application {
	MessageAreaPane pane = new MessageAreaPane();
	MessageWriteNativeBridgeImpl wn = new MessageWriteNativeBridgeImpl(pane.getWritePane());
	MessageReadNativeBridge out = new MessageReadNativeBridgeImpl();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(580);
		ts.setHeight(740);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

		ts.setBackgroundImageClassPath("/test/images/wallpaper/default.jpg");

		MessageWriteMapper writeMapper = pane.getWritePane().getMapper();
		writeMapper.setNativeBridge(wn);

		String name = "read-demo.html";
		String classPath = "/general/block/message/chat/";
		String classFliePath = classPath + name;
		URL url = this.getClass().getResource(classFliePath);
		String rootPath = url.toExternalForm().replace(name, "");
		String content = TextFileUtil.getTextByClassPath(classFliePath);
		content = content.replace("src=\"", "src=\"" + rootPath);
		content = content.replace("href=\"", "href=\"" + rootPath);
		// pane.getReadPane().getWebViewPane().load(url);
		System.out.println(content);
		pane.getReadPane().getWebViewPane().getWebEngine().loadContent(content);

		MessageReadMapper readMapper = pane.getReadPane().getMapper();
		readMapper.setNativeBridge(out);

		ts.setDoneAction(a -> {
			send();
		});

		Button b = new Button("html");
		ts.addBottomBeforeButton(b);
		b.setOnAction(a -> {
			System.out.println(pane.getWritePane().getMapper().getHtml());
		});

		WebViews.addHyperlinkListener(pane.getReadPane().getWebViewPane().getWebView(), new WebViewHyperlinkListener() {

			@Override
			public boolean hyperlinkUpdate(HyperlinkEvent event) {
				System.out.println(event.getURL());
				LinkOpenUtil.open(event.getURL().toString());
				return true;
			}
		}, javax.swing.event.HyperlinkEvent.EventType.ACTIVATED);
	}

	private void send() {
		String avatarUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";

		MessageWriteMapper writeMapper = pane.getWritePane().getMapper();

		MessageReadMapper readMapper = pane.getReadPane().getMapper();
		String messageContent = writeMapper.getMessageContent();

		if (null != messageContent && !messageContent.isEmpty()) {
			MessageContentWrap data = new MessageContentWrap();

			data.setKey(System.currentTimeMillis() + "");
			data.setAvatar(avatarUrl);
			data.setName("小龙女");
			data.setUserId("kkkyyy");
			data.setNameVisible(true);
			data.setType(0);

			data.setTimeText("2020-01-01");
			data.setTimeVisible(true);
			data.setOwn((new Random().nextInt(3) == 0));
			data.setStatus(new Random().nextInt(3));
			data.setReceive((new Random().nextInt(3) == 0));
			System.out.println("messageContent->:" + messageContent);
			Content content = JSONObject.parseObject(messageContent, Content.class);
			data.setContent(content);
			String json = JSONObject.toJSONString(data);
			System.out.println("message->:" + json);
			readMapper.insertLast(json);
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
