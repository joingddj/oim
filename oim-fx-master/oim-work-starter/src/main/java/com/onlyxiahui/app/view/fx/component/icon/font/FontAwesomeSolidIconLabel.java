package com.onlyxiahui.app.view.fx.component.icon.font;

/**
 * FontAwesome 图标按钮 <br>
 * use 使用方式:在FontAwesome中找到css,content: "\f6c3"; 的值上加上u 如 "\f6c3"->"\uf6c3"
 * setFontIcon("\uf6c3")<br>
 * Date 2020-03-12 10:16:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FontAwesomeSolidIconLabel extends FontIconLabel {

	public FontAwesomeSolidIconLabel() {
		initComponent();
	}

	private void initComponent() {
		this.setIconFont(FontAwesomeIcon.solidFont);
	}
}
