
package com.oimchat.app.fx.im.ui.classics.module.avatar;

import com.oimchat.app.fx.view.ui.classics.module.avatar.AvatarUploadStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Description <br>
 * Date 2021-03-05 10:28:00<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AvatarUploadStageTest extends Application {

	AvatarUploadStage stage = new AvatarUploadStage();

	@Override
	public void start(Stage primaryStage) {
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
