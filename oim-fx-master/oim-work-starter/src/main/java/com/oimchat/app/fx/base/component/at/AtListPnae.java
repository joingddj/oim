
package com.oimchat.app.fx.base.component.at;

import java.util.List;

import com.onlyxiahui.app.fx.OnlyPopupOver;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;

/**
 * Description <br>
 * Date 2021-03-04 14:23:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AtListPnae extends StackPane {

	private OnlyPopupOver popupOver = new OnlyPopupOver();

	ListView<AtItemData> listView = new ListView<>();
	ValueEvent<AtItemData> va;

	public AtListPnae() {
		popupOver.setArrowLocation(OnlyPopupOver.ArrowLocation.TOP_LEFT);

		popupOver.setDetachable(false);
		popupOver.setDetached(false);

		popupOver.setArrowSize(0);

		popupOver.setContentNode(this);

		listView.setPrefSize(160, 220);

		this.getChildren().add(listView);

		listView.setOnKeyPressed(k -> {
			// k.consume();
			// System.out.println("listPressed" + ":" + k.getCode());
		});

		listView.setOnKeyReleased(k -> {
			k.consume();
			// System.out.println("listReleased" + ":" + k.getCode());
		});
		initEvent();

		listView.setCellFactory((ListView<AtItemData> list) -> new ListCell<AtItemData>() {
			@Override
			public void updateItem(AtItemData item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null) {
					this.setText("");
					this.setGraphic(null);
					return;
				}
				Image image = item.getImage();
				String text = item.getText();

				this.setText(text);

				if (null != image) {
					ImageView iv = new ImageView(image);
					iv.setFitHeight(24);
					iv.setFitWidth(24);
					this.setGraphic(iv);
				} else {
					this.setGraphic(null);
				}
			}
		});

		listView.setBorder(Border.EMPTY);
		listView.setBackground(Background.EMPTY);
		listView.setBlendMode(BlendMode.DARKEN);
	}

	private void initEvent() {
		listView.setOnKeyReleased(k -> {
			if (k.getCode() == KeyCode.ENTER) {
				k.consume();
				if (null != va) {
					va.value(listView.getSelectionModel().getSelectedItem());
				}
			}
		});
		listView.setOnMouseClicked(k -> {
			if (null != va) {
				va.value(listView.getSelectionModel().getSelectedItem());
			}
		});
	}

	public void show(Node node, double x, double y) {
		popupOver.show(node, x, y);
	}

	public void hide() {
		popupOver.hide();
	}

	public boolean isShowing() {
		return popupOver.isShowing();
	}

	public void listViewFireEvent(KeyEvent key) {
		listView.fireEvent(key);
	}

	public ListView<AtItemData> getListView() {
		return listView;
	}

	public void setList(List<AtItemData> items) {
		FxUtil.invoke(() -> {
			listView.getItems().clear();
			listView.getItems().addAll(items);
		});
	}

	public void setOnSelected(ValueEvent<AtItemData> va) {
		this.va = va;
	}
}
