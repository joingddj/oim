
package com.oimchat.app.fx.base.component.chat;

import java.net.URL;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.oimchat.client.basic.util.TextFileUtil;
import com.onlyxiahui.app.fx.OnlyPopupOver;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Description <br>
 * Date 2021-03-02 18:19:02<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageWritePane extends StackPane {

	final WebViewPane webViewPane = new WebViewPane();
	final MessageWriteMapper mapper = new MessageWriteMapper(webViewPane);
	final MessageWriteEditHandler editHandler = new MessageWriteEditHandler(webViewPane, mapper);
	private OnlyPopupOver popupOver = new OnlyPopupOver();
	private Label overLabel = new Label();

	public MessageWritePane() {
		initComponent();
	}

	private void initComponent() {
		this.setPadding(new Insets(0, 20, 0, 20));
		this.getChildren().add(webViewPane);
//		String classPath = "/general/block/message/chat/write-index.html";
//		String url = this.getClass().getResource(classPath).toExternalForm();
//		webViewPane.load(url);
		
		
		String name = "write-index.html";
		String classPath = "/general/block/message/chat/";
		String classFliePath = classPath + name;
		URL url = this.getClass().getResource(classFliePath);
		String rootPath = url.toExternalForm().replace(name, "");
		String content = TextFileUtil.getTextByClassPath(classFliePath);
		content = content.replace("src=\"", "src=\"" + rootPath);
		content = content.replace("href=\"", "href=\"" + rootPath);

		webViewPane.getWebEngine().loadContent(content);
		
		webViewPane.getWebPage().setEditable(true);
		webViewPane.getWebView().setFocusTraversable(true);
		webViewPane.getWebView().setOnDragDropped(a -> {

		});
		popupOver.setArrowLocation(OnlyPopupOver.ArrowLocation.BOTTOM_CENTER);

		popupOver.setDetachable(false);
		popupOver.setDetached(false);

		overLabel.setPadding(new Insets(4));
		popupOver.setContentNode(overLabel);
	}

	public WebViewPane getWebViewPane() {
		return webViewPane;
	}

	public MessageWriteMapper getMapper() {
		return mapper;
	}

	public MessageWriteEditHandler getEditHandler() {
		return editHandler;
	}

	public void showPrompt(String text) {
		overLabel.setText(text);
		popupOver.show(webViewPane.getWebView());
		webViewPane.getWebView().requestFocus();
	}
}
