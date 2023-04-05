
package com.oimchat.app.fx.im.view.component.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.oimchat.app.awt.font.util.FontUtil;
import com.oimchat.app.awt.image.util.AwtImageUtil;
import com.oimchat.app.fx.base.component.at.AtItemData;
import com.oimchat.app.fx.base.component.at.AtListPnae;
import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.base.component.chat.MessageWriteNativeBridge;
import com.oimchat.app.fx.base.component.chat.MessageWritePane;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageNetUtil;
import com.onlyxiahui.common.utils.base.security.Base64Util;
import com.onlyxiahui.common.utils.base.util.thread.ThreadPoolBuilder;

import javafx.geometry.Bounds;;

/**
 * Description <br>
 * Date 2021-03-04 14:19:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageWriteNativeBridgeImpl implements MessageWriteNativeBridge {

	MessageWritePane messageWritePane;

	AtListPnae atPane = new AtListPnae();

	private final ExecutorService executor = ThreadPoolBuilder.build();

	public MessageWriteNativeBridgeImpl(MessageWritePane messageWritePane) {
		super();
		this.messageWritePane = messageWritePane;
		MessageWriteMapper mapper = messageWritePane.getMapper();

		atPane.setOnSelected(v -> {
			if (null != v) {
				System.out.println(v.getText());
				String userId = v.getKey();
				String text = v.getText();
				text = (null == text) ? "" : text;
				try {
					byte[] bytes = AwtImageUtil.drawTextImageBytes("@" + text, FontUtil.getDefaultFont(), new Color(98, 84, 216), 0.1F);
					String img = Base64Util.encrypt(bytes);
					String srv = "data:image/png;base64," + img;
					mapper.insertAtUserImage(userId, text, srv);
					
//					mapper.insertAtUser(userId, text );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// mapper.insertAtUser((null == userId) ? "" : userId, );
			}
			atPane.hide();
		});
	}

	@Override
	public void onInput(String text, double x, double y, int rangeStartOffset, int rangeEndOffset) {
		Bounds bounds = messageWritePane.getWebViewPane().localToScreen(messageWritePane.getWebViewPane().getBoundsInLocal());
		double sx = bounds.getMinX() + x + 10;
		double sy = bounds.getMinY() + y - 50;
		System.out.println(text + "|" + rangeStartOffset + "|" + rangeEndOffset);
		String value = getTextValue(text, rangeStartOffset);
		System.out.println("value:" + value);
		if (null != value) {

			this.execute(() -> {
				String avatarUrl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg";

				List<AtItemData> list = new ArrayList<>();

				AtItemData at = new AtItemData();
				at.setImage(null);
				at.setText("所有用户");
				at.setKey("");
				list.add(at);

				for (int i = 0; i < 100; i++) {
					at = new AtItemData();
					at.setImage(ImageNetUtil.loadImage(avatarUrl));
					at.setText("张" + i + "号");
					at.setKey(i + "");
					list.add(at);
				}
				atPane.setList(list);
				FxUtil.invoke(() -> {
					atPane.show(messageWritePane.getWebViewPane(), sx, sy);
				});
			});
		} else {
			if (atPane.isShowing()) {
				atPane.hide();
			}
		}
	}

	protected void execute(Runnable runnable) {
		try {
			executor.submit(runnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getTextValue(String text, int rangeStartOffset) {
		String value = null;
		String at = "@";
		if (null != text && !text.isEmpty()) {
			int length = text.length();
			if (rangeStartOffset < length) {
				text = text.substring(0, rangeStartOffset);
				length = text.length();
			}
			int lastIndex = text.lastIndexOf(at);
			if (lastIndex > -1 && lastIndex < length) {
				value = text.substring(lastIndex, length);
			}
		}
		return value;
	}

	// 根据str,font的样式以及输出文件目录
	public static void createImage(String str, Font font, Integer width, Integer height) throws Exception {
		// 创建图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setClip(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
		g.setColor(Color.red);// 在换成黑色
		g.setFont(font);// 设置画笔字体
		/** 用于获得垂直居中y */
		Rectangle clip = g.getClipBounds();
		FontMetrics fm = g.getFontMetrics(font);
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		int y = (clip.height - (ascent + descent)) / 2 + ascent;
		for (int i = 0; i < 6; i++) {// 256 340 0 680
			g.drawString(str, i * 680, y);// 画出字符串
		}
		g.dispose();
		// ImageIO.write(image, "png", outFile);// 输出png图片
	}

}
