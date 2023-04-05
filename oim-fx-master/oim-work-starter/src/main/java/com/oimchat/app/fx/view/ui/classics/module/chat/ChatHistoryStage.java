package com.oimchat.app.fx.view.ui.classics.module.chat;

import com.oimchat.app.fx.base.component.chat.MessageReadPane;
import com.oimchat.app.fx.base.component.loading.WaitingPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsCommonStage;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * 
 * @author: XiaHui
 * @date 2018-02-01 13:44:29
 */
public class ChatHistoryStage extends ClassicsCommonStage {

	Pagination page = new Pagination();// 分页组件
	StackPane centerPane = new StackPane();
	MessageReadPane showPane = new MessageReadPane();
	WaitingPane waitingPane = new WaitingPane();
	ScrollPane scrollPane = new ScrollPane();

	public ChatHistoryStage() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setTitle("聊天记录");
		// this.setResizable(false);
		this.setWidth(750);
		this.setHeight(480);
		// this.setTitlePaneStyle(2);
		this.setRadius(5);
		BorderPane rootPane = new BorderPane();
		this.setCenter(rootPane);

		centerPane.getChildren().add(showPane);
		centerPane.getChildren().add(waitingPane);

		// centerPane.setStyle("-fx-background-color:rgba(8, 103, 68, 0.5)");
		centerPane.setStyle("-fx-background-color:rgba(255, 255, 255)");

		rootPane.setCenter(centerPane);
		rootPane.setBottom(page);

		this.showWaiting(false, WaitingPane.show_waiting);
		this.setPage(0, 1);
	}

	private void iniEvent() {
		// TODO Auto-generated method stub
	}

	public void showWaiting(boolean show, String key) {
		FxUtil.invoke(() -> {
			waitingPane.setVisible(show);
			showPane.setVisible(!show);
			waitingPane.show(key);
		});
	}

	public void setPage(int currentPage, int totalPage) {
		FxUtil.invoke(() -> {
			page.setCurrentPageIndex(currentPage);
			page.setPageCount(totalPage);
		});
	}

	public void setTotalPage(int totalPage) {
		FxUtil.invoke(() -> {
			page.setPageCount(totalPage);
		});
	}

	public void setPageFactory(Callback<Integer, Node> value) {
		FxUtil.invoke(() -> {
			page.setPageFactory(value);
		});
	}

	public void setMessages(String json) {
		FxUtil.invoke(() -> {
			showPane.getMapper().setMessages(json);
		});
	}

	public void insertBefore(String json) {
		FxUtil.invoke(() -> {
			showPane.getMapper().insertBefore(json);
		});
	}

	public void insertLast(String json) {
		FxUtil.invoke(() -> {
			showPane.getMapper().insertLast(json);
		});
	}

	public void replaceImage(String id, String src) {
		String img = src.replace("\\", "/");
		FxUtil.invoke(() -> {
			showPane.getMapper().replaceImageSrc(id, img);
		});
	}

	public void clear() {
		FxUtil.invoke(() -> {
			showPane.getMapper().clear();
		});
	}

	public MessageReadPane getReadPane() {
		return showPane;
	}
}
