/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.image;

import com.oimchat.app.fx.view.ui.classics.module.image.ImagePreviewStage;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class ImagePreviewStageTest extends Application {

	ImagePreviewStage auf = new ImagePreviewStage();

	@Override
	public void start(Stage primaryStage) {
		auf.show();
		Image image = ImageLoadUtil.getImageByClassPath("/test/images/wallpaper/default.jpg");
		auf.setImage(image);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
