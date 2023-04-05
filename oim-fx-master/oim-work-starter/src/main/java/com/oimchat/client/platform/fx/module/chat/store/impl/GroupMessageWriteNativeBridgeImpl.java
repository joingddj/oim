
package com.oimchat.client.platform.fx.module.chat.store.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.awt.font.util.FontUtil;
import com.oimchat.app.awt.image.util.AwtImageUtil;
import com.oimchat.app.fx.base.component.at.AtItemData;
import com.oimchat.app.fx.base.component.at.AtListPnae;
import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.base.component.chat.MessageWriteNativeBridge;
import com.oimchat.app.fx.base.component.chat.MessageWritePane;
import com.oimchat.app.fx.base.component.head.HeadSimpleItem;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberBox;
import com.oimchat.client.general.kernel.work.module.group.box.GroupMemberUserBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.platform.fx.module.group.function.GroupMemberListFunction;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.context.Material;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.security.Base64Util;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;;

/**
 * Description <br>
 * Date 2021-03-04 14:19:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMessageWriteNativeBridgeImpl implements MessageWriteNativeBridge, Material {

	AppContext appContext;
	MessageWritePane messageWritePane;

	AtListPnae atPane = new AtListPnae();
	String groupId;

	@Override
	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}

	public GroupMessageWriteNativeBridgeImpl(MessageWritePane messageWritePane) {
		this.messageWritePane = messageWritePane;
		MessageWriteMapper mapper = messageWritePane.getMapper();

		atPane.setOnSelected(v -> {
			if (null != v) {
				String userId = v.getKey();
				String text = v.getText();
				text = (null == text) ? "" : text;
				try {
					byte[] bytes = AwtImageUtil.drawTextImageBytes("@" + text, FontUtil.getDefaultFont(), new Color(98, 84, 216), 0.1F);
					String img = Base64Util.encrypt(bytes);
					String src = "data:image/png;base64," + img;
					mapper.insertAtUserImage(userId, text, src);
				} catch (Exception e) {
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
		String value = getTextValue(text, rangeStartOffset);
		if (null != value) {
			this.execute(() -> {
				List<AtItemData> list = find(value);
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
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(runnable);
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
			if (lastIndex > -1 && (lastIndex) < length) {
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

	private List<AtItemData> find(String text) {
		GroupMemberUserBox gmubox = this.appContext.getObject(GroupMemberUserBox.class);
		GroupMemberBox gmbox = this.appContext.getObject(GroupMemberBox.class);
		GroupMemberListFunction f = this.appContext.getObject(GroupMemberListFunction.class);

		if (null != text) {
			text = text.replace("@", "");
		}

		List<AtItemData> list = new ArrayList<>();
		AtItemData atAll = new AtItemData();
		atAll.setImage(null);
		atAll.setText("所有用户");
		atAll.setKey("");
		list.add(atAll);

		List<User> users = gmubox.getListByCategoryKey(groupId);
		int size = 0;
		for (User ud : users) {
			String userId = ud.getId();

			GroupMember gm = gmbox.get(groupId, userId);

			String remarkName = (null == gm) ? "" : gm.getNickname();
			String account = ud.getAccount();
			String email = ud.getEmail();
			String mobile = ud.getMobile();
			String name = ud.getName();
			String nickname = ud.getNickname();
			String number = ud.getNumber() + "";

			boolean mark = false;

			if (StringUtil.isBlank(text)) {
				mark = true;
			}

			if (null != account && !mark) {
				mark = (account.indexOf(text) != -1);
			}
			if (null != email && !mark) {
				mark = (email.indexOf(text) != -1);
			}
			if (null != mobile && !mark) {
				mark = (mobile.indexOf(text) != -1);
			}
			if (null != name && !mark) {
				mark = (name.indexOf(text) != -1);
			}
			if (null != nickname && !mark) {
				mark = (nickname.indexOf(text) != -1);
			}
			if (null != remarkName && !mark) {
				mark = (remarkName.indexOf(text) != -1);
			}
			if (null != number && !mark) {
				mark = (number.indexOf(text) != -1);
			}
			if (mark) {

				HeadSimpleItem item = f.getItem(groupId, userId);

				Image image = item.getHeadImage();
				AtItemData at = new AtItemData();
				at.setImage(image);
				at.setText(nickname);
				at.setKey(userId);
				list.add(at);
				size++;
			}

			if (size > 20) {
				break;
			}
		}
		return list;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
