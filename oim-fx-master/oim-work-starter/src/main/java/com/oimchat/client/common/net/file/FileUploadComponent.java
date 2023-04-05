package com.oimchat.client.common.net.file;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.onlyxiahui.aware.basic.net.file.FileHttpUpload;

/**
 * 
 * <br>
 * Date 2019-09-10 17:47:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileUploadComponent {

	String url;

	FileHttpUpload fhu = new FileHttpUpload();

	public String uploadFile(String url, File file) {
		return fhu.upload(url, file, null, null);
	}

	@SuppressWarnings("unchecked")
	public <T> T upload(String url, File file, Class<?> classType) {
		T d = null;
		try {
			String json = uploadFile(url, file);

			if (null != json && JSONObject.isValidObject(json)) {
				Object o = JSONPath.read(json, "body.data");
				if (o instanceof JSONObject) {
					JSONObject jo = (JSONObject) o;
					d = (T) jo.toJavaObject(classType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	public <T> T upload(File file, Class<?> classType) {
		return upload(getUrl(), file, classType);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
