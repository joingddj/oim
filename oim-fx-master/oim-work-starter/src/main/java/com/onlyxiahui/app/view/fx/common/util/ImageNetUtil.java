package com.onlyxiahui.app.view.fx.common.util;

import javafx.scene.image.Image;

/**
 * 
 * Description <br>
 * Date 2020-03-11 15:14:45<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ImageNetUtil {

	public static Image loadImage(String url) {
		Image image = ImageLoadUtil.getImageByUrl(url, 0, 0, false, false, true);
		return image;
	}

	public static Image loadImage(String url, double w, double h) {
		Image image = ImageLoadUtil.getImageByUrl(url, w, h, false, false, true);
		return image;
	}

	public static Image loadImage(
			String url,
			double w,
			double h,
			boolean r,
			boolean smooth,
			boolean backgroundLoading) {
		Image image = ImageLoadUtil.getImageByUrl(url, w, h, r, smooth, backgroundLoading);
		return image;
	}
}
