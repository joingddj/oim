
package com.oimchat.app.fx.im.ui.classics.module.user;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.app.fx.view.ui.classics.module.user.UserEditPane;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Description <br>
 * Date 2021-03-05 11:47:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserEditPaneStageTest extends Application {

	UserEditPane pane = new UserEditPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(460);
		ts.setHeight(755);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(pane);

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
