
package com.oimchat.client.platform.fx.common.config.theme;

import java.io.File;
import java.net.MalformedURLException;

import com.oimchat.client.general.basic.config.FileConfigAccess;
import com.oimchat.client.platform.basic.constant.AppConstant;
import com.onlyxiahui.app.view.fx.common.util.ColorUtil;
import com.sun.javafx.css.StyleManager;

import javafx.scene.paint.Color;

/**
 * Description <br>
 * Date 2021-03-26 11:50:22<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ThemeColorHandler {

	String path = "/config/global/theme/theme.css";

	public void update(Color color) {
		String filePath = getFilePath();
		String webColor = ColorUtil.colorToHex(color);
		StringBuilder sb = new StringBuilder();
		sb.append(".root {");
		sb.append("-only-base-theme-color:#");
		sb.append(webColor);
		sb.append(";");
		sb.append("}");
		FileConfigAccess.addOrUpdate(filePath, sb.toString());
		File file = new File(filePath);
		try {
			StyleManager.getInstance().addUserAgentStylesheet(file.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilePath() {
		return AppConstant.getAppHomePath() + path;
	}

	public static void main(String[] arg) {
		new ThemeColorHandler().update(Color.BLUE);
	}
}
