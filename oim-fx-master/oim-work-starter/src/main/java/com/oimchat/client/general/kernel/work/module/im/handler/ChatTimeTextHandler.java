
package com.oimchat.client.general.kernel.work.module.im.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oimchat.client.general.kernel.work.module.im.data.setting.MessagePastTimeFormat;
import com.oimchat.client.general.kernel.work.module.im.data.setting.MessageTimeSetting;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;
import com.onlyxiahui.common.utils.base.util.time.LocalDateUtil;

/**
 * Description <br>
 * Date 2021-03-30 09:41:52<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatTimeTextHandler extends AbstractMaterial {

	MessageTimeSetting messageTimeSetting;
	final List<MessagePastTimeFormat> messageTimeFormatList = new ArrayList<>();
	final List<MessagePastTimeFormat> itemTimeFormatList = new ArrayList<>();

	public ChatTimeTextHandler(AppContext appContext) {
		super(appContext);
		init();
	}

	private void init() {
		messageTimeSetting = new MessageTimeSetting();

		messageTimeFormatList.add(new MessagePastTimeFormat(0, LocalDateUtil.FORMAT_TIME));
		messageTimeFormatList.add(new MessagePastTimeFormat(1000L * 60 * 60 * 24, "MM-dd HH:mm:ss"));
		messageTimeFormatList.add(new MessagePastTimeFormat(1000L * 60 * 60 * 24 * 365, LocalDateUtil.FORMAT_DATE_TIME));

		itemTimeFormatList.add(new MessagePastTimeFormat(0, LocalDateUtil.FORMAT_TIME));
		itemTimeFormatList.add(new MessagePastTimeFormat(1000L * 60 * 60 * 24, "mm-dd hh"));
		itemTimeFormatList.add(new MessagePastTimeFormat(1000L * 60 * 60 * 24 * 365, "yy-mm-dd"));

	}

	public boolean timeVisible(Long currentTimestamp, Long lastTimestamp) {
		currentTimestamp = currentTimestamp == null ? System.currentTimeMillis() : currentTimestamp;
		lastTimestamp = lastTimestamp == null ? 0 : lastTimestamp;
		long mergeMillisecond = messageTimeSetting.getMergeMillisecond();
		long intervalMillisecond = (currentTimestamp - lastTimestamp);
		return (intervalMillisecond > mergeMillisecond);
	}

	public String timeText(Long timestamp) {
		long dateTimestamp = System.currentTimeMillis();
		long durationMillisecond = (dateTimestamp - timestamp);
		String format = getMessagePastTimeFormatValue(durationMillisecond);
		Date date = new Date(timestamp);
		String time = DateUtil.format(date, format);
		return time;
	}

	public String timeItem(Long timestamp) {
		long dateTimestamp = System.currentTimeMillis();
		long durationMillisecond = (dateTimestamp - timestamp);
		String format = getItemPastTimeFormatValue(durationMillisecond);
		Date date = new Date(timestamp);
		String time = DateUtil.format(date, format);
		return time;
	}

	public MessageTimeSetting getMessageTimeSetting() {
		return messageTimeSetting;
	}

	public void setMessageTimeSetting(MessageTimeSetting messageTimeSetting) {
		this.messageTimeSetting = messageTimeSetting;
	}

	public List<MessagePastTimeFormat> getMessageTimeFormatList() {
		return messageTimeFormatList;
	}

	public List<MessagePastTimeFormat> getItemTimeFormatList() {
		return itemTimeFormatList;
	}

	public String getPastTimeFormatValue(List<MessagePastTimeFormat> timeFormatList, long durationMillisecond, String format) {
		long tempMillisecond = -1;
		for (MessagePastTimeFormat v : timeFormatList) {
			long millisecond = v.getMillisecond();
			if (durationMillisecond >= millisecond && millisecond > tempMillisecond) {
				if (StringUtil.isNotBlank(v.getFormat())) {
					format = v.getFormat();
					tempMillisecond = millisecond;
				}
			}
		}
		return format;
	}

	public String getMessagePastTimeFormatValue(long durationMillisecond) {
		return getPastTimeFormatValue(messageTimeFormatList, durationMillisecond, LocalDateUtil.FORMAT_DATE_TIME);
	}

	public String getItemPastTimeFormatValue(long durationMillisecond) {
		return getPastTimeFormatValue(itemTimeFormatList, durationMillisecond, LocalDateUtil.FORMAT_TIME);
	}
}
