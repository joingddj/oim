
package com.oimchat.app.fx.base.component.chat;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.oimchat.client.general.common.util.HtmlContentUtil;

/**
 * Description <br>
 * Date 2021-03-02 13:10:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageReadMapper {

	WebViewPane webViewPane;

	public MessageReadMapper(WebViewPane webViewPane) {
		this.webViewPane = webViewPane;
	}

	public void setNativeBridge(MessageReadNativeBridge bridge) {
		webViewPane.setScriptMember("nativeBridge", bridge);
	}

	public void setMessages(String json) {
		webViewPane.scriptCall("window.webBridge", "setMessages", json);
	}

	public void insertBefore(String json) {
		webViewPane.scriptCall("window.webBridge", "insertBefore", json);
	}

	public void insertLast(String json) {
		webViewPane.scriptCall("window.webBridge", "insertLast", json);
	}

	public void clear() {
		webViewPane.scriptCall("window.webBridge", "clear");
	}

	public void scrollToBottom() {
		webViewPane.scriptCall("window.webBridge", "scrollToBottom");
	}

	public void updateScrollIntoView(String viewId) {
		webViewPane.scriptCall("window.webBridge", "updateScrollIntoView", viewId);
	}

	public void setScrollTop(int top) {
		webViewPane.scriptCall("window.webBridge", "setScrollTop", top);
	}

	public void saveScrollTop() {
		webViewPane.scriptCall("window.webBridge", "saveScrollTop");
	}

	public void setListMaxSize(int maxSize) {
		webViewPane.scriptCall("window.webBridge", "setListMaxSize", maxSize);
	}

	public void replaceImageSrc(String id, String src) {
		webViewPane.scriptCall("window.webBridge", "replaceImageSrc", id, src);
	}

	public void removeNode(String id) {
		webViewPane.scriptCall("window.webBridge", "removeNode", id);
	}

	public void insertAt(String json) {
		webViewPane.scriptCall("window.webBridge", "insertAt", json);
	}

	public void insertChatPrompt(String json) {
		webViewPane.scriptCall("window.webBridge", "insertChatPrompt", json);
	}

	public void setTextPrompt(String text) {
		webViewPane.scriptCall("window.webBridge", "setTextPrompt", text);
	}

	public String getImageTags() {
		StringBuilder sb = new StringBuilder();
		// this.updateStyle(createStyleValue().toString());
		Document doc = webViewPane.getWebPage().getDocument(webViewPane.getWebPage().getMainFrame());
		if (doc instanceof HTMLDocument) {
			HTMLDocument htmlDocument = (HTMLDocument) doc;
			NodeList nl = htmlDocument.getElementsByName("chat-image");
			// HTMLCollection ims = htmlDocument.getImages();
			if (null != nl) {
				int size = nl.getLength();
				for (int i = 0; i < size; i++) {
					Node node = nl.item(i);
					if (node instanceof HTMLImageElement) {
						HTMLImageElement ie = (HTMLImageElement) node;
						String id = ie.getId();
						String src = ie.getSrc();
						String alt = ie.getAlt();
						sb.append(HtmlContentUtil.getImageTag(id, "chat-image", "", src, alt));
					}
				}
			}
		}
		return sb.toString();
	}

	public String getFirstMessageKey() {
		String key = null;
		Document doc = webViewPane.getWebPage().getDocument(webViewPane.getWebPage().getMainFrame());
		if (doc instanceof HTMLDocument) {
			HTMLDocument htmlDocument = (HTMLDocument) doc;
			NodeList nl = htmlDocument.getElementsByName("message-content");
			if (null != nl) {
				int size = nl.getLength();
				if (size > 0) {
					Node node = nl.item(0);
					if (node instanceof HTMLElement) {
						HTMLElement ie = (HTMLElement) node;
						key = ie.getId();
					}
				}
			}
		}
		return key;
	}
}
