package com.onlyxiahui.app.view.fx.component.tab;

import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author XiaHui
 */
public class IconTab extends Tab {

	public IconTab() {
	}

	public IconTab(FontIconText icon) {
		this("", null, icon);
	}

	public IconTab(String text, FontIconText icon) {
		this(text, null, icon);
	}

	public IconTab(String text, Font font, FontIconText icon) {
		this(text, font, null, icon);
	}

	public IconTab(String text, Font font, Paint paint, FontIconText icon) {

		if (null != text && !"".equals(text)) {
			tabButton.setText(text);
		}
		if (null != font) {
			tabButton.setFont(font);
		}
		if (null != paint) {
			tabButton.setTextFill(paint);
		}
		if (null != icon) {
			iconButton.setGraphic(icon);
		}
		updateTab();
	}

	protected void updateTab() {
		Node n = iconButton.getGraphic();
		if (!this.isSelected()) {
			if (mouseEntered) {
				tabButton.setStyle("-fx-text-fill:-fx-accent");
			} else {
				tabButton.setStyle("-fx-text-fill:derive(-fx-text-inner-color,70%)");
			}
		} else {
			tabButton.setStyle("-fx-text-fill:-fx-accent");
		}

		if (null != n) {
			if (!this.isSelected()) {
				if (mouseEntered) {
					n.setStyle("-fx-fill:-fx-accent");
				} else {
					// -fx-box-border derive(-fx-control-inner-background,-30%)
					n.setStyle("-fx-fill:derive(-fx-text-inner-color,70%)");
				}
			} else {
				n.setStyle("-fx-fill:-fx-accent");
			}
		}
	}
}
