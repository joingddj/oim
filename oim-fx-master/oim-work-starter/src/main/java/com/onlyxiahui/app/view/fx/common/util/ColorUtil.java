package com.onlyxiahui.app.view.fx.common.util;

import java.util.Locale;

import javafx.scene.paint.Color;

/**
 * Description <br>
 * Date 2020-06-24 17:56:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ColorUtil {

	public static String colorToHex(Color c) {
		return String.format((Locale) null,
				"%02x%02x%02x",
				Math.round(c.getRed() * 255),
				Math.round(c.getGreen() * 255),
				Math.round(c.getBlue() * 255));
	}
}
