package com.oimchat.client.platform.kernel.work.module.inform.hanlder;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.oimchat.client.platform.basic.interaction.sound.SoundPlayer;
import com.oimchat.client.platform.common.inform.type.SoundType;

/**
 * @author XiaHui
 * @date 2015年3月10日 上午10:51:30
 */
public class SoundHandler {

	private SoundPlayer soundPlay = new SoundPlayer();
	private Map<Integer, URL> map = new HashMap<Integer, URL>();
	private Map<Integer, Long> timeMap = new HashMap<Integer, Long>();
	private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

	public SoundHandler() {
		map.put(SoundType.sound_type_message, SoundHandler.class.getResource("/general/resources/common/sound/inform_message.wav"));
		map.put(SoundType.sound_type_system, SoundHandler.class.getResource("/general/resources/common/sound/inform_system.wav"));
		map.put(SoundType.sound_type_shake, SoundHandler.class.getResource("/general/resources/common/sound/inform_shake.wav"));
		map.put(SoundType.sound_type_notify, SoundHandler.class.getResource("/general/resources/common/sound/inform_notify.wav"));
		Timer timer = new Timer();
		timer.schedule(new PromptTask(), 1000, 600);
	}

	class PromptTask extends TimerTask {

		@Override
		public void run() {
			if (queue.peek() != null) {
				Integer type = queue.poll();
				play(type);
			}
		}
	}

	public void put(int type) {
		queue.add(type);
	}

	public void play(int type) {
		Long time = timeMap.get(type);
		if (null == time || ((System.currentTimeMillis() - time) > 1500)) {
			URL file = map.get(type);
			if (null != file) {
				soundPlay.play(file);
			}
			timeMap.put(type, System.currentTimeMillis());
		}
	}

	public static void main(String[] args) {
		SoundHandler handler = new SoundHandler();

		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handler.put(SoundType.sound_type_system);
		}
	}
}
