
package com.oimchat.app.fx.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

/**
 * Description <br>
 * Date 2021-04-16 10:28:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageURLConnection extends URLConnection {

	protected ImageURLConnection(URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	private byte[] data;

	@Override
	public void connect() throws IOException {
		if (connected) {
			return;
		}
		loadImage();
		connected = true;
	}

	public String getHeaderField(String name) {
		if ("Content-Type".equalsIgnoreCase(name)) {
			return getContentType();
		} else if ("Content-Length".equalsIgnoreCase(name)) {
			return "" + getContentLength();
		}
		return null;
	}

	public String getContentType() {
		String fileName = getURL().getFile();
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		return "image/" + ext; // TODO: switch based on file-type
	}

	public int getContentLength() {
		return data.length;
	}

	public long getContentLengthLong() {
		return data.length;
	}

	public boolean getDoInput() {
		return true;
	}

	public InputStream getInputStream() throws IOException {
		connect();
		return new ByteArrayInputStream(data);
	}

	private void loadImage() throws IOException {
		if (data != null) {
			return;
		}
//		try {
//		int timeout = this.getConnectTimeout();
//		long start = System.currentTimeMillis();
		URL url = getURL();

		String imgPath = url.toExternalForm();
		imgPath = imgPath.startsWith("location://") ? imgPath.substring("location://".length()) : imgPath.substring("location:".length()); // attention: triple '/' is reduced to a single '/'

		// this is my own asynchronous image implementation
		// instead of this part (including the following loop) you could do your own
		// (synchronous) loading logic
//			MyImage img = ImageURLConnection.getImage(imgPath);
//			do {
//				if (img.isFailed()) {
//					throw new IOException("Could not load image: " + getURL());
//				} else if (!img.hasData()) {
//					long now = System.currentTimeMillis();
//					if (now - start > timeout) {
//						throw new SocketTimeoutException();
//					}
//					Thread.sleep(100);
//				}
//			} while (!img.hasData());
//			data = img.getData();
		loadImage(imgPath);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	private void loadImage(String imgPath) throws IOException {
		try {
			String ext = imgPath.substring(imgPath.lastIndexOf('.') + 1);
			BufferedImage read = ImageIO.read(new File(imgPath));
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(read, ext, os);
			data = os.toByteArray();
		} catch (IOException ioe) {
			System.out.println("IO exception: " + ioe);
		}
	}

	public OutputStream getOutputStream() throws IOException {
		// this might be unnecessary - the whole method can probably be omitted for our
		// purposes
		return new ByteArrayOutputStream();
	}

	public java.security.Permission getPermission() throws IOException {
		return null; // we need no permissions to access this URL
	}

}
