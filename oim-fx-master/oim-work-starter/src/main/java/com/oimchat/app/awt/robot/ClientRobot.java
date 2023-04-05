
package com.oimchat.app.awt.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * Description <br>
 * Date 2021-03-25 10:20:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ClientRobot {

	private Robot robot;

	public ClientRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public boolean has() {
		return null != robot;
	}

	public BufferedImage getScreen(int x, int y, int width, int height) {
		return robot.createScreenCapture(new Rectangle(x, y, width, height));
	}
}
