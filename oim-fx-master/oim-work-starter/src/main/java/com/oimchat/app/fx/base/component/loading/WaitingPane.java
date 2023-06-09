package com.oimchat.app.fx.base.component.loading;

import java.util.HashMap;
import java.util.Map;

import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author: XiaHui
 * @date: 2016年10月10日 上午11:47:48
 */
public class WaitingPane extends StackPane {

	private Map<String, WaitingData> map = new HashMap<String, WaitingData>();
	private VBox panel = new VBox();
	private ImageView imageView = new ImageView();
	private Label textLabel = new Label();

	public static String show_waiting = "waiting";
	public static String show_result = "result";

	public WaitingPane() {
		initComponents();
		initData();
	}

	private void initComponents() {
		this.getChildren().add(panel);

		panel.setAlignment(Pos.CENTER);
		panel.getChildren().add(imageView);
		panel.getChildren().add(textLabel);

	}

	private void initData() {
		add(show_waiting, "正在发送中,请稍等。", ImageLoadUtil.getImageByClassPath("/general/view/common/images/loading/loading_bar_140x9.gif"));
		add(show_result, "消息发送失败！", ImageLoadUtil.getImageByClassPath("/general/view/common/images/info/33x33/error.png"));
		show(show_waiting);
	}

	public void add(String key, String text, Image image) {
		WaitingData wd = map.get(key);
		if (null == wd) {
			wd = new WaitingData(text, image);
			map.put(key, wd);
		} else {
			wd.setImage(image);
			wd.setText(text);
		}
	}

	public void setText(String key, String text) {
		WaitingData wd = map.get(key);
		if (null != wd) {
			wd.setText(text);
		}
	}

	public void setImage(String key, Image image) {
		WaitingData wd = map.get(key);
		if (null != wd) {
			wd.setImage(image);
		}
	}

	public void show(String text, Image image) {
		this.imageView.setImage(image);
		this.textLabel.setText(text);
	}

	public void show(String key) {
		WaitingData wd = map.get(key);
		if (null != wd) {
			show(wd.getText(), wd.getImage());
		}
	}

	public class WaitingData {

		private String text;
		private Image image;

		public WaitingData(String text, Image image) {
			this.text = text;
			this.image = image;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}
	}
}
