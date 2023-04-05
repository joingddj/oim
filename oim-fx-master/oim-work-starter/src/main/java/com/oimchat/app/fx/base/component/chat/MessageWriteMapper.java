
package com.oimchat.app.fx.base.component.chat;

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.onlyxiahui.app.view.fx.common.util.ColorUtil;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.paint.Color;

/**
 * Description <br>
 * Date 2021-03-03 10:52:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageWriteMapper {

	WebViewPane webViewPane;
	private String fontName = "Microsoft YaHei";
	private int fontSize = 14;
	private Color color = Color.BLACK;
	private boolean bold = false;
	private boolean underline = false;
	private boolean italic = false;

	public MessageWriteMapper(WebViewPane webViewPane) {
		this.webViewPane = webViewPane;
		webViewPane.getWebEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				webViewPane.getWebView().requestFocus();
				// updateStyle();
				// clear();
			}
		});
	}

	public void setNativeBridge(MessageWriteNativeBridge bridge) {
		webViewPane.setScriptMember("nativeBridge", bridge);
	}

	private void updateStyle() {
		// this.updateStyle(createStyleValue().toString());
		Document doc = webViewPane.getWebPage().getDocument(webViewPane.getWebPage().getMainFrame());
		if (doc instanceof HTMLDocument) {
			HTMLDocument htmlDocument = (HTMLDocument) doc;
			HTMLElement htmlDocumentElement = (HTMLElement) htmlDocument.getDocumentElement();
			if (null != htmlDocumentElement) {
				HTMLElement htmlBodyElement = (HTMLElement) htmlDocumentElement.getElementsByTagName("body").item(0);

				if (null != htmlBodyElement) {
					htmlBodyElement.setAttribute("style", createStyleValue().toString());
				}
			}
		}
	}

	private StringBuilder createStyleValue() {
		StringBuilder style = new StringBuilder();

		// style.append("background-color:rgba(0,152,50,0.7);");
		style.append("word-wrap:break-word;");// word-wrap: break-word;
		style.append("font-family:").append(fontName).append(";");
		style.append("font-size:").append(fontSize).append("px;");
		if (underline) {
			style.append("margin-top:0;text-decoration:underline;");
		} else {
			style.append("margin-top:0;");
		}
		if (italic) {
			style.append("font-style:italic;");
		}
		// if (italic) {
		// style.append("font-style:oblique;");
		// }
		if (bold) {
			style.append("font-weight:bold;");
		}
		if (null != color) {
			String c = ColorUtil.colorToHex(color);
			style.append("color:#");
			style.append(c);
			style.append(";");
		}
		return style;
	}

	public String getHtml() {
		String html = webViewPane.getWebPage().getHtml(webViewPane.getWebPage().getMainFrame());
		return html;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		updateStyle();
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		updateStyle();
	}

	public Color getColor() {
		return color;
	}

	public String getWebColor() {
		return ColorUtil.colorToHex(color);
	}

	public void setColor(Color color) {
		this.color = color;
		updateStyle();
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
		updateStyle();
	}

	public boolean isUnderline() {
		return underline;
	}

	public void setUnderline(boolean underline) {
		this.underline = underline;
		updateStyle();
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
		updateStyle();
	}

	public void insertBeforeHtml(String html) {
		webViewPane.scriptCall("window.webBridge", "insertBeforeHtml", html);
	}

	public void insertLastHtml(String html) {
		webViewPane.scriptCall("window.webBridge", "insertLastHtml", html);
	}

	public void replaceElementHtml(String id, String html) {
		webViewPane.scriptCall("window.webBridge", "replaceElementHtml", id, html);
	}

	public void clear() {
		webViewPane.scriptCall("window.webBridge", "clear");
	}

	public void insertAtCursorHtml(String html) {
		webViewPane.scriptCall("window.webBridge", "insertAtCursorHtml", html);
	}

	public void focus() {
		webViewPane.scriptCall("window.webBridge", "focus");
	}

	public boolean isFocus() {
		boolean is = false;
		Object o = webViewPane.scriptCall("window.webBridge", "isFocus");
		if (o instanceof Boolean) {
			is = (Boolean) o;
		}
		return is;
	}

	public boolean hasElement(String id) {
		boolean is = false;
		Object o = webViewPane.scriptCall("window.webBridge", "hasElement", id);
		if (o instanceof Boolean) {
			is = (Boolean) o;
		}
		return is;
	}

	public void replaceImageSrc(String id, String src) {
		webViewPane.scriptCall("window.webBridge", "replaceImageSrc", id, src);
	}

	public void deleteSelection() {
		webViewPane.scriptCall("window.webBridge", "deleteSelection");
	}

	public String getMessageContent() {
		String v = null;
		Object o = webViewPane.scriptCall("window.webBridge", "getMessageContent");
		if (o instanceof String) {
			v = (String) o;
		}
		return v;
	}

	public void updateStyle(String style) {
		webViewPane.scriptCall("window.webBridge", "updateStyle", style);
	}

	public void insertAtUser(String userId, String text) {
		webViewPane.scriptCall("window.webBridge", "insertAtUser", userId, text);
	}

	public void insertAtUserImage(String userId, String text, String img) {
		webViewPane.scriptCall("window.webBridge", "insertAtUserImage", userId, text, img);
	}
}
