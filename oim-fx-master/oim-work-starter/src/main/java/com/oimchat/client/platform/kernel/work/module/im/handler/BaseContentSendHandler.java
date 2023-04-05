
package com.oimchat.client.platform.kernel.work.module.im.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.oimchat.client.basic.common.data.im.message.content.Item;
import com.oimchat.client.basic.common.data.im.message.content.item.ImageValue;
import com.oimchat.client.basic.util.FileNameUtil;
import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.common.net.file.FileDownloadComponent;
import com.oimchat.client.general.common.util.WebImagePathUtil;
import com.oimchat.client.general.kernel.support.file.upload.ImageUploader;
import com.oimchat.client.platform.kernel.work.common.file.FileHandleInfo;
import com.oimchat.client.platform.kernel.work.common.file.FileInfo;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.io.FileUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

/**
 * Description <br>
 * Date 2021-03-24 15:43:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseContentSendHandler extends AbstractMaterial {

	FileDownloadComponent d = new FileDownloadComponent();

	public BaseContentSendHandler(AppContext appContext) {
		super(appContext);
	}

	public void uploadImages(List<Item> items) {
		if (null != items) {
			final List<FileHandleInfo> handleList = new ArrayList<FileHandleInfo>();
			for (Item item : items) {
				if (Item.type_image.equals(item.getType())) {
					Object o = item.getValue();
					if (o instanceof ImageValue) {
						ImageValue iv = (ImageValue) o;
						String filePath = iv.getUrl();
						if (StringUtils.isNotBlank(filePath)) {
							String localPath = localPath();
							if (filePath.startsWith("file:")) {
								String absolutePath = WebImagePathUtil.fileImageSourceToPath(filePath);
								File file = new File(absolutePath);
								if (file.exists()) {
									upload(file, localPath, iv, handleList);
								}
							} else {
								download(filePath, localPath, iv);
							}
						}
					}
				}
			}
		}
	}

	private void upload(final File file, final String localPath, final ImageValue iv, final List<FileHandleInfo> handleList) {
		if (file.exists()) {
			final FileHandleInfo fileHandleInfo = new FileHandleInfo();
			final String fileName = file.getName();
			long size = file.length();

			ImageUploader iu = this.appContext.getObject(ImageUploader.class);

			iu.syncUpload(file,
					(p) -> {
					},
					(info, fd) -> {
						if (info.isSuccess()) {

							String id = fd.getId();
							String url = fd.getUrl();
//					String path = fd.getPath();

							String suffixName = FileNameUtil.getSuffixName(fileName);

							iv.setId(id);
							iv.setUrl(url);
							iv.setExtension(suffixName);
							iv.setType("1");
							iv.setName(fileName);
							iv.setSize(size);

							String saveFilePath = PathUtil.merge(localPath, Md5Util.lower32(url));
							FileUtil.checkOrCreateFile(saveFilePath);
							File newFile = new File(saveFilePath);
							try {
								FileUtils.copyFile(file, newFile);
							} catch (IOException e) {
								e.printStackTrace();
							}

							FileInfo fileInfo = new FileInfo();
							fileInfo.setFile(newFile);

							fileHandleInfo.setFileInfo(fileInfo);
							fileHandleInfo.setSuccess(true);
							handleList.add(fileHandleInfo);
						} else {

							FileInfo fileInfo = new FileInfo();
							fileInfo.setFile(file);
							fileHandleInfo.setFileInfo(fileInfo);
							fileHandleInfo.setSuccess(false);
							handleList.add(fileHandleInfo);
						}
					});
		}
	}

	private void download(final String url, final String localPath, final ImageValue iv) {
		if (StringUtil.isNotBlank(url)) {
			String name = Md5Util.lower32(url);
			String filePath = PathUtil.merge(localPath, name);
			File file = new File(filePath);
			if ((file.exists() && !file.isDirectory())) {
			} else {
				file = d.download(localPath, name, true, url);
			}

			if (null != file) {
				final String fileName = file.getName();
				long size = file.length();
				String suffixName = FileNameUtil.getSuffixName(fileName);

				iv.setUrl(url);
				iv.setExtension(suffixName);
				iv.setType("2");
				iv.setName(fileName);
				iv.setSize(size);
			}
		}
	}

	public abstract String localPath();
}
