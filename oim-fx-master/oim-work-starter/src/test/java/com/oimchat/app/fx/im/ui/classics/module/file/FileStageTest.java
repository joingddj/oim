/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.file;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.file.FileDownItem;
import com.oimchat.app.fx.view.ui.classics.module.file.FileUpItem;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class FileStageTest extends Application {
	ListRootPane listPane = new ListRootPane();

	@Override
	public void start(Stage primaryStage) {

		ClassicsTitleStage ts = new ClassicsTitleStage();
		ts.setWidth(320);
		ts.setHeight(240);
		ts.setTitle("测试");
		ts.show();
		ts.setCenter(listPane);

		String name = "123.mp4";
		String size = "134mb/450mb";
		String speed = "124kb/s";
		Image image = ImageLoadUtil.getImageByClassPath("/images/file/default.png");
		FileUpItem fui = new FileUpItem();
		fui.setName(name);
		fui.setImage(image);
		fui.setSize(size);
		fui.setSpeed(speed);
		fui.setProgress(25);
		listPane.addNode(fui);

		FileDownItem fdi = new FileDownItem();
		fdi.setName(name);
		fdi.setImage(image);
		fdi.setSize(size);
		fdi.setSpeed(speed);
		fdi.setProgress(25);
		fdi.showSaveAs(false);
		listPane.addNode(fdi);

		fdi = new FileDownItem();
		fdi.setName(name);
		fdi.setImage(image);
		fdi.setSize(size);
		fdi.setSpeed(speed);
		fdi.setProgress(25);
		fdi.showSaveAs(true);
		listPane.addNode(fdi);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
