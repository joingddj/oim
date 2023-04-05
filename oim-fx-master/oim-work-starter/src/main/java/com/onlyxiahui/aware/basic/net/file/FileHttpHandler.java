package com.onlyxiahui.aware.basic.net.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.onlyxiahui.aware.basic.net.file.action.FileAction;

/**
 * 
 * 
 * <br>
 * Date 2019-09-10 17:10:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileHttpHandler {

	public <T> void handel(InputStream in, OutputStream out, long size, FileAction<T> fileAction) throws IOException {
		byte[] bufferOut = new byte[1024];

		int length = 0;
		long speed = 0;
		long up = 0;
		long time = System.currentTimeMillis();
		while ((length = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, length);
			speed = speed + length;
			up = up + length;
			long tempTime = System.currentTimeMillis();
			if (tempTime - time > 1000) {
				double percentage = (up > 0 && size > 0) ? ((double) up / (double) size) : 1d;
				progress(fileAction, speed, size, up, percentage);
				speed = 0;
				time = tempTime;
			}
		}
		double percentage = (up > 0 && size > 0) ? ((double) up / (double) size) : 1D;
		progress(fileAction, speed, size, up, percentage);
	}

	private <T> void progress(FileAction<T> fileAction, long speed, long size, long finishSize, double progress) {
		if (null != fileAction) {
			fileAction.progress(speed, size, finishSize, progress);
		}
	}
}
