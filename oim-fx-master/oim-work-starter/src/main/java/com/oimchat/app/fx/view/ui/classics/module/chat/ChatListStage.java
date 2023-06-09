package com.oimchat.app.fx.view.ui.classics.module.chat;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import com.oimchat.app.fx.base.component.choose.Choose;
import com.oimchat.app.fx.base.component.choose.ChooseGroup;
import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ChatListStage extends ClassicsStage {

	protected Map<String, HeadCloseItem> itemMap = new ConcurrentSkipListMap<String, HeadCloseItem>();

	private final BorderPane baseBorderPane = new BorderPane();

	private final ChooseGroup chooseGroup = new ChooseGroup();

	private final VBox itemVBox = new VBox();
	private final ScrollPane itemScrollPane = new ScrollPane();

	private final StackPane itemPane = new StackPane();
	private final BorderPane chatPane = new BorderPane();

	private double paneMinWidth = 585;
	private double itemWidth = 190;
	private double stageWidth;
	private int type = 0;

	public ChatListStage() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setTitle("OIM");
		this.setWidth(1030);
		this.setHeight(660);
		this.setMinWidth(itemWidth + paneMinWidth);
		this.setMinHeight(510);
		this.setCenter(baseBorderPane);

		baseBorderPane.setLeft(itemPane);
		baseBorderPane.setCenter(chatPane);

		itemPane.setPrefWidth(itemWidth);
		itemPane.setMinWidth(65);
		itemPane.setMaxWidth(250);

		itemVBox.setPadding(new Insets(0, 0, 0, 0));

		itemScrollPane.setFitToWidth(true);
		itemScrollPane.setContent(itemVBox);
		itemScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		itemPane.setPadding(new Insets(0, 1, 0, 0));
		itemPane.getChildren().add(itemScrollPane);
	}

	private void iniEvent() {
		itemPane.setOnMouseDragExited((MouseEvent me) -> {
			Cursor cursor = getCursor(me, itemPane);
			if (itemPane.getCursor() != cursor) {
				itemPane.setCursor(cursor);
			}
		});

		itemPane.setOnMouseMoved((MouseEvent me) -> {
			Cursor cursor = getCursor(me, itemPane);
			if (itemPane.getCursor() != cursor) {
				itemPane.setCursor(cursor);
			}
		});
		itemPane.setOnMousePressed((MouseEvent me) -> {
			stageWidth = ChatListStage.this.getWidth();
			itemWidth = itemPane.getWidth();
		});
		itemPane.setOnMouseDragged((MouseEvent me) -> {
			if (type == 1) {
			} else if (type == 2) {
			} else if (type == 3) {
			} else if (type == 4) {
			} else if (type == 5) {
			} else if (type == 6) {
				double value = me.getX();
				double max = itemPane.getMaxWidth();
				double min = itemPane.getMinWidth();
				if ((min <= value && value <= max)) {
					itemPane.setPrefWidth(value);
					ChatListStage.this.setWidth(stageWidth + (value - itemWidth));
					ChatListStage.this.setMinWidth(paneMinWidth + value);
				}
				me.consume();
			} else if (type == 7) {
			} else if (type == 8) {
			}
		});

		itemPane.setOnMouseClicked(e -> {
			e.consume();
		});
	}

	public void remove(String key) {
		HeadCloseItem chatItem = itemMap.remove(key);
		removeItem(chatItem);
	}

	public void addItem(HeadCloseItem chatItem) {
		if (!itemVBox.getChildren().contains(chatItem)) {
			itemVBox.getChildren().add(chatItem);
			chatItem.setChooseGroup(chooseGroup);
			itemMap.put(chatItem.getKey(), chatItem);
		}
	}

	private void removeItem(HeadCloseItem chatItem) {
		if (null != chatItem) {
			itemVBox.getChildren().remove(chatItem);
			if (itemMap.isEmpty()) {
				this.hide();
			} else {
				if (chatItem.isSelected()) {
					Iterator<String> iterator = itemMap.keySet().iterator();
					if (iterator.hasNext()) {
						String nextKey = iterator.next();
						selected(nextKey);
					}
				}
			}
		}
	}

	public boolean isItemListEmpty() {
		return itemVBox.getChildren().isEmpty();
	}

	public boolean has(String key) {
		return itemMap.containsKey(key);
	}

	public HeadCloseItem getItem(String key) {
		return itemMap.get(key);
	}

	public void selected(String key) {
		HeadCloseItem chatItem = itemMap.get(key);
		if (null != chatItem) {
			chatItem.setSelected(true);
		}
	}

	public boolean isSelected(String key) {
		HeadCloseItem chatItem = itemMap.get(key);
		boolean selected = (null != chatItem) && chatItem.isSelected();
		return selected;
	}

	public void setChatPane(Node node) {
		chatPane.setCenter(node);
	}

	private Cursor getCursor(MouseEvent me, Pane pane) {
		Cursor cursor = Cursor.DEFAULT;

		double grp = 3;
		double width = pane.getWidth();
		double height = pane.getHeight();

		double x = me.getX();
		double y = me.getY();

		if (x < grp && y < grp) {
			// cursor = Cursor.SE_RESIZE;
			type = 1;
		} else if (x > (width - grp) && y < grp) {
			// cursor = Cursor.SW_RESIZE;
			type = 2;
		} else if (x < grp && y > (height - grp)) {
			// cursor = Cursor.SW_RESIZE;
			type = 3;
		} else if (x > (width - grp) && y > (height - grp)) {
			// cursor = Cursor.SE_RESIZE;
			type = 4;
		} else if (x < grp) {
			// cursor = Cursor.H_RESIZE;
			type = 5;
		} else if (x > (width - grp)) {
			cursor = Cursor.H_RESIZE;
			type = 6;
		} else if (y < grp) {
			// cursor = Cursor.V_RESIZE;
			type = 7;
		} else if (y > (height - grp)) {
			// cursor = Cursor.V_RESIZE;
			type = 8;
		} else {
			type = 0;
		}
		return cursor;
	}

	public void addSelectedItemListener(ChangeListener<Choose> listener) {
		chooseGroup.selectedChooseProperty().addListener(listener);
	}
}
