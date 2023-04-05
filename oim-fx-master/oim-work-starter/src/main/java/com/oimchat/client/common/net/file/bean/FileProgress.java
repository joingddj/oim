
package com.oimchat.client.common.net.file.bean;

/**
 * Description <br>
 * Date 2021-03-16 09:39:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FileProgress {

	long speed;
	long size;
	long finishSize;
	double progress;

	public FileProgress() {
		super();
	}

	public FileProgress(long speed, long size, long finishSize, double progress) {
		super();
		this.speed = speed;
		this.size = size;
		this.finishSize = finishSize;
		this.progress = progress;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getFinishSize() {
		return finishSize;
	}

	public void setFinishSize(long finishSize) {
		this.finishSize = finishSize;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}
}
