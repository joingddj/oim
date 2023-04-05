
package com.onlyxiahui.app.view.fx.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * 
 * Image Util <br>
 * Date 2020-03-12 09:03:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ImageLoadUtil {

	public static Image getImageByClassPath(String classPath) {
		Image image = getImageByUrl(classPath, 0, 0, false, false, false);
		return image;
	}

	public static Image getImageByClassPath(String classPath, double w, double h) {
		Image image = getImageByUrl(classPath, w, h, false, false, false);
		return image;
	}

	public static Image getImageByClassPath(
			String classPath,
			double w,
			double h,
			boolean r,
			boolean smooth,
			boolean backgroundLoading) {
		Image image = getImageByUrl(classPath, w, h, r, smooth, backgroundLoading);
		return image;
	}

	/*************************/
	public static Image getImageByFilePath(String filePath) {
		return getImageByFilePath(filePath, 0, 0, false, false, false);
	}

	public static Image getImageByFilePath(String filePath, double w, double h) {
		return getImageByFilePath(filePath, w, h, false, false, false);
	}

	public static Image getImageByFilePath(
			String filePath,
			double w,
			double h,
			boolean r,
			boolean smooth,
			boolean backgroundLoading) {
		Image image = null;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			String url = file.toURI().toString();
			image = getImageByUrl(url, w, h, r, smooth, backgroundLoading);
		}
		return image;
	}

	/*******************************/

	public static Image toImage(byte[] bytes) {
		return toImage(bytes, 0, 0, false, false);
	}

	public static Image toImage(
			byte[] bytes,
			double w,
			double h,
			boolean r,
			boolean smooth) {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		return getImageByInputStream(is, w, h, r, smooth);
	}

	public static Image getImageByUrl(String url) {
		return getImageByUrl(url, 0, 0, false, false, false);
	}

	public static Image getImageByUrl(String url, double w, double h) {
		return getImageByUrl(url, w, h, false, false, false);
	}

	public static Image getImageByUrl(
			String url,
			double w,
			double h,
			boolean r,
			boolean smooth,
			boolean backgroundLoading) {
		Image image = new Image(url, w, h, r, smooth, backgroundLoading);
		return image;
	}

	public static Image getImageByInputStream(
			InputStream is,
			double w,
			double h,
			boolean r,
			boolean smooth) {
		Image image = null;
		try {
			image = new Image(is, w, h, r, smooth);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}
}
