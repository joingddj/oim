
package com.oimchat.client.general.kernel.work.module.system.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.client.general.kernel.work.module.common.store.AuthStore;
import com.oimchat.client.general.kernel.work.module.system.sender.SystemNetSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.wofa.client.net.core.back.DataBackActionEmptyAdapter;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-16 23:26:27<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class HeartbeatHandler extends AbstractMaterial {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 消息发送时间间隔(毫秒)
	private long intervalMaxTimestamp = 1000 * 30;
	private long intervalMinTimestamp = 1000 * 5;

	private long delayMaxTimestamp = 300 * 3;
	private long delayMinTimestamp = 30;

	private long sendTimestamp = 0;
	private long backTimestamp = 0;

	// 心跳本次发送间隔时间
	private long currentIntervalTimestamp = 1000 * 30;

	public HeartbeatHandler(AppContext appContext) {
		super(appContext);
	}

	public void heartbeat() {
		AuthStore as = appContext.getObject(AuthStore.class);
		long intervalTimestamp = System.currentTimeMillis() - backTimestamp;
		boolean over = intervalTimestamp >= currentIntervalTimestamp;
		if (over && as.isAuth()) {
			SystemNetSender sender = this.appContext.getObject(SystemNetSender.class);
			sendTimestamp = System.currentTimeMillis();
			sender.heartbeat(new DataBackActionEmptyAdapter() {
				@Back
				public void back(
						@Define("info") Info info,
						@Define("body.intervalMaxTimestamp") long maxInterval,
						@Define("body.intervalMinTimestamp") long minInterval,
						@Define("body.currentIntervalTimestamp") long currentInterval) {
					backTimestamp = System.currentTimeMillis();
					long responseTimestamp = backTimestamp - sendTimestamp;
					if (currentInterval > 0) {
						currentIntervalTimestamp = currentInterval;
					} else {
						if (responseTimestamp > delayMinTimestamp) {
							if (responseTimestamp > delayMaxTimestamp) {
								currentIntervalTimestamp = intervalMinTimestamp;
							} else {
								Double b = ((double) responseTimestamp) / ((double) delayMaxTimestamp);
								currentIntervalTimestamp = intervalMaxTimestamp - ((Double) (intervalMaxTimestamp * b)).longValue();
								if (currentIntervalTimestamp < intervalMinTimestamp) {
									currentIntervalTimestamp = intervalMinTimestamp;
								}
							}
						} else {
							currentIntervalTimestamp = intervalMaxTimestamp;
						}
					}
					// if (logger.isTraceEnabled()) {
					// logger.debug("心跳响应：sendTimestamp =" + sendTimestamp + "|backTimestamp=" +
					// backTimestamp + "|responseTimestamp=" + responseTimestamp +
					// "|currentIntervalTimestamp=" + currentIntervalTimestamp);
					// }
				}
			});
		}
	}
}
