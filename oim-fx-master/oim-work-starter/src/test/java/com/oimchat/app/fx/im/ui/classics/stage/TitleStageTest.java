
package com.oimchat.app.fx.im.ui.classics.stage;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.sun.javafx.css.StyleManager;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Description <br>
 * Date 2020-07-02 11:45:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TitleStageTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		Button b = new Button("gggg");
		b.getStyleClass().add("primary-button");

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(320);
		ts.setHeight(240);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(b);
		StyleManager.getInstance().addUserAgentStylesheet(TitleStageTest.class.getResource("/com/only/common/css/Only.css").toString());
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
