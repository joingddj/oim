
package com.oimchat.client.general.kernel.work.module.im.data.setting;

/**
 * Description <br>
 * Date 2021-03-30 09:49:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageTimeSetting {
	/**
	 * 聊天时间合并时长
	 */
	private long mergeMillisecond = 1000 * 60 * 5;

	public long getMergeMillisecond() {
		return mergeMillisecond;
	}

	public void setMergeMillisecond(long mergeMillisecond) {
		this.mergeMillisecond = mergeMillisecond;
	}
}
