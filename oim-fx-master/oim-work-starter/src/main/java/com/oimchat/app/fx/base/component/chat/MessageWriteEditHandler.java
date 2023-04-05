
package com.oimchat.app.fx.base.component.chat;

import com.oimchat.app.fx.base.component.web.WebViewPane;
import com.onlyxiahui.app.view.fx.common.data.DataConverter;
import com.sun.javafx.webkit.InputMethodClientImpl;
import com.sun.webkit.event.WCInputMethodEvent;

import javafx.scene.input.InputMethodEvent;

/**
 * Description <br>
 * Date 2021-03-03 10:52:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageWriteEditHandler {

	WebViewPane webViewPane;
	MessageWriteMapper mapper;
	MessageWriteEditBridge editBridge;

	DataConverter<String, String> inputTextConverter;

	public MessageWriteEditHandler(WebViewPane webViewPane, MessageWriteMapper mapper) {
		this.webViewPane = webViewPane;
		this.mapper = mapper;
		editBridge = new MessageWriteEditBridgeImpl(mapper);
		init();
	}

	public void init() {
		webViewPane.setScriptMember("editBridge", editBridge);
		webViewPane.getWebView().addEventFilter(InputMethodEvent.ANY, (e) -> {
			processInputMethodEvent(e);
		});
	}

	public DataConverter<String, String> getInputTextConverter() {
		return inputTextConverter;
	}

	public void setInputTextConverter(DataConverter<String, String> inputTextConverter) {
		this.inputTextConverter = inputTextConverter;
		editBridge.setInputTextConverter(inputTextConverter);
	}

	private void processInputMethodEvent(InputMethodEvent e) {
		e.consume();
		int size = e.getComposed().size();
		String text = e.getCommitted();
		if (size <= 0) {
			text = (null != inputTextConverter) ? inputTextConverter.converter(text) : text;
			InputMethodEvent ie = new InputMethodEvent(
					e.getSource(),
					e.getTarget(),
					e.getEventType(),
					e.getComposed(),
					"",
					e.getCaretPosition());
			WCInputMethodEvent imEvent = InputMethodClientImpl.convertToWCInputMethodEvent(ie);
			webViewPane.getWebPage().dispatchInputMethodEvent(imEvent);
			ie.consume();
			mapper.insertAtCursorHtml(text);
			return;
		} else {
			WCInputMethodEvent imEvent = InputMethodClientImpl.convertToWCInputMethodEvent(e);
			webViewPane.getWebPage().dispatchInputMethodEvent(imEvent);
		}
	}
}
