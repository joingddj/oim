
package com.oimchat.app.fx.base.component.list;

import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 * Description <br>
 * Date 2020-07-02 19:22:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ItemListCell<T> extends ListCell<T> {

	public ItemListCell() {
		super();
		init();
	}

	private void init() {
		this.setStyle("-fx-padding: 0 0 0 0;");
	}

	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else if (item instanceof Node) {
			setText(null);
			Node currentNode = getGraphic();
			Node newNode = (Node) item;
			if (currentNode == null || !currentNode.equals(newNode)) {
				setGraphic(newNode);
			}
		} else {
			/**
			 * This label is used if the item associated with this cell is to be represented
			 * as a String. While we will lazily instantiate it we never clear it, being
			 * more afraid of object churn than a minor "leak" (which will not become a
			 * "major" leak).
			 */
			setText(item == null ? "null" : item.toString());
			setGraphic(null);
		}
	}
}
