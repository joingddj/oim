package com.oimchat.app.fx.view.ui.classics.module.chat;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.app.fx.base.component.chat.MessageAreaPane;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

/**
 * 
 * Description <br>
 * Date 2021-03-02 18:19:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ChatPane extends StackPane {

	private Map<Object, Object> attributeMap = new HashMap<Object, Object>();

	private final BorderPane baseBorderPane = new BorderPane();

	private final ChatTopPane topPane = new ChatTopPane();
	private final MessageAreaPane areaPane = new MessageAreaPane();
	private ValueEvent<List<File>> fileEventAction;

	public ChatPane() {
		initComponent();
		iniEvent();

	}

	private void initComponent() {
		baseBorderPane.setTop(topPane);
		baseBorderPane.setCenter(areaPane);
		this.getChildren().add(baseBorderPane);
	}

	private void iniEvent() {
		setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != ChatPane.this) {
					event.acceptTransferModes(TransferMode.ANY);
				}
			}
		});
		EventHandler<DragEvent> eh = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				List<File> files = dragboard.getFiles();
				if (null != files && null != fileEventAction) {
					fileEventAction.value(files);
				}
				event.consume();
			}
		};
		setOnDragDropped(eh);
		areaPane.getReadPane().getWebViewPane().setWebOnDragDropped(eh);
		areaPane.getWritePane().getWebViewPane().setWebOnDragDropped(eh);
	}

	public void addAttribute(Object key, Object value) {
		attributeMap.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(Object key) {
		return (T) attributeMap.get(key);
	}

	public void setText(String value) {
		topPane.setText(value);
	}

	public void setName(String value) {
		topPane.setName(value);
	}

	public void addTopTool(Node node) {
		topPane.addTool(node);
	}

	public void addTopRightTool(Node node) {
		topPane.addRightTool(node);
	}

	public void setRight(Node value) {
		baseBorderPane.setRight(value);
	}

	public void addMiddleLeftTool(Node node) {
		areaPane.addMiddleLeftTool(node);
	}

	public void addMiddleRightTool(Node node) {
		areaPane.addMiddleRightTool(node);
	}

	public void setOnSendAction(EventHandler<ActionEvent> value) {
		areaPane.setOnSendAction(value);
	}

	public void setSendButtonDisable(boolean value) {
		areaPane.setSendButtonDisable(value);
	}

	public MessageAreaPane getAreaPane() {
		return areaPane;
	}

	public void setOnFileEventAction(ValueEvent<List<File>> fileEventAction) {
		this.fileEventAction = fileEventAction;
	}

	public void setOnWriteKeyReleased(EventHandler<? super KeyEvent> value) {
		WebView webView = areaPane.getWritePane().getWebViewPane().getWebView();
		webView.setOnKeyReleased(value);
	}
}
