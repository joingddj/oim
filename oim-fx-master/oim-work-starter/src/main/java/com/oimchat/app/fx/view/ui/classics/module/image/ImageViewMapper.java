
package com.oimchat.app.fx.view.ui.classics.module.image;

import java.util.List;

import com.oimchat.app.fx.base.component.web.WebViewPane;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;

/**
 * Description <br>
 * Date 2021-03-03 10:52:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageViewMapper {

	WebViewPane webViewPane;

	public ImageViewMapper(WebViewPane webViewPane) {
		this.webViewPane = webViewPane;
		webViewPane.getWebEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
			}
		});
	}

	public void setImageUrls(String url, List<String> urls) {
		webViewPane.scriptCall("window.webBridge", "setImageUrls", url, urls);
	}
}
