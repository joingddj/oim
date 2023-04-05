package com.onlyxiahui.app.view.fx.component.tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author XiaHui
 */
public class TabPane extends StackPane {

	private final Map<Tab, Node> map = new ConcurrentHashMap<Tab, Node>();
	private final BorderPane borderPane = new BorderPane();
	private final HBox hBox = new HBox();
	private final VBox vBox = new VBox();
	private Side side = Side.TOP;

	private Tab selectedTab;

	public TabPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.getChildren().add(borderPane);
		updateTab();
	}

	private void iniEvent() {
		// TODO Auto-generated method stub

	}

	private void add(Node tab) {
		if (Side.TOP == side) {
			hBox.getChildren().add(tab);
			HBox.setHgrow(tab, Priority.ALWAYS);
		} else if (Side.RIGHT == side) {
			vBox.getChildren().add(tab);
			VBox.setVgrow(tab, Priority.ALWAYS);
		} else if (Side.BOTTOM == side) {
			hBox.getChildren().add(tab);
			HBox.setHgrow(tab, Priority.ALWAYS);
		} else if (Side.LEFT == side) {
			vBox.getChildren().add(tab);
			VBox.setVgrow(tab, Priority.ALWAYS);
		} else {
			hBox.getChildren().add(tab);
			HBox.setHgrow(tab, Priority.ALWAYS);
		}
	}

	private List<Node> getAllNodes() {
		List<Node> list = new ArrayList<>(hBox.getChildren().size() + vBox.getChildren().size());
		list.addAll(hBox.getChildren());
		list.addAll(vBox.getChildren());
		return list;
	}

	private void updateTab() {
		borderPane.setTop(null);
		borderPane.setRight(null);
		borderPane.setBottom(null);
		borderPane.setLeft(null);
		List<Node> list = getAllNodes();
		hBox.getChildren().clear();
		vBox.getChildren().clear();

		if (Side.TOP == side) {
			borderPane.setTop(hBox);
		} else if (Side.RIGHT == side) {
			borderPane.setRight(vBox);
		} else if (Side.BOTTOM == side) {
			borderPane.setBottom(hBox);
		} else if (Side.LEFT == side) {
			borderPane.setLeft(vBox);
		} else {
			borderPane.setBottom(hBox);
		}
		for (Node node : list) {
			if (node instanceof Tab) {
				Tab tab = (Tab) node;
				setTabSide(tab);
			}
			add(node);
		}
	}

	public void selected(Tab tab) {
		if (!tab.equals(selectedTab)) {
			if (null != selectedTab) {
				selectedTab.setSelected(false);
			}
			Node node = map.get(tab);
			if (!tab.isSelected()) {
				tab.setSelected(true);
				setSelectedNode(node);
			}
			selectedTab = tab;
		}
	}

	private void setSelectedNode(Node value) {
		borderPane.setCenter(value);
	}

	public void setSide(Side side) {
		this.side = side;
		updateTab();
	}

	private void tabOnMouseClicked(Event event) {
		Object o = event.getSource();
		if (o instanceof Tab) {
			Tab tabTemp = (Tab) o;
			selected(tabTemp);
		}
	}

	public void selected(int index) {
		Node node = null;
		if (Side.TOP == side) {
			node = hBox.getChildren().get(index);
		} else if (Side.RIGHT == side) {
			node = vBox.getChildren().get(index);
		} else if (Side.BOTTOM == side) {
			node = hBox.getChildren().get(index);
		} else if (Side.LEFT == side) {
			node = vBox.getChildren().get(index);
		} else {
			node = hBox.getChildren().get(index);
		}
		if (node instanceof Tab) {
			Tab tabTemp = (Tab) node;
			selected(tabTemp);
		}
	}

	public void setTabSize(double value) {
		hBox.setPrefHeight(value);
		vBox.setPrefWidth(value);
	}

	public void setTabPadding(Insets value) {
		hBox.setPadding(value);
		vBox.setPadding(value);
	}

	private void setTabSide(Tab tab) {
		if (Side.TOP == side) {
			tab.setSide(Side.BOTTOM);
		} else if (Side.RIGHT == side) {
			tab.setSide(Side.LEFT);
		} else if (Side.BOTTOM == side) {
			tab.setSide(Side.TOP);
		} else if (Side.LEFT == side) {
			tab.setSide(Side.RIGHT);
		} else {
			tab.setSide(Side.BOTTOM);
		}
	}

	public void add(Tab tab, Node node) {
		setTabSide(tab);
		add(tab);
		map.put(tab, node);
		if (null == selectedTab) {
			tab.setSelected(true);
			selectedTab = tab;
			setSelectedNode(node);
		}
		tab.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				selected(tab);
			}
		});
		tab.setOnMouseClicked((Event event) -> {
			tabOnMouseClicked(event);
		});
	}

	public IconTab add(FontIconText icon, Node node) {
		IconTab tab = new IconTab(icon);
		add(tab, node);
		return tab;
	}

	public IconTab add(String text, FontIconText icon, Node node) {
		IconTab tab = new IconTab(text, icon);
		add(tab, node);
		return tab;
	}

	public ImageTab add(String text, Image normalImage, Image hoverImage, Image selectedImage, Node node) {
		ImageTab tab = new ImageTab(text, normalImage, hoverImage, selectedImage);
		add(tab, node);
		return tab;
	}

	public ImageTab add(String text, Font font, Paint paint, Image normalImage, Image hoverImage, Image selectedImage, Node node) {
		ImageTab tab = new ImageTab(text, font, paint, normalImage, hoverImage, selectedImage);
		add(tab, node);
		return tab;
	}

	public ImageTab add(Image normalImage, Image hoverImage, Image selectedImage, Node node) {
		ImageTab tab = new ImageTab(normalImage, hoverImage, selectedImage);
		add(tab, node);
		return tab;
	}
}
