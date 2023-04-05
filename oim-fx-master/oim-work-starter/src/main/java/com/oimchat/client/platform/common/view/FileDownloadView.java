package com.oimchat.client.platform.common.view;

import com.oimchat.client.common.net.file.bean.FileData;
import com.onlyxiahui.app.basic.view.View;

/**
 * 描述： 添加好友或者加入群显示窗口
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public interface FileDownloadView extends View {

	public void download(FileData fileData);

}
