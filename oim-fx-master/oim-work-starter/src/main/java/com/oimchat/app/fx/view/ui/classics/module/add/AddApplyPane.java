
package com.oimchat.app.fx.view.ui.classics.module.add;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.element.list.KeyText;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Description <br>
 * Date 2021-02-26 15:50:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AddApplyPane extends StackPane {

	BorderPane rootPane = new BorderPane();

	BorderPane infoRootPane = new BorderPane();
	BaseInfoPane infoPanel = new BaseInfoPane();

	TextField remarkText = new TextField();
	ComboBox<KeyText> categoryBox = new ComboBox<KeyText>();
	Button newButton = new Button();
	ValueEvent<String> eventAction;

	public AddApplyPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setWidth(460);
		this.setHeight(355);
		this.setStyle("-fx-background-color:#ffffff");

		infoPanel.setHeadSize(100);
		infoPanel.setMaxWidth(130);
		infoPanel.setStyle("-fx-background-color:#e8f0f3");

		categoryBox.getSelectionModel().select(0);
		categoryBox.setConverter(new StringConverter<KeyText>() {

			@Override
			public String toString(KeyText object) {
				return object.getText();
			}

			@Override
			public KeyText fromString(String string) {
				return null;
			}

		});
		categoryBox.setCellFactory(new Callback<ListView<KeyText>, ListCell<KeyText>>() {
			@Override
			public ListCell<KeyText> call(ListView<KeyText> param) {
				ListCell<KeyText> cell = new ListCell<KeyText>() {
					{
						super.setPrefWidth(100);
					}

					@Override
					public void updateItem(KeyText item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.getText());
						} else {
							setText(null);
						}
					}
				};
				return cell;
			}
		});

		remarkText.setPromptText("备注 选填");
		newButton.setText("新建分组");

		remarkText.setLayoutX(75);
		remarkText.setLayoutY(20);
		remarkText.setPrefSize(155, 23);

		categoryBox.setLayoutX(75);
		categoryBox.setLayoutY(55);
		categoryBox.setPrefSize(155, 23);

		newButton.setLayoutX(235);
		newButton.setLayoutY(55);
		newButton.setPrefSize(70, 23);

		AnchorPane infoPane = new AnchorPane();
		infoPane.setStyle("-fx-background-color:#ffffff");
		infoPane.getChildren().add(remarkText);
		infoPane.getChildren().add(categoryBox);
		infoPane.getChildren().add(newButton);

		infoRootPane.setTop(infoPane);

		rootPane.setLeft(infoPanel);
		rootPane.setCenter(infoRootPane);

		this.getChildren().add(rootPane);
	}

	private void iniEvent() {
		newButton.setOnAction(a -> {
			getTextInputDialog("").showAndWait().ifPresent(response -> {
				if (null == response || response.isEmpty()) {
					// System.out.println("No name was inserted");
				} else {
					if (null != eventAction) {
						eventAction.value(response);
					}
				}
			});
		});
	}

	public void clearData() {
		remarkText.setText("");
	}

	public BaseInfoPane getInfoPanel() {
		return infoPanel;
	}

	public String getRemark() {
		return remarkText.getText();
	}

	public void addCategory(String id, String name) {
		categoryBox.getItems().add(new KeyText(id, name));
	}

	public void selectCategory(int index) {
		categoryBox.getSelectionModel().select(index);
	}

	public String getCategoryId() {
		KeyText kt = categoryBox.getValue();
		return (null == kt) ? null : kt.getKey();
	}

	public void clearCategory() {
		categoryBox.getItems().clear();
	}

	public void setNewCategoryAction(ValueEvent<String> eventAction) {
		this.eventAction = eventAction;
	}

	public void setBottomNode(Node node) {
		infoRootPane.setBottom(node);
	}

	public TextInputDialog getTextInputDialog(String name) {
		TextInputDialog textInput = new TextInputDialog(name);
		textInput.setTitle("输入分组");
		textInput.setContentText("名称:");
		return new TextInputDialog("");
	}
}
