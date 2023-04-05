
package com.oimchat.app.fx.base.component.head;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Description <br>
 * Date 2020-07-05 18:56:00<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HeadCloseItem extends HeadItem {

	private final Button closeButton = new Button();
	private boolean mouseEntered = false;

	public HeadCloseItem() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {

		closeButton.setText("X");
		closeButton.setFocusTraversable(false);
		closeButton.setStyle("-fx-text-fill:rgba(255, 255, 255, 1);-fx-background-color: #bc0000;-fx-background-radius: 50%;");

		// closeButton.getStyleClass().remove("button");
		closeButton.getStyleClass().add("head-item-close-button");
		closeButton.setVisible(mouseEntered);

		StackPane pane = new StackPane();
		pane.getChildren().add(closeButton);

		HBox box = new HBox();
		box.setAlignment(Pos.CENTER_RIGHT);
		box.getChildren().add(pane);
		box.setPadding(new Insets(0, 20, 0, 0));

		this.getChildren().add(box);
	}

	private void iniEvent() {
		this.setOnMouseEntered((Event event) -> {
			mouseEntered = true;
			updateCloseButton();
		});
		this.setOnMouseExited((Event event) -> {
			mouseEntered = false;
			updateCloseButton();
		});

	}

	public void updateCloseButton() {
		closeButton.setVisible(mouseEntered);
	}

	public void setOnCloseAction(EventHandler<ActionEvent> value) {
		closeButton.setOnAction(value);
	}
}
