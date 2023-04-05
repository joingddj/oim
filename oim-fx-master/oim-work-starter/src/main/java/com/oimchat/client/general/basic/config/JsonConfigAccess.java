/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.client.general.basic.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import com.oimchat.client.basic.util.TextFileUtil;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.utils.base.io.FileUtil;

/**
 * 
 * @author XiaHui
 */
public class JsonConfigAccess {

	public static <T> T getByFilePath(String path, Class<T> classType) {
		String json = TextFileUtil.getTextByFilePath(path);
		if (null == json) {
			json = (isArray(classType)) ? "[]" : "{}";
		}
		T t = JsonUtil.toObject(json, classType);
		return t;
	}

	public synchronized static void addOrUpdate(String filePath, Object o) {
		if (null != filePath && null != o) {
			String json = JsonUtil.toJson(o);
			saveJson(filePath, json);
		}
	}

	private static boolean saveJson(String filePath, String json) {
		boolean b = false;
		try {
			FileUtil.checkOrCreateFile(filePath);
			File file = new File(filePath);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(json);
			fileWriter.close();
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static boolean isArray(Class<?> classType) {
		boolean is = (null != classType && (Collection.class.isAssignableFrom(classType) || classType.isArray()));
		return is;
	}
}
