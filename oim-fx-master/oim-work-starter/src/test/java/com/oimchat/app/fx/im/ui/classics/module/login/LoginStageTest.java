/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.login;

import java.util.HashSet;
import java.util.Set;

import com.oimchat.app.fx.view.ui.classics.module.login.LoginPane;
import com.oimchat.app.fx.view.ui.classics.module.login.LoginStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class LoginStageTest extends Application {
	LoginStage l = new LoginStage();

	@Override
	public void start(Stage primaryStage) {

		l.show();

		LoginPane lp = l.getLoginPane();

		Set<String> accounts = new HashSet<>();
		for (int i = 0; i < 100; i++) {
			accounts.add("1000" + i);
		}

		lp.setAccounts(accounts);
		lp.addAccountListener((o, ov, nv) -> {
			System.out.println("Account:" + nv);
		});

		lp.setOnAccountRemove((v) -> {
			System.out.println("Remove:" + v);
		});

		lp.setOnLoginAction((a) -> {
			lp.verify();
		});

		// l.showPopupPrompt("从未");
		// StyleManager.getInstance().
		// File file = new File("Resources/Video/Login/login.mp4");
		// File file = new File("Resources/Video/Login/Swf/1/main.swf");
		// l.setVideo(file);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
