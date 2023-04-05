/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlyxiahui.app.view.fx.component.icon.font;

import com.oimchat.client.basic.util.TextFileUtil;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.StringUnicodeUtil;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author XiaHui
 */
public class MaterialIconButtonStage extends Stage {

	BorderPane rootPane = new BorderPane();
	ScrollPane cropperScrollPane = new ScrollPane();
	FlowPane iconsPane = new FlowPane(3, 3);

	public MaterialIconButtonStage() {
		init();
		new Thread(() -> {
			initData();
		}).start();
	}

	private void init() {
		this.setScene(new Scene(rootPane, 500, 500));
		this.setTitle("组件测试");

		cropperScrollPane.setBackground(Background.EMPTY);
		cropperScrollPane.setContent(iconsPane);

		rootPane.setCenter(cropperScrollPane);
	}

	private void initData() {

		String TTF_PATH = "/com/onlyxiahui/app/view/fx/default/font/icon/mi/4.0.0/MaterialIcons-Regular.codepoints";
		String text = TextFileUtil.getTextByClassPath(TTF_PATH);
		// System.out.println(text);

		if (null != text) {
			String[] lines = text.split("\n");
			for (String line : lines) {
				if (null != line) {
					String[] array = line.split(" ");
					if (array.length > 1) {
						String name = array[0];
						String code = array[1];

						System.out.println("name:" + name + "/" + "code:" + code);

						String unicode = "\\u" + code;

						FontIconButton icon = new MaterialIconButton();
						icon.setIconFontSize(30.2D);
						icon.setFontIcon(StringUnicodeUtil.unicodeStr2String(unicode));
						FxUtil.invoke(() -> {
							iconsPane.getChildren().add(new IconView(icon, name, unicode));
						});
					}
				}
			}
		}
	}
}
