
package com.oimchat.client.platform.fx.common.config.theme.data;

/**
 * Description <br>
 * Date 2021-04-16 12:20:10<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ColorData {

	private double red = 1.0D;
	private double green = 1.0D;
	private double blue = 1.0D;
	private double opacity = 0.3D;

	public ColorData() {
		super();
	}

	public ColorData(double red, double green, double blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public ColorData(double red, double green, double blue, double opacity) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.opacity = opacity;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public double getRed() {
		return red;
	}

	public void setRed(double red) {
		this.red = red;
	}

	public double getGreen() {
		return green;
	}

	public void setGreen(double green) {
		this.green = green;
	}

	public double getBlue() {
		return blue;
	}

	public void setBlue(double blue) {
		this.blue = blue;
	}

}
