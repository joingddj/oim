
package com.onlyxiahui.app.view.fx.component.icon.font;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Font 图标 <br>
 * Date 2020-03-12 10:16:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FontIconText extends Text {

	private static final String DEFAULT_STYLE_CLASS = "font-icon-text";
	private Font iocnFont = Font.getDefault();

	public FontIconText() {
		initComponent();
		initEvent();
	}

	private void initComponent() {
		this.setFocusTraversable(false);
		this.getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	private void initEvent() {
	}

	public void setFontIcon(String icon) {
		setText(icon);
	}

	public String getIconFontFamily() {
		return iocnFont.getFamily();
	}

	public void setIconFontFamily(String iconFontFamily) {
		if (null != iconFontFamily && !iconFontFamily.isEmpty()) {
			Font t = Font.font(iconFontFamily, iocnFont.getSize());
			if (null != t) {
				iocnFont = t;
				updateIconFont();
			}
		}
	}

	public double getIconFontSize() {
		return iocnFont.getSize();
	}

	public void setIconFontSize(double iconFontSize) {
		if (iconFontSize > 0) {
			Font t = Font.font(iocnFont.getFamily(), iconFontSize);
			if (null != t) {
				iocnFont = t;
				updateIconFont();
			}
		}
	}

	public void setIconFont(Font font) {
		if (null != font) {
			iocnFont = font;
			updateIconFont();
		}
	}

	private void updateIconFont() {
		setFont(iocnFont);
	}
}
