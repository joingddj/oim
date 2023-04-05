
package com.oimchat.app.fx.base.component.chat;

import java.net.URL;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.oimchat.client.basic.util.TextFileUtil;

import javafx.scene.layout.StackPane;

/**
 * Description <br>
 * Date 2021-03-02 18:04:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageReadPane extends StackPane {

	final WebViewPane webViewPane = new WebViewPane();
	final MessageReadMapper mapper = new MessageReadMapper(webViewPane);

	public MessageReadPane() {
		initComponent();
	}

	private void initComponent() {
		this.getChildren().add(webViewPane);
		// String classPath = "/general/block/message/chat/read-index.html";
		// String url = this.getClass().getResource(classPath).toExternalForm();
		// webViewPane.load(url);

		String name = "read-index.html";
		String classPath = "/general/block/message/chat/";
		String classFliePath = classPath + name;
		URL url = this.getClass().getResource(classFliePath);
		String rootPath = url.toExternalForm().replace(name, "");
		String content = TextFileUtil.getTextByClassPath(classFliePath);
		content = content.replace("src=\"", "src=\"" + rootPath);
		content = content.replace("href=\"", "href=\"" + rootPath);

		webViewPane.getWebEngine().loadContent(content);

		webViewPane.getWebPage().setEditable(false);
		// webPage.setContextMenuEnabled(false);
		webViewPane.getWebView().setFocusTraversable(false);
		webViewPane.getWebView().setContextMenuEnabled(false);
		webViewPane.getWebView().setOnContextMenuRequested(e -> {

		});
	}

	public WebViewPane getWebViewPane() {
		return webViewPane;
	}

	public MessageReadMapper getMapper() {
		return mapper;
	}
}
