package com.onlyxiahui.aware.basic.net.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.FileNameUtil;
import com.onlyxiahui.aware.basic.net.file.action.FileAction;
import com.onlyxiahui.common.utils.base.io.FileUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

/**
 * 
 * 
 * <br>
 * Date 2019-09-10 17:09:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileHttpDownload extends FileHttpHandler {

	String post = "POST";
	String get = "GET";

	public void download(String http, String savaPath, String fileName, FileAction<File> fileAction) {
		download(http, savaPath, fileName, false, fileAction);
	}

	public void download(String http, String savaPath, String fileName, boolean onlyReplaceName, FileAction<File> fileAction) {
		download(http, post, savaPath, fileName, false, fileAction);
	}

	public void download(String http, String method, String savaPath, String fileName, boolean onlyReplaceName, FileAction<File> fileAction) {
		boolean success = true;
		File file = null;
		OutputStream out = null;
		BufferedInputStream in = null;
		URLConnection urlConnection = null;
		try {
			// 统一资源
			URL url = new URL(http);
			// 连接类的父类，抽象类
			urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			if (post.equals(method) || get.equals(method)) {
				// 设定请求的方法，默认是GET
				httpUrlConnection.setRequestMethod(method);
			}
			// 设置字符编码
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpUrlConnection.connect();

			String disposition = httpUrlConnection.getHeaderField("Content-Disposition");
			in = new BufferedInputStream(httpUrlConnection.getInputStream());
			String saveName = getFileName(http, disposition, fileName, onlyReplaceName);

			String fileFullName = savaPath + saveName;
			// 文件大小
			int size = httpUrlConnection.getContentLength();

			FileUtil.createParentFolder(fileFullName);

			file = new File(fileFullName);
			out = new FileOutputStream(file);

			this.handel(in, out, size, fileAction);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			success = false;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != fileAction) {
				if (success) {
					fileAction.success(file);
				} else {
					fileAction.lost(file);
				}
			}
		}
	}

	public void download(String http, String filePath, FileAction<File> fileAction) {
		download(http, new File(filePath), fileAction);
	}

	public void download(String http, File saveFile, FileAction<File> fileAction) {

		boolean success = true;
		File file = null;
		OutputStream out = null;
		BufferedInputStream in = null;
		URLConnection urlConnection = null;
		try {
			// 统一资源
			URL url = new URL(http);
			// 连接类的父类，抽象类
			urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			// 设定请求的方法，默认是GET
			httpUrlConnection.setRequestMethod(post);
			// 设置字符编码
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpUrlConnection.connect();

			String absolutePath = saveFile.getAbsolutePath();
			// 文件大小
			int size = httpUrlConnection.getContentLength();
			in = new BufferedInputStream(httpUrlConnection.getInputStream());

			FileUtil.createParentFolder(absolutePath);

			out = new FileOutputStream(saveFile);
			this.handel(in, out, size, fileAction);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			success = false;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		if (out != null) {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (null != fileAction) {
			if (success) {
				fileAction.success(file);
			} else {
				fileAction.lost(file);
			}
		}
	}

	public static String getFileNameFromUrl(String url) {
		if (url == null) {
			return null;
		}

		try {
			String protocol = "//";
			String dot = "..";
			// Add a protocol if none found
			if (!url.contains(protocol)) {
				url = "http://" + url;
			}
			URL uri = new URL(url);
			String result = FileNameUtil.getName(uri.getPath());

			if (result == null || result.isEmpty()) {
				return null;
			}
			if (result.contains(dot)) {
				return null;
			}
			return result;
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public String getFileName(String http, String disposition, String fileName, boolean onlyReplaceName) {

		String originalName = null;
		String saveName = null;

		if (null != disposition && !disposition.isEmpty()) {
			String[] array = disposition.split("filename=");
			if (array.length > 1) {
				originalName = array[1];
			}
		}

		if (null == originalName || originalName.isEmpty()) {
			originalName = getFileNameFromUrl(http);
		}

		if (null != fileName && !fileName.isEmpty()) {
			saveName = fileName;
		} else {
			saveName = originalName;
		}

		if (null == saveName || saveName.isEmpty()) {
			saveName = FileNameUtil.createSaveName("");
		}

		boolean hasOriginalName = (null != originalName && !originalName.isEmpty());
		boolean hasNewName = (null != fileName && !fileName.isEmpty());

		if (onlyReplaceName && hasNewName && hasOriginalName) {

			String name = FileNameUtil.getSimpleName(saveName);
			String suffixName = FileNameUtil.getSuffixName(originalName);
			if (null != suffixName && !suffixName.isEmpty()) {
				saveName = name + "." + suffixName;
			}
		}
		return saveName;
	}

	public File postDownload(String path, String url) {
		List<File> files = new ArrayList<File>();
		FileAction<File> fileAction = new FileAction<File>() {

			@Override
			public void progress(long speed, long size, long finishSize, double percentage) {
			}

			@Override
			public void success(final File fileData) {
				files.add(fileData);
			}

			@Override
			public void lost(File file) {

			}

		};
		String name = Md5Util.lower32(url);
		download(url, "POST", path, name, true, fileAction);
		return (files.isEmpty()) ? null : files.get(0);
	}

	public File postDownload(String path, String name, boolean onlyReplaceName, String url) {
		List<File> files = new ArrayList<File>();
		FileAction<File> fileAction = new FileAction<File>() {

			@Override
			public void progress(long speed, long size, long finishSize, double percentage) {
			}

			@Override
			public void success(final File fileData) {
				files.add(fileData);
			}

			@Override
			public void lost(File file) {

			}

		};
		download(url, "POST", path, name, onlyReplaceName, fileAction);
		return (files.isEmpty()) ? null : files.get(0);
	}
}
