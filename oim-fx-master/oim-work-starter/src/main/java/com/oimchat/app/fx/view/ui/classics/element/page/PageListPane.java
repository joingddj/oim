package com.oimchat.app.fx.view.ui.classics.element.page;

import com.oimchat.app.fx.base.component.loading.LoadingPane;

import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * @author: XiaHui
 * @date: 2017年3月27日 下午4:30:11
 */
public class PageListPane extends StackPane {

	BorderPane listRootPane = new BorderPane();
	Pagination page = new Pagination();
	BorderPane centerPane = new BorderPane();
	LoadingPane loadingPane = new LoadingPane();

	public PageListPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.getChildren().add(listRootPane);

		loadingPane.getChildren().add(centerPane);

		listRootPane.setCenter(loadingPane);
		listRootPane.setBottom(page);
		listRootPane.setStyle("-fx-background-color:rgba(255, 255, 255, 0.9)");
		this.showLoding();
		this.setPage(0, 1);
	}

	private void iniEvent() {

	}

	public void showFail() {
		loadingPane.showFail();
	}

	public void showLoding() {
		loadingPane.showLoding();
	}

	public void showNode() {
		loadingPane.showNode();
	}

	public void setPage(int currentPage, int totalPage) {
		page.setCurrentPageIndex(currentPage);
		page.setPageCount(totalPage);
	}

	public void setTotalPage(int totalPage) {
		page.setPageCount(totalPage);
	}

	public void setPageFactory(Callback<Integer, Node> value) {
		page.setPageFactory(value);
	}

	public int getCurrentPage() {
		return page.getCurrentPageIndex();
	}

	public void setContentPane(Node node) {
		centerPane.setCenter(node);
	}
}
