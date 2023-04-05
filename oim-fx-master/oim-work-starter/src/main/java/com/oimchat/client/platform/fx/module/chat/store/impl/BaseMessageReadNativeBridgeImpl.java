
package com.oimchat.client.platform.fx.module.chat.store.impl;

import com.oimchat.app.awt.desktop.util.LinkOpenUtil;
import com.oimchat.app.fx.base.component.chat.MessageReadMapper;
import com.oimchat.app.fx.base.component.chat.MessageReadNativeBridge;
import com.oimchat.client.common.net.file.bean.FileData;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.platform.common.view.FileDownloadView;
import com.oimchat.client.platform.fx.module.image.wrap.ImageViewWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-02 12:10:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseMessageReadNativeBridgeImpl extends AbstractMaterial implements MessageReadNativeBridge {

	protected MessageReadMapper readMapper;

	public BaseMessageReadNativeBridgeImpl(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void download(String messageKey, String itemKey, String url, String name, long size) {
//		System.out.println("download(" + messageKey + "," + itemKey + "," + url + ")");
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			FileData fd = new FileData();
			fd.setUrl(url);
			fd.setName(name);
			fd.setSize(size);
			fd.setPath(url);
			FileDownloadView view = this.appContext.getObject(FileDownloadView.class);
			view.download(fd);
		});
	}

	@Override
	public void showImage(String messageKey, String itemKey, String url) {
		// System.out.println("showImage(" + messageKey + "," + itemKey + "," + url +
		// ")");
		String html = readMapper.getImageTags();
		ImageViewWrap iv = this.appContext.getObject(ImageViewWrap.class);
		iv.setImageHtml(itemKey, html);
		iv.setVisible(true);
//		System.out.println(itemKey);
//		System.out.println(html);
	}

	@Override
	public void openUrl(String url) {
		// TODO Auto-generated method stub
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			LinkOpenUtil.open(url);
		});
	}

	@Override
	public void resend(String key) {
//		
	}

	@Override
	public void saveScrollTop(int top) {
	}

	@Override
	public void onScrollTop(int elementSize) {

	}

	public MessageReadMapper getReadMapper() {
		return readMapper;
	}

	public void setReadMapper(MessageReadMapper readMapper) {
		this.readMapper = readMapper;
	}
}
