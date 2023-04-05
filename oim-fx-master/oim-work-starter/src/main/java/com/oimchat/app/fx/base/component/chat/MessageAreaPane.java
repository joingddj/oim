package com.oimchat.app.fx.base.component.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * 
 * Description <br>
 * Date 2021-03-02 18:19:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class MessageAreaPane extends StackPane {

	private Map<Object, Object> attributeMap = new HashMap<Object, Object>();

	private final MessageSplitPane splitPane = new MessageSplitPane();
	private final MessageReadPane readPane = new MessageReadPane();
	private final MessageWritePane writePane = new MessageWritePane();

	private final BorderPane writeBorderPane = new BorderPane();

	private final ToolBar middleLeftToolBar = new ToolBar();
	private final ToolBar middleRightToolBar = new ToolBar();

	private final HBox middleToolBarBox = new HBox();
	private final HBox bottomButtonBox = new HBox();

	private Button sendButton = new Button();

	public MessageAreaPane() {
		initComponent();
		iniEvent();

	}

	private void initComponent() {

		HBox line = new HBox();
		line.setMinHeight(1);
		line.setStyle("-fx-background-color:#d6d6d6;");

		middleLeftToolBar.setOnMouseEntered(m -> {
			middleLeftToolBar.setCursor(Cursor.DEFAULT);
		});
		middleRightToolBar.setOnMouseEntered(m -> {
			middleRightToolBar.setCursor(Cursor.DEFAULT);
		});

		// TODO

		middleLeftToolBar.setPadding(new Insets(0, 0, 0, 0));

		middleLeftToolBar.setBackground(Background.EMPTY);
		// middleToolBar.setPadding(new Insets(0, 5, 5, 2));
		middleLeftToolBar.setStyle("-fx-background-color:null");

		middleToolBarBox.getChildren().add(middleLeftToolBar);
		middleToolBarBox.setMinHeight(20);
		// middleToolBarBox.setSpacing(6);
		middleToolBarBox.setAlignment(Pos.CENTER_LEFT);
		middleToolBarBox.setPadding(new Insets(0, 10, 0, 0));

		middleRightToolBar.setPadding(new Insets(0, 0, 0, 0));
		middleRightToolBar.setBackground(Background.EMPTY);
		// middleRightToolBar.setPadding(new Insets(0, 5, 5, 2));
		middleRightToolBar.setStyle("-fx-background-color:null");

		HBox middleRightToolBarBox = new HBox();
		middleRightToolBarBox.setMinHeight(20);
		// middleRightToolBarBox.setSpacing(6);
		middleRightToolBarBox.setAlignment(Pos.CENTER_RIGHT);

		middleRightToolBarBox.getChildren().add(middleRightToolBar);

		BorderPane middleToolBarPane = new BorderPane();
		middleToolBarPane.setPadding(new Insets(3, 10, 3, 10));
		middleToolBarPane.setCenter(middleToolBarBox);
		middleToolBarPane.setRight(middleRightToolBarBox);

		VBox writeTempBox = new VBox();

		writeTempBox.getChildren().add(line);
		writeTempBox.getChildren().add(middleToolBarPane);

		readPane.setStyle("-fx-background-color:rgba(250, 250, 250, 0.9)");
		writeBorderPane.setStyle("-fx-background-color:rgba(250, 250, 250, 0.9)");

		writeBorderPane.setTop(writeTempBox);
		writeBorderPane.setCenter(writePane);
		writeBorderPane.setBottom(bottomButtonBox);

		splitPane.setTop(readPane);
		splitPane.setBottom(writeBorderPane);
		splitPane.setPrefWidth(780);

		sendButton.setText("发送");

		sendButton.setPrefSize(72, 24);
		sendButton.setFocusTraversable(false);

		// bottomButtonBox.setStyle("-fx-background-color:#ffffff;");
		bottomButtonBox.setPadding(new Insets(0, 15, 0, 0));
		bottomButtonBox.setSpacing(5);
		bottomButtonBox.setAlignment(Pos.CENTER_RIGHT);
		bottomButtonBox.setMinHeight(40);
		bottomButtonBox.getChildren().add(sendButton);

		this.getChildren().add(splitPane);
	}

	private void iniEvent() {
		splitPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			e.consume();
		});
	}

	public void addAttribute(Object key, Object value) {
		attributeMap.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(Object key) {
		return (T) attributeMap.get(key);
	}

	public void addMiddleLeftTool(Node node) {
		middleLeftToolBar.getItems().add(node);
	}

	public void addMiddleLeftTool(List<Node> nodes) {
		middleLeftToolBar.getItems().addAll(nodes);
	}

	public void clearMiddleLeftTool() {
		middleLeftToolBar.getItems().clear();
	}

	public void addMiddleRightTool(Node node) {
		middleRightToolBar.getItems().add(node);
	}

	public void addMiddleRightTool(List<Node> nodes) {
		middleRightToolBar.getItems().addAll(nodes);
	}

	public void clearMiddleRightTool() {
		middleRightToolBar.getItems().clear();
	}

//	public HBox getMiddleToolBarBox() {
//		return middleToolBarBox;
//	}

	public void setOnSendAction(EventHandler<ActionEvent> value) {
		sendButton.setOnAction(value);
	}

	public ToolBar getMiddleLeftToolBar() {
		return middleLeftToolBar;
	}

	public ToolBar getMiddleRightToolBar() {
		return middleRightToolBar;
	}

	public void setSendButtonDisable(boolean value) {
		sendButton.setDisable(value);
	}

	public MessageReadPane getReadPane() {
		return readPane;
	}

	public MessageWritePane getWritePane() {
		return writePane;
	}

	public void addBottomBeforeButton(Button button) {
		bottomButtonBox.getChildren().add(0, button);
	}

	public void addBottomLastButton(Button button) {
		bottomButtonBox.getChildren().add(button);
	}
}
