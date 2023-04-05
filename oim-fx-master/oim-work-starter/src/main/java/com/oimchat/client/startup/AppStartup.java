package com.oimchat.client.startup;

import java.net.URL;

import javax.swing.UIManager;

import com.oimchat.app.fx.net.ImageURLStreamHandlerFactory;
import com.oimchat.client.platform.basic.constant.AppConstant;

import javafx.application.Application;

/**
 * 
 * Description 启动类<br>
 * Date 2020-11-19 10:52:52<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class AppStartup {

	public static void main(String[] args) {
		URL.setURLStreamHandlerFactory(new ImageURLStreamHandlerFactory());
		System.setProperty("logFilePath", AppConstant.getAppHomePath());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(AppStartup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		Application.launch(AppLauncher.class, args);
	}
}
