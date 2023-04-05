package com.oimchat.client.common.net.file;

import java.io.File;

import com.onlyxiahui.aware.basic.net.file.FileHttpDownload;

/**
 * 
 * <br>
 * Date 2019-09-10 17:47:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileDownloadComponent {

	FileHttpDownload fhd = new FileHttpDownload();

	public File download(String path, String url) {
		return fhd.postDownload(path, url);
	}

	public File download(String path, String name, boolean onlyReplaceName, String url) {
		return fhd.postDownload(path, name, onlyReplaceName, url);
	}
}
