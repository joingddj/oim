
package com.oimchat.client.platform.fx.work.common.access;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.net.file.FileDownloadComponent;
import com.oimchat.client.platform.fx.work.common.data.AvatarInfo;
import com.oimchat.client.platform.kernel.work.common.file.FileInfo;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

/**
 * Description <br>
 * Date 2021-03-14 17:08:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AvatarLoadAccess extends AbstractMaterial {

	FileDownloadComponent d = new FileDownloadComponent();

	public AvatarLoadAccess(AppContext appContext) {
		super(appContext);
	}

	public void syncLoad(String rootPath, List<AvatarInfo> list, ValueAction<List<FileInfo>> back) {
		List<FileInfo> files = new ArrayList<>();
		for (AvatarInfo ai : list) {
			syncLoad(rootPath, ai, (fi) -> {
				files.add(fi);
			});
		}
		back.value(files);
	}

	public void syncLoad(String rootPath, AvatarInfo ai, ValueAction<FileInfo> back) {
		String key = ai.getKey();
		String url = ai.getUrl();
		FileInfo fi = new FileInfo();
		fi.setKey(key);

		if (StringUtil.isNotBlank(url)) {
			String name = Md5Util.lower32(url);
			String filePath = PathUtil.merge(rootPath, name);
			File file = new File(filePath);
			if ((file.exists() && !file.isDirectory())) {
				fi.setFile(file);
			} else {
				file = d.download(rootPath, name, false, url);
				fi.setFile(file);
			}
		}
		back.value(fi);
	}

	public String getPath(String rootPath, String url) {
		String path = PathUtil.merge(rootPath, Md5Util.lower32(url));
		return path;
	}
}
