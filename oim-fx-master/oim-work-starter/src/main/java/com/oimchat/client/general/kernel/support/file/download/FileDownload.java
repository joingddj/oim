
package com.oimchat.client.general.kernel.support.file.download;

import java.io.File;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.net.file.bean.FileProgress;
import com.oimchat.client.general.common.task.RunExecutor;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.aware.basic.net.file.FileHttpDownload;
import com.onlyxiahui.aware.basic.net.file.action.FileAction;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-14 17:08:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FileDownload extends AbstractMaterial {

	FileHttpDownload fhd = new FileHttpDownload();

	public FileDownload(AppContext appContext) {
		super(appContext);
	}

	public void syncDownload(String url, File saveFile, ValueAction<FileProgress> va, ValueBack<File> back) {
		if (StringUtil.isNotBlank(url)) {
			fhd.download(url, saveFile, new FileAction<File>() {

				@Override
				public void success(File t) {
					Info info = new Info();
					back.back(info, t);
				}

				@Override
				public void progress(long speed, long size, long finishSize, double progress) {
					va.value(new FileProgress(speed, size, finishSize, progress));
				}

				@Override
				public void lost(File t) {
					Info info = new Info();
					info.addWarning("4.002", "下载失败！");
					back.back(info, null);
				}
			});
		}
	}

	public void asynDownload(String url, File saveFile, ValueAction<FileProgress> va, ValueBack<File> back) {
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			syncDownload(url, saveFile, va, back);
		});
	}
}
