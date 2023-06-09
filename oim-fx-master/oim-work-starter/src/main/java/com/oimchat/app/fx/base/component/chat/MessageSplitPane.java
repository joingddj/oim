package com.oimchat.app.fx.base.component.chat;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MessageSplitPane extends StackPane {

	
	private int type = 0;

	private final BorderPane borderPane = new BorderPane();

	private final BorderPane topPane = new BorderPane();
	private final BorderPane bottomPane = new BorderPane();
	private double minTop = 55;
	private double minBottom = 105;
	private double rootHeight;

	public MessageSplitPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		bottomPane.setPrefHeight(140);
		bottomPane.setMinHeight(minBottom);

		borderPane.setCenter(topPane);
		borderPane.setBottom(bottomPane);

		this.getChildren().add(borderPane);
	}

	private void iniEvent() {
		bottomPane.setOnMouseExited((MouseEvent me) -> {
			Cursor cursor = Cursor.DEFAULT;
			if (this.getCursor() != cursor) {
				this.setCursor(cursor);
			}
		});

		bottomPane.setOnMouseMoved((MouseEvent me) -> {
			Cursor cursor = getCursor(me, bottomPane);
			if (this.getCursor() != cursor) {
				this.setCursor(cursor);
			}
		});
		this.setOnMousePressed((MouseEvent me) -> {
			rootHeight = MessageSplitPane.this.getHeight();
		});
		this.setOnMouseDragged((MouseEvent me) -> {
			if (type == 1) {
			} else if (type == 2) {
			} else if (type == 3) {
			} else if (type == 4) {
			} else if (type == 5) {
			} else if (type == 6) {
			} else if (type == 7) {

				double value = rootHeight - me.getY();
				updateHeight(bottomPane, value);

				me.consume();
			} else if (type == 8) {

			}
		});
	}

	private void updateHeight(Pane stage, double value) {
		if ((minBottom <= value && value <= (rootHeight - minTop))) {
			stage.setPrefHeight(value);
		}
	}

	private Cursor getCursor(MouseEvent me, Pane pane) {
		Cursor cursor = Cursor.DEFAULT;

		double grp = 4;
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
			// cursor = Cursor.H_RESIZE;
			type = 6;
		} else if (y < grp) {
			cursor = Cursor.V_RESIZE;
			type = 7;
		} else if (y > (height - grp)) {
			// cursor = Cursor.V_RESIZE;
			type = 8;
		} else {
			type = 0;
		}
		return cursor;
	}

	public void setTop(Node node) {
		topPane.setCenter(node);
	}

	public void setBottom(Node node) {
		bottomPane.setCenter(node);
	}
}
