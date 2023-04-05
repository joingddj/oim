/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.client.platform.basic.constant;

/**
 * 
 * @author xh
 */
public class AppConstant {


	public static final String home = ".oim";
	public static final String charset = "UTF-8";

	public static String getUserHomePath() {
		return System.getProperty("user.home");
	}

	public static String getAppHomePath() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.home"));
		sb.append("/");
		sb.append(home);
		return sb.toString();
	}
}
