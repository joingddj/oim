
package com.oimchat.app.fx.view.ui.classics.module.setting;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Description <br>
 * Date 2021-03-19 13:50:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactHarassSettingPane extends StackPane {

	private final BorderPane borderPane = new BorderPane();
	ToggleGroup tg = new ToggleGroup();
	VBox topVbox = new VBox();
	private ScrollPane scrollPane = new ScrollPane();

	String key;

	Map<String, RadioButton> map = new HashMap<>();

	public ContactHarassSettingPane() {
		initUi();
	}

	private void initUi() {
		HBox hbox = new HBox(18);
		hbox.setAlignment(Pos.CENTER);

		topVbox.setSpacing(5);
		topVbox.setAlignment(Pos.CENTER_LEFT);

		hbox.getChildren().add(topVbox);

		scrollPane.setBackground(Background.EMPTY);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setContent(hbox);

		borderPane.setCenter(scrollPane);

		this.getChildren().add(borderPane);
	}

	public void addTab(String key, String text, Node node) {
		VBox vbox = new VBox();
		RadioButton rb = new RadioButton(text);
		rb.setToggleGroup(tg);
		vbox.getChildren().add(rb);
		if (null != node) {
			node.visibleProperty().bind(rb.selectedProperty());
			node.managedProperty().bind(rb.selectedProperty());
			vbox.getChildren().add(node);
		}
		rb.selectedProperty().addListener((o, ov, nv) -> {
			if (nv) {
				setKey(key);
			}
		});
//		rb.setOnAction(a -> {
//
//		});

		topVbox.getChildren().add(vbox);
		map.put(key, rb);
	}

	public void setSelected(String key) {
		RadioButton rb = map.get(key);
		if (null != rb) {
			rb.setSelected(true);
		}
	}

	public String getSelected() {
		return key;
	}

	private void setKey(String key) {
		this.key = key;
	}
}
