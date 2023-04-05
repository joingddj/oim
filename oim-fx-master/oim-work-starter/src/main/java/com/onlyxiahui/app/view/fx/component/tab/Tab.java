package com.onlyxiahui.app.view.fx.component.tab;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author XiaHui
 */
public class Tab extends StackPane {

	protected BorderPane borderPane = new BorderPane();

	protected Button tabButton = new Button();
	protected Button iconButton = new Button();

	protected boolean selected = false;
	protected boolean mouseEntered = false;
	protected HBox hBox = new HBox();

	private final Label redLabel = new Label();
	private BooleanProperty red = new SimpleBooleanProperty(false);

	public Tab() {
		initComponent();
		iniEvent();
		update();
	}

	private void initComponent() {

		this.getChildren().add(borderPane);

		borderPane.setCenter(tabButton);

		tabButton.getStyleClass().clear();

		tabButton.setBackground(Background.EMPTY);
		tabButton.setBorder(Border.EMPTY);
		tabButton.setFocusTraversable(false);

		iconButton.getStyleClass().clear();

		iconButton.setBackground(Background.EMPTY);
		iconButton.setBorder(Border.EMPTY);
		iconButton.setFocusTraversable(false);

		hBox.setStyle("-fx-background-color:rgba(194, 194, 173, 1)");

		redLabel.setStyle("-fx-text-fill:rgba(255, 255, 255, 1);-fx-background-color: red;-fx-background-radius: 50%;");
		redLabel.setMinSize(16, 16);
		redLabel.setAlignment(Pos.CENTER);

		StackPane redStackPane = new StackPane();
		redStackPane.getChildren().add(redLabel);

		HBox redHBox = new HBox();
		redHBox.setAlignment(Pos.TOP_RIGHT);
		redHBox.getChildren().add(redStackPane);
		redHBox.setPadding(new Insets(0, 0, 0, 0));

		VBox redVBox = new VBox();
		redVBox.setAlignment(Pos.TOP_RIGHT);
		redVBox.getChildren().add(redHBox);

		StackPane leftStackPane = new StackPane();

		leftStackPane.getChildren().add(iconButton);
		leftStackPane.getChildren().add(redVBox);

		tabButton.setGraphic(leftStackPane);

		this.setSide(Side.BOTTOM);
		this.setIconPadding(new Insets(5, 5, 5, 5));
	}

	private void iniEvent() {
		this.setOnMouseEntered((Event event) -> {
			mouseEntered = true;
			update();
		});
		this.setOnMouseExited((Event event) -> {
			mouseEntered = false;
			update();
		});

		red.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				updateRed();
			}
		});
	}

	public void setOnAction(EventHandler<ActionEvent> value) {
		tabButton.setOnAction(value);
	}

	private void updateRed() {
		redLabel.setVisible(isRed());
	}

	public void setRed(boolean red) {
		this.red.set(red);
	}

	public boolean isRed() {
		return this.red.get();
	}

	public void setRedText(String value) {
		redLabel.setText(value);
	}

	public void setSide(Side side) {
		borderPane.setTop(null);
		borderPane.setRight(null);
		borderPane.setBottom(null);
		borderPane.setLeft(null);

		if (Side.TOP == side) {
			borderPane.setTop(hBox);
			hBox.setMinHeight(2);

			tabButton.setContentDisplay(ContentDisplay.LEFT);

		} else if (Side.RIGHT == side) {
			borderPane.setRight(hBox);
			hBox.setMinWidth(2);

			tabButton.setContentDisplay(ContentDisplay.TOP);
		} else if (Side.BOTTOM == side) {
			borderPane.setBottom(hBox);
			hBox.setMinHeight(2);

			tabButton.setContentDisplay(ContentDisplay.LEFT);
		} else if (Side.LEFT == side) {
			borderPane.setLeft(hBox);
			hBox.setMinWidth(2);

			tabButton.setContentDisplay(ContentDisplay.TOP);
		} else {
			borderPane.setBottom(hBox);
			hBox.setMinHeight(2);

			tabButton.setContentDisplay(ContentDisplay.LEFT);
		}
	}

	private void update() {
		updateTab();
		updateSelected();
		updateRed();
	}

	protected void updateSelected() {
		if (!this.isSelected()) {
			hBox.setStyle("-fx-background-color:-fx-box-border");
		} else {
			hBox.setStyle("-fx-background-color:-fx-accent");
		}
	}

	protected void updateTab() {

	}

	public void setIconPadding(Insets value) {
		tabButton.setPadding(value);
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		update();
	}
}
