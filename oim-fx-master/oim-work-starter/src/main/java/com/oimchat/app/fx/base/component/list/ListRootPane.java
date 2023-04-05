package com.oimchat.app.fx.base.component.list;

import com.oimchat.app.fx.base.component.list.skin.UnitIncrementScrollPaneSkin;

import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 * 
 * Description <br>
 * Date 2020-07-02 11:39:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ListRootPane extends VBox {

	VBox box = new VBox();
	ScrollPane scrollPane = new ScrollPane();

	public ListRootPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.getChildren().add(scrollPane);

		scrollPane.setBackground(Background.EMPTY);
		scrollPane.setVvalue(20);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		scrollPane.setContent(box);
		scrollPane.widthProperty().addListener((Observable observable) -> {
			box.setPrefWidth(scrollPane.getWidth());
		});
		final double initialInc = 0.2;
		UnitIncrementScrollPaneSkin skin = new UnitIncrementScrollPaneSkin(scrollPane);
		skin.getVerticalScrollBar().setUnitIncrement(initialInc);
		skin.getVerticalScrollBar().setBlockIncrement(initialInc);
		skin.getHorizontalScrollBar().setUnitIncrement(initialInc);
		skin.getHorizontalScrollBar().setBlockIncrement(initialInc);

		scrollPane.setSkin(skin);

		EventHandler<MouseEvent> mouseEntered = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
			}
		};

		EventHandler<MouseEvent> mouseExited = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			}
		};
		scrollPane.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEntered);
		scrollPane.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExited);
	}

	private void iniEvent() {
	}

	public void addNode(Node node) {
		if (!box.getChildren().contains(node)) {
			box.getChildren().add(node);
		}
	}

	public void addNode(int index, Node node) {
		if (box.getChildren().contains(node)) {
			box.getChildren().remove(node);
		}
		if (!box.getChildren().contains(node)) {
			box.getChildren().add(index, node);
		}
	}

	public void removeNode(Node node) {
		box.getChildren().remove(node);
	}

	public void removeNode(int index) {
		box.getChildren().remove(index);
	}

	public void clearNode() {
		box.getChildren().clear();
	}

	public int nodeSize() {
		return box.getChildren().size();
	}

	public VBox getBox() {
		return box;
	}
}
