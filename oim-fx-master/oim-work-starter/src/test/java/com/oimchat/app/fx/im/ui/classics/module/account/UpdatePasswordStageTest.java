/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.fx.im.ui.classics.module.account;

import com.oimchat.app.fx.view.ui.classics.module.account.UpdatePasswordStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class UpdatePasswordStageTest extends Application {
	UpdatePasswordStage l=  new UpdatePasswordStage();
    @Override
    public void start(Stage primaryStage) {
    	
        l.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
