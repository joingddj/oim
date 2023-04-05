
package com.oimchat.client.general.basic.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.oimchat.client.basic.util.TextFileUtil;
import com.onlyxiahui.common.utils.base.io.FileUtil;

/**
 * Description <br>
 * Date 2021-03-26 11:43:55<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FileConfigAccess {

	public static String getTextByFilePath(String path) {
		String text = TextFileUtil.getTextByFilePath(path);
		return text;
	}

	public static boolean isExists(String path) {
		return new File(path).exists();
	}

	public synchronized static void addOrUpdate(String filePath, String text) {
		if (null != filePath && null != text) {
			saveText(filePath, text);
		}
	}

	private static boolean saveText(String filePath, String text) {
		boolean b = false;
		try {
			FileUtil.checkOrCreateFile(filePath);
			File file = new File(filePath);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(text);
			fileWriter.close();
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
}
