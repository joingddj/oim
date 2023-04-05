
package com.onlyxiahui.app.view.fx.component.icon.font;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.text.Font;

/**
 * Description <br>
 * Date 2021-03-26 09:59:09<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FontAwesomeIcon {

	static Font brandsFont;
	static Font regularFont;
	static Font solidFont;
	static {
		try {
			String TTF_PATH_1 = "/com/onlyxiahui/app/view/fx/default/font/icon/fontawesome/5.13.2/fa-brands-400.ttf";
			brandsFont = Font.loadFont(FontAwesomeIcon.class.getResource(TTF_PATH_1).openStream(), 14.0d);
			String TTF_PATH_2 = "/com/onlyxiahui/app/view/fx/default/font/icon/fontawesome/5.13.2/fa-regular-400.ttf";
			regularFont = Font.loadFont(FontAwesomeIcon.class.getResource(TTF_PATH_2).openStream(), 10.0d);
			String TTF_PATH_3 = "/com/onlyxiahui/app/view/fx/default/font/icon/fontawesome/5.13.2/fa-solid-900.ttf";
			solidFont = Font.loadFont(FontAwesomeIcon.class.getResource(TTF_PATH_3).openStream(), 10.0d);
		} catch (IOException ex) {
			Logger.getLogger(FontAwesomeIcon.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
