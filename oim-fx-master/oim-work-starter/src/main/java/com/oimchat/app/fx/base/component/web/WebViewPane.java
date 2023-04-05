
package com.oimchat.app.fx.base.component.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.webkit.Accessor;
import com.sun.webkit.WebPage;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * Description <br>
 * Date 2021-02-26 17:47:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class WebViewPane extends StackPane {

	WebView webView = new WebView();
	WebEngine webEngine;
	WebPage webPage;
	boolean isLoad = false;
	List<String> scriptList = new ArrayList<String>();
	Map<String, Object> memberMap = new HashMap<>();

	public WebViewPane() {
		initComponent();
		initContextMenu();
		iniEvent();
	}

	private void initContextMenu() {
	}

	private void initComponent() {
		this.getChildren().add(webView);
		webEngine = webView.getEngine();
		webPage = Accessor.getPageFor(webEngine);
//		webPage.setEditable(false);
		// webPage.setContextMenuEnabled(false);
//		webView.setFocusTraversable(true);
//		webView.setContextMenuEnabled(false);
		webView.setBlendMode(BlendMode.DARKEN);// 透明
		// webView.setBlendMode(BlendMode.LIGHTEN);
//		webView.setOnContextMenuRequested(e -> {
//		});
	}

	private void iniEvent() {
		webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				isLoad = true;
				initWebMember(webEngine);
				if (!scriptList.isEmpty()) {
					int size = scriptList.size();
					for (int i = 0; i < size; i++) {
						String js = scriptList.get(i);
						webEngine.executeScript(js);
					}
					scriptList.clear();
				}
			}
		});
	}

	private void setMember(String name, Object member) {
		if (isLoad) {
			JSObject window = (JSObject) webEngine.executeScript("window");
			if (null != window) {
				window.setMember(name, member);
			}
		}
	}

	private void initWebMember(WebEngine webEngine) {
		if (isLoad && !memberMap.isEmpty()) {
			for (Map.Entry<String, Object> e : memberMap.entrySet()) {
				setMember(e.getKey(), e.getValue());
			}
		}
	}

	public WebView getWebView() {
		return webView;
	}

	public WebEngine getWebEngine() {
		return webEngine;
	}

	public WebPage getWebPage() {
		return webPage;
	}

	public void setWebOnDragDropped(EventHandler<? super DragEvent> value) {
		webView.setOnDragDropped(value);
	}

	public boolean isLoad() {
		return isLoad;
	}

	public String getHtml() {
		String html = webPage.getHtml(webPage.getMainFrame());
		return html;
	}

	public void load(String url) {
		webEngine.load(url);
	}

	public void setScriptMember(String name, Object member) {
		if ((null != name && !name.isEmpty()) && null != member) {
			memberMap.put(name, member);
		}
		if (isLoad) {
			initWebMember(webEngine);
		}
	}

	public Object executeScript(String js) {
		Object o = null;
		if (isLoad) {
			o = webEngine.executeScript(js);
		} else {
			scriptList.add(js);
		}
		return o;
	}

	public Object scriptCall(String objectName, String methodName, Object... args) {
		Object o = null;
		if (isLoad) {
			JSObject window = (JSObject) webEngine.executeScript(objectName);
			if (null != window) {
				o = window.call(methodName, args);
			}
		}
		return o;
	}

	public void setBackgroundColor(Color color) {
		this.setStyle("-fx-background-color:rgba(" + color.getRed() * 255 + "," + color.getGreen() * 255 + "," + color.getBlue() * 255 + ", " + color.getOpacity() + ")");
	}
}
