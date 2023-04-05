
package com.onlyxiahui.app.fx.image;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Description <br>
 * Date 2021-03-13 10:54:31<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageShowStageTest extends Application {

	public Parent createContent() {

		BorderPane pane = new BorderPane();

		TextField text = new TextField("");
		Button button = new Button("确定");
		ImageView iv = new ImageView();

		pane.setTop(text);
		pane.setCenter(iv);
		pane.setBottom(button);

		button.setOnAction(a -> {
			String path = text.getText();
			
		});
		return pane;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.setWidth(480);
		primaryStage.setHeight(640);
		primaryStage.show();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
