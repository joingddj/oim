package com.onlyxiahui.aware.basic.net.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import com.onlyxiahui.aware.basic.net.file.action.FileAction;

/**
 * 
 * 
 * 文件上传<br>
 * Date 2019-09-10 17:11:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileHttpUpload extends FileHttpHandler {

	/**
	 * 
	 * 
	 * <br>
	 * Date 2019-09-19 09:29:51<br>
	 * 
	 * @param http
	 * @param file
	 * @param dataMap
	 * @param fileAction
	 * @return String
	 * @since 1.0.0
	 */
	public String upload(String http, File file, Map<String, String> dataMap, FileAction<String> fileAction) {
		return upload(http, "", file, dataMap, fileAction);
	}

	/**
	 * 
	 * 
	 * <br>
	 * Date 2019-09-19 09:29:57<br>
	 * 
	 * @param http
	 * @param input
	 * @param file
	 * @param dataMap
	 * @param fileAction
	 * @return String
	 * @since 1.0.0
	 */
	public String upload(String http, String input, File file, Map<String, String> dataMap, FileAction<String> fileAction) {
		boolean success = false;
		String result = "";
		HttpURLConnection conn = null;
		DataInputStream in = null;
		OutputStream out = null;
		// boundary就是request头和上传文件内容的分隔符
		String boundary = "---------------------------123821742118716";
		try {
			URL url = new URL(http);
			conn = extracted(boundary, url);

			out = new DataOutputStream(conn.getOutputStream());
			// text
			putData(out, boundary, dataMap);

			// file
			if (file != null) {

				String inputName = (null == input || input.isEmpty()) ? "file" : input;
				String filename = file.getName();
				long size = file.length();

				StringBuffer sb = new StringBuffer();
				sb.append("\r\n").append("--").append(boundary).append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
				sb.append("Content-Type:application/octet-stream;charset=UTF-8" + "\r\n\r\n");

				out.write(sb.toString().getBytes());
				in = new DataInputStream(new FileInputStream(file));
				this.handel(in, out, size, fileAction);
			}

			byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
			out.write(endData);
			// 读取返回数据
			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			result = sb.toString();
			reader.close();
			reader = null;
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			if (conn != null) {
				// conn.disconnect();
				conn = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fileAction) {
				if (success) {
					fileAction.success(result);
				} else {
					fileAction.lost(result);
				}
			}
		}
		return result;
	}

	private HttpURLConnection extracted(String boundary, URL url) throws IOException, ProtocolException {
		HttpURLConnection conn;
		conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(30000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		return conn;
	}

	private void putData(OutputStream out, String boundary, Map<String, String> dataMap) {
		if (dataMap != null) {
			StringBuffer sb = new StringBuffer();
			Iterator<Map.Entry<String, String>> i = dataMap.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> entry = i.next();
				String inputName = (String) entry.getKey();
				String inputValue = (String) entry.getValue();
				if (inputValue == null) {
					continue;
				}
				sb.append("\r\n").append("--").append(boundary).append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
				sb.append(inputValue);
			}
			try {
				out.write(sb.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
