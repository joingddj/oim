
package com.oimchat.app.awt.image.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Description <br>
 * Date 2021-03-04 18:34:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AwtImageUtil {

	public static BufferedImage drawTextBufferedImage(String pressText, Font font, Color color, float alpha) {
		int fontSize = font.getSize();
		int width = fontSize * getLength(pressText) + fontSize;
		int height = fontSize + 2;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try {
			// String fontName = font.getFontName();
			// int fontStyle = font.getStyle();
			Graphics2D g = bufferedImage.createGraphics();
//			g.setFont(font);
//			g.setColor(color);
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
//
//			g.drawString(pressText, 0, 0);
//			g.dispose();

//			Graphics2D g2 = bufferedImage.createGraphics();
//			g2.setComposite(AlphaComposite.Src);
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2.setStroke(new BasicStroke(1));
//			g2.setComposite(AlphaComposite.SrcAtop);
//			g.drawString(pressText, 0, 0);
//			g.dispose();

			g.setClip(0, 0, width, height);

			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景

			g.setColor(color);// 在换成黑色
			g.setFont(font);// 设置画笔字体
			/** 用于获得垂直居中y */
			Rectangle clip = g.getClipBounds();
			FontMetrics fm = g.getFontMetrics(font);
			int ascent = fm.getAscent();
			int descent = fm.getDescent();
			int y = (clip.height - (ascent + descent)) / 2 + ascent;
			for (int i = 0; i < 6; i++) {// 256 340 0 680
				g.drawString(pressText, i * 680, y);// 画出字符串
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	public static byte[] drawTextImageBytes(String pressText, Font font, Color color, float alpha) {
		byte[] bytes = null;
		try {

			BufferedImage bufferedImage = drawTextBufferedImage(pressText, font, color, alpha);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
			bytes = outputStream.toByteArray();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	 * 
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	/*************************************************/
	public static BufferedImage imageToBufferImage(Image image) {
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.TRANSLUCENT;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			return bimage;
		}
		if (bimage == null) {
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		return bimage;
	}

	public static BufferedImage toRoundedCornerBufferedImage(BufferedImage image, int width, int height, int cornersWidth, int cornerHeight) {
		int w = image.getWidth();
		int h = image.getHeight();
		if (0 != width && 0 < width) {
			w = width;
		}
		if (0 != height && 0 < height) {
			h = height;
		}
		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = output.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(1));
		g2.fillRoundRect(0, 0, w, h, cornersWidth, cornerHeight);
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, w, h, null);
		g2.dispose();
		return output;
	}

	public static Image toRoundedCornerImage(Image image, int width, int height, int cornersWidth, int cornerHeight) {
		return toRoundedCornerBufferedImage(imageToBufferImage(image), width, height, cornersWidth, cornerHeight);
	}

	public static BufferedImage getRoundedCornerBufferedImageByUrl(URL url, int width, int height, int cornersWidth, int cornerHeight) {
		try {
			BufferedImage image = ImageIO.read(url);
			return toRoundedCornerBufferedImage(image, width, height, cornersWidth, cornerHeight);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Image getRoundedCornerImageByUrl(URL url, int width, int height, int cornersWidth, int cornerHeight) {
		Image image = getRoundedCornerBufferedImageByUrl(url, width, height, cornersWidth, cornerHeight);
		return image;
	}
}
