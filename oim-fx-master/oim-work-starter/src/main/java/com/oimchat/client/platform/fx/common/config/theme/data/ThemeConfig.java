package com.oimchat.client.platform.fx.common.config.theme.data;

import javafx.scene.paint.Color;

/**
 * @author XiaHui
 * @date 2017年10月21日 下午1:27:57
 */
public class ThemeConfig {

	private String backgroundImageUrl;
	private ColorData backgroundColorData = new ColorData();
	private ColorData themeColorData = new ColorData();

	public ThemeConfig() {
		Color backgroundColor = Color.web("ffffff");
		Color themeColor = Color.web("6677fb");
		backgroundColorData = new ColorData(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
		themeColorData = new ColorData(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue());
		backgroundImageUrl = this.getClass().getResource("/general/resources/common/images/wallpaper/b_1.jpg").toExternalForm();
	}

	public String getBackgroundImageUrl() {
		return backgroundImageUrl;
	}

	public void setBackgroundImageUrl(String backgroundImageUrl) {
		this.backgroundImageUrl = backgroundImageUrl;
	}

	public ColorData getBackgroundColorData() {
		return backgroundColorData;
	}

	public void setBackgroundColorData(ColorData backgroundColorData) {
		this.backgroundColorData = backgroundColorData;
	}

	public ColorData getThemeColorData() {
		return themeColorData;
	}

	public void setThemeColorData(ColorData themeColorData) {
		this.themeColorData = themeColorData;
	}
}
