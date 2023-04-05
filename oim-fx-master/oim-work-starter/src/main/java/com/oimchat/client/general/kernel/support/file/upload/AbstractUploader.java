
package com.oimchat.client.general.kernel.support.file.upload;

import java.io.File;
import java.util.Map;

import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.net.file.bean.FileData;
import com.oimchat.client.common.net.file.bean.FileProgress;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.common.util.ServerAddressUtil;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerAddressData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.aware.basic.net.file.FileHttpUpload;
import com.onlyxiahui.aware.basic.net.file.action.FileAction;
import com.onlyxiahui.common.data.common.value.MessageValue;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.message.bean.Info;
import com.alibaba.fastjson.TypeReference;

/**
 * Description <br>
 * Date 2021-03-16 09:26:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractUploader extends AbstractMaterial {

	FileHttpUpload fhu = new FileHttpUpload();

	public AbstractUploader(AppContext appContext) {
		super(appContext);
	}

	public void asynUpload(File file, ValueAction<FileProgress> va, ValueBack<FileData> back) {
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			syncUpload(file, va, back);
		});
	}

	public void syncUpload(File file, ValueAction<FileProgress> va, ValueBack<FileData> back) {
		ServerAddressData address = getServerAddress();
		if (null == address || !address.isEnabled()) {
			String serverName = this.getServerName();
			String text = "没有可用的" + serverName + "！";
			Info info = new Info();
			info.addWarning("4.001", text);
			back.back(info, null);
		} else {
			String http = ServerAddressUtil.convertHttpUrl(address);
			String path = this.getPath();
			String url = PathUtil.merge(http, path);
			Map<String, String> dataMap = getParameterMap();

			FileAction<String> fileAction = new FileAction<String>() {

				@Override
				public void progress(long speed, long size, long finishSize, double progress) {
					va.value(new FileProgress(speed, size, finishSize, progress));
				}

				@Override
				public void success(String json) {

					MessageValue<FileData> mv = JsonUtil.toObject(json, new TypeReference<MessageValue<FileData>>() {
					});
					Info info = mv.getInfo();
					FileData fd = mv.getBody();
					back.back(info, fd);

				}

				@Override
				public void lost(String t) {
					Info info = new Info();
					info.addWarning("4.002", "上传失败！");
					back.back(info, null);
				}
			};
			fhu.upload(url, file, dataMap, fileAction);
		}
	}

	public abstract Map<String, String> getParameterMap();

	public abstract String getPath();

	public abstract ServerAddressData getServerAddress();

	public abstract String getServerName();
}
