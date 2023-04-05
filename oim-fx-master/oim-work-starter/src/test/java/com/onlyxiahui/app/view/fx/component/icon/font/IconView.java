
package com.onlyxiahui.app.view.fx.component.icon.font;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Description <br>
 * Date 2021-03-27 09:55:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class IconView extends BorderPane {
	TextField textField = new TextField();
	TextField nameField = new TextField();

	public IconView(FontIconButton icon, String name, String text) {

		textField.setText(text);
		textField.setEditable(false);

		nameField.setText(name);
		nameField.setEditable(false);

		VBox box = new VBox();
		box.getChildren().add(nameField);
		box.getChildren().add(textField);
		box.setAlignment(Pos.CENTER);
		this.setBottom(box);
		this.setIcon(icon, text);
	}

	public void setIcon(FontIconButton icon, String text) {
		this.setCenter(icon);
		icon.setOnAction(a -> {
			Map<DataFormat, Object> content = new HashMap<>();
			content.put(DataFormat.PLAIN_TEXT, text);
			Clipboard.getSystemClipboard().setContent(content);
		});
	}
}
