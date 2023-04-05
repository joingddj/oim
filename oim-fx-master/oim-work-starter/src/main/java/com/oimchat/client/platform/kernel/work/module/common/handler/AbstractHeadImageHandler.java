package com.oimchat.client.platform.kernel.work.module.common.handler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.general.kernel.work.common.box.BaseDataBox;
import com.oimchat.client.platform.fx.work.common.access.AvatarLoadAccess;
import com.oimchat.client.platform.fx.work.common.data.AvatarInfo;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * @author: XiaHui
 * @date: 2018-01-27 14:35:36
 */
public abstract class AbstractHeadImageHandler<T extends BaseDataBox<URL>> extends AbstractMaterial {

	public AbstractHeadImageHandler(AppContext appContext) {
		super(appContext);
	}

	public abstract T getBox();

	public abstract String rootPath();

	public abstract URL getHeadUrl(String head);

	public void loadAvatarFile(String avatar, ValueAction<File> back) {
		if (null != back) {
			if (StringUtil.isNotBlank(avatar)) {
				String rootPath = rootPath();
				AvatarInfo ai = new AvatarInfo();
				ai.setKey(avatar);
				ai.setUrl(avatar);
				AvatarLoadAccess alh = this.appContext.getObject(AvatarLoadAccess.class);
				alh.syncLoad(rootPath, ai, (fi) -> {
					File file = fi.getFile();
					if (null != file) {
						back.value(file);
					} else {
						back.value(null);
					}
				});
			} else {
				back.value(null);
			}
		}
	}

	public void loadAvatarUrl(String avatar, ValueAction<URL> back) {
		loadAvatarFile(avatar, (file) -> {
			if (null != file) {
				try {
					back.value(file.toURI().toURL());
				} catch (MalformedURLException e) {
					e.printStackTrace();
					back.value(null);
				}
			} else {
				back.value(null);
			}
		});
	}

	public void loadHeadUrl(String head, ValueAction<URL> back) {
		if (null != back) {
			URL url = getHeadUrl(head);
			if (null == url) {
				url = getHeadUrl("1");
			}
			back.value(url);
		}
	}

	public void loadAvatarUrl(String head, String avatar, ValueAction<URL> back) {
		if (StringUtil.isNotBlank(avatar)) {
			loadAvatarUrl(avatar, (u) -> {
				if (null == u) {
					loadHeadUrl(head, back);
				} else {
					back.value(u);
				}
			});
		} else {
			loadHeadUrl(head, back);
		}
	}

	public URL getAvatarUrl(String id) {
		T box = getBox();
		return box.get(id);
	}

	public void loadAvatarUrl(String id, String head, String avatar, ValueAction<URL> back) {
		T box = getBox();
		if (StringUtil.isNotBlank(avatar)) {
			loadAvatarUrl(head, avatar, (u) -> {
				box.put(id, u);
			});
		}
	}

	public void getOrLoadAvatarUrl(String id, String head, String avatar, ValueAction<URL> back) {
		T box = getBox();
		URL url = box.get(id);
		if (null != url) {
			back.value(url);
		} else {
			loadAvatarUrl(id, head, avatar, back);
		}
	}

}
