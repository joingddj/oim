package com.oimchat.client.startup;

import com.oimchat.client.startup.initializer.AppInitializer;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 
 * Description <br>
 * Date 2020-11-18 11:53:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class AppLauncher extends Application {

	AppInitializer startup = new AppInitializer();

	@Override
	public void start(Stage primaryStage) throws Exception {
		javafx.scene.image.Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/logo/logo_64.png");
		primaryStage.getIcons().clear();
		primaryStage.getIcons().add(image);
		Platform.setImplicitExit(false);
	}
}
