
package com.oimchat.app.fx.im.ui.classics.module.group;

import com.oimchat.app.fx.view.ui.classics.module.group.GroupEditStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Description <br>
 * Date 2021-04-02 10:21:11<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupEditStageTest extends Application {
	GroupEditStage stage = new GroupEditStage();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage.show();
	}
}
