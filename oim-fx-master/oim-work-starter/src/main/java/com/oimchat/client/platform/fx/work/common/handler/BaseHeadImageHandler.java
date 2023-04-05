package com.oimchat.client.platform.fx.work.common.handler;

import java.net.URL;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.general.kernel.work.common.box.BaseDataBox;
import com.oimchat.client.platform.kernel.work.module.common.handler.AbstractHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.scene.image.Image;

/**
 * @author: XiaHui
 * @date: 2018-01-27 14:35:36
 */
public abstract class BaseHeadImageHandler<T extends BaseDataBox<URL>> extends AbstractHeadImageHandler<T> {

	public BaseHeadImageHandler(AppContext appContext) {
		super(appContext);
	}

	public void loadAvatarImage(String avatar, ValueAction<Image> back) {
		loadAvatarUrl(avatar, (url) -> {
			if (null != url) {
				Image image = ImageLoadUtil.getImageByUrl(url.toExternalForm());
				back.value(image);
			} else {
				back.value(null);
			}
		});
	}

	public void loadHeadImage(String head, ValueAction<Image> back) {
		if (null != back) {
			loadHeadUrl(head, (url) -> {
				Image image = null;
				if (null != url) {
					image = ImageLoadUtil.getImageByUrl(url.toExternalForm());
				}
				back.value(image);
			});
		}
	}

	public void loadAvatarImage(String head, String avatar, ValueAction<Image> back) {
		if (StringUtil.isNotBlank(avatar)) {
			loadAvatarImage(avatar, (img) -> {
				if (null == img) {
					loadHeadImage(head, back);
				} else {
					back.value(img);
				}
			});
		} else {
			loadHeadImage(head, back);
		}
	}
}
