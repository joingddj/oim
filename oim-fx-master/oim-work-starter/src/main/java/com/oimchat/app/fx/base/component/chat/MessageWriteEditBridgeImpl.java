
package com.oimchat.app.fx.base.component.chat;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.client.basic.util.StringHtmlUtil;
import com.onlyxiahui.app.view.fx.common.data.DataConverter;

import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;

/**
 * Description <br>
 * Date 2021-03-03 17:14:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageWriteEditBridgeImpl implements MessageWriteEditBridge {

	MessageWriteMapper mapper;
	boolean canBlankLine = false;
	DataConverter<String, String> inputTextConverter;

	public MessageWriteEditBridgeImpl(MessageWriteMapper mapper) {
		this.mapper = mapper;
//		Tidy tidy = new Tidy();
//		tidy.setXHTML(true); // set desired config options using tidy setters
//		tidy.setInputEncoding("utf8");
//		tidy.setShowWarnings(false);
//		tidy.setWraplen(1024);
//		tidy.setSmartIndent(true);
//		tidy.setQuiet(true);
//		tidy.setPrintBodyOnly(true);
//		tidy.setOutputEncoding("utf8");
//		try {
//			StringReader sr = new StringReader(content);
//			StringWriter sw = new StringWriter();
//			tidy.parse(sr, sw);
//			content = sw.toString();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

	@Override
	public void paste() {
		// .getContent(DataFormat.PLAIN_TEXT);
		String content = (String) Clipboard.getSystemClipboard().getHtml();
		if (content != null) {
			content = handlerHtml(content);
		}

		if (content == null) {
			content = (String) Clipboard.getSystemClipboard().getString();
			content = StringHtmlUtil.htmlEscape(content);
		}
		// content = null == inputTextConverter ? content : inputTextConverter.converter(content);
		
		// System.out.println(content);
		
		if (content != null) {
			Map<DataFormat, Object> map = new HashMap<>();
			map.put(DataFormat.HTML, content);
			Clipboard.getSystemClipboard().setContent(map);
		}

		if (content != null) {
//			mapper.deleteSelection();
//			mapper.insertAtCursorHtml(content);
		}
	}

	private String handlerHtml(String text) {
		if (text != null) {
			text = text.replaceAll("(<bR\\/>|<bR>|<Br\\/>|<Br>|<br\\/>|<br>|<BR>|<BR\\/>)", "\n");
			text = text.replaceAll("<(?!(img|IMG))[^>]*>", "");
			text = trim(text);
		}
		return text;
	}

	private String trim(String text) {
		String exp = "^[\\s\\uFEFF\\xA0]+|[\\s\\uFEFF\\xA0]+$";
		return text == null ? null : text.replaceAll(exp, "");
	}

	public boolean canBlankLine() {
		return canBlankLine;
	}

	@Override
	public void showLog(Object o) {
		System.out.println("showLog");
		System.out.println(o);
	}

	@Override
	public DataConverter<String, String> getInputTextConverter() {
		return inputTextConverter;
	}

	@Override
	public void setInputTextConverter(DataConverter<String, String> inputTextConverter) {
		this.inputTextConverter = inputTextConverter;
	}
}
