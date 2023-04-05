
package com.onlyxiahui.aware.basic.net.file;

import java.io.File;

import com.onlyxiahui.aware.basic.net.file.action.FileAction;

/**
 * Description <br>
 * Date 2021-03-27 20:42:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FileHttpUploadTest {

	public static void main(String[] args) {
		String url = "http://10.32.5.49:7000/v1/main/file/upload";
		File file = new File("G:\\Data\\Dev\\Eclipse\\eclipse-jee-2020-12-R-win32-x86_64.zip");
		FileHttpUpload fu = new FileHttpUpload();
		fu.upload(url, file, null, new FileAction<String>() {

			@Override
			public void success(String t) {
				System.out.println("success:" + t);
			}

			@Override
			public void progress(long speed, long size, long finishSize, double progress) {
				System.out.println("progress:" + progress + "   |speed:" + speed);
			}

			@Override
			public void lost(String t) {
				System.out.println("lost:" + t);
			}
		});
	}

}
