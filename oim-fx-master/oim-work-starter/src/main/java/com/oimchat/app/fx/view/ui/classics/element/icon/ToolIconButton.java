
package com.oimchat.app.fx.view.ui.classics.element.icon;

import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconButton;

/**
 * Description <br>
 * Date 2021-03-08 18:38:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ToolIconButton extends FontAwesomeSolidIconButton {

	public ToolIconButton() {
		initComponent();
	}

	private void initComponent() {
//		this.getStyleClass().clear();
//		this.getStyleClass().add("icon-button");
		this.setIconFontSize(20);
		//this.getFontIconText().setStyle("-fx-fill: -fx-accent;");
		this.setGraphicTextGap(1);
	}
}
