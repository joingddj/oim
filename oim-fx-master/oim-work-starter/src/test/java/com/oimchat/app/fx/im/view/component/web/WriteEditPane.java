
package com.oimchat.app.fx.im.view.component.web;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.base.component.chat.MessageWritePane;
import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Description <br>
 * Date 2021-03-03 11:14:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class WriteEditPane extends BorderPane {
	TilePane tilePane = new TilePane();

	int fontSize = 12;
	ColorPicker colorPicker = new ColorPicker(Color.BLACK);

	Button button = new Button("插入");

	Button insertImageButton = new Button("插入图片");
	ComboBox<String> fontFamilyComboBox = new ComboBox<String>();
	Button fontSizeA = new Button("fontSize+");
	Button fontSizeD = new Button("fontSize-");
	Button boldButton = new Button("bold");
	Button underlineButton = new Button("underline");
	Button italicButton = new Button("italic");
	Button htmlButton = new Button("html");

	Button contentButton = new Button("内容");

	MessageWritePane writePane = new MessageWritePane();
	MessageWriteMapper mapper = writePane.getMapper();

	public WriteEditPane() {

		init();
		initEvent();
	}

	private void init() {
		insertImageButton.setFocusTraversable(false);
		fontFamilyComboBox.setFocusTraversable(false);
		button.setFocusTraversable(false);
		fontSizeA.setFocusTraversable(false);
		fontSizeD.setFocusTraversable(false);
		boldButton.setFocusTraversable(false);
		underlineButton.setFocusTraversable(false);
		italicButton.setFocusTraversable(false);
		htmlButton.setFocusTraversable(false);

		tilePane.setPrefColumns(3); // preferred columns
		tilePane.setAlignment(Pos.CENTER);

		tilePane.getChildren().add(insertImageButton);
		tilePane.getChildren().add(button);
		tilePane.getChildren().add(colorPicker);
		tilePane.getChildren().add(fontSizeA);
		tilePane.getChildren().add(fontFamilyComboBox);
		tilePane.getChildren().add(fontSizeD);
		tilePane.getChildren().add(boldButton);
		tilePane.getChildren().add(underlineButton);
		tilePane.getChildren().add(italicButton);
		tilePane.getChildren().add(htmlButton);
		tilePane.getChildren().add(contentButton);

		ObservableList<String> fonts = new ObservableListWrapper<String>(new ArrayList<String>()); // FXCollections.observableArrayList(Font.getFamilies());
		fontFamilyComboBox.setItems(fonts);

		fonts.add("宋体");
		fonts.add("小篆");
		fonts.add("Microsoft YaHei");
		fonts.add("Helvetica");
		fonts.add("TimesRoman");
		fonts.add("Courier");
		fonts.add("Helvetica");
		fonts.add("TimesRoman");

		this.setTop(tilePane);
		this.setCenter(writePane);

		writePane.getWebViewPane().setBackgroundColor(Color.rgb(255, 255, 255, 0.95));
	}

	private void initEvent() {
		colorPicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				Color newColor = colorPicker.getValue();
				mapper.setColor(newColor);
			}
		});

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// mapper.insert("hhhh");
			}
		});

		fontFamilyComboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String font = fontFamilyComboBox.getValue().toString();
				System.out.println(font);
				if (null != font) {
					mapper.setFontName(font);
				}
			}

		});

		fontFamilyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			mapper.setFontName(newValue);
		});
		fontSizeA.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fontSize++;
				String size = fontSize + "px";
				System.out.println(size);
				mapper.setFontSize(fontSize);
			}
		});

		fontSizeD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fontSize--;
				String size = fontSize + "px";
				System.out.println(size);
				mapper.setFontSize(fontSize);
			}
		});

		boldButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mapper.setBold(!mapper.isBold());
			}
		});

		underlineButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mapper.setUnderline(!mapper.isUnderline());
			}
		});

		italicButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mapper.setItalic(!mapper.isItalic());
			}
		});

		htmlButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(mapper.getHtml());
			}
		});
		insertImageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				insertImage();
			}
		});

		contentButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(mapper.getMessageContent());
			}
		});

//		writePane.getWebViewPane().getWebView().setOnInputMethodTextChanged(ime -> {
//			// ime.consume();
//			String text = ime.getCommitted();
//			// System.out.println(text);
//			mapper.insertAtCursorHtml(text);
//			text = writePane.getWebViewPane().getWebPage().getClientSelectedText();
//			System.out.println(text);
//		});

	}

	public void insertImage() {
		String tag = "img";

		Map<String, String> map = new HashMap<>();
		map.put("alt", "Embedded Image");
		map.put("src",
				"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAEeSURBVDhPbZFba8JAEIXz//9BoeCD4GNQAqVQBUVKm97UeMFaUmvF1mqUEJW0NXV6mtlL3PRwILuz52NnJxax5iGdt8l2TaP4shCZVBI4ezCjypVbkUklASN07NftlzMNh2GMYA6Iv0UFksWiH5w8Lk5HS9QkgHv5GAtuGq+SAKIAYJQl4E3UMTyYBLFzp7at1Q7p5scGQQlAszVdeJwYV7sqTa2xCKTKAET+dK1zysfSAEZReFqaadv1GsNi/53fAFv8KT2vkMYiKd8YADty7ptXvgaU3cuREc26Xx+YQPUtQnud3kznapkB2K4JJIcDgOBzr3uDkh+6Fjdb9XmUBf6OU3Fv6EHsIcy91hVTygP5CksA6uejGa78DxD9ApzMoGHun6uuAAAAAElFTkSuQmCC");
		String html = "<img alt=\"Embedded Image\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAEeSURBVDhPbZFba8JAEIXz//9BoeCD4GNQAqVQBUVKm97UeMFaUmvF1mqUEJW0NXV6mtlL3PRwILuz52NnJxax5iGdt8l2TaP4shCZVBI4ezCjypVbkUklASN07NftlzMNh2GMYA6Iv0UFksWiH5w8Lk5HS9QkgHv5GAtuGq+SAKIAYJQl4E3UMTyYBLFzp7at1Q7p5scGQQlAszVdeJwYV7sqTa2xCKTKAET+dK1zysfSAEZReFqaadv1GsNi/53fAFv8KT2vkMYiKd8YADty7ptXvgaU3cuREc26Xx+YQPUtQnud3kznapkB2K4JJIcDgOBzr3uDkh+6Fjdb9XmUBf6OU3Fv6EHsIcy91hVTygP5CksA6uejGa78DxD9ApzMoGHun6uuAAAAAElFTkSuQmCC\" />";

		System.out.println(writePane.getWebViewPane().getWebView().isFocused());

		if (!writePane.getWebViewPane().getWebView().isFocused()) {
			writePane.getWebViewPane().getWebView().requestFocus();
		}
		mapper.insertAtCursorHtml(html);
	}
}
