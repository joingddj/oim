package com.oimchat.client.platform.basic.interaction.sound;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 描述：播放wav声音文件
 * 
 * @author XiaHui
 * @date 2015年3月8日 下午10:30:51
 * @version 0.0.1
 */
public class SoundPlayer {

	private static final int BUFFER_SIZE = 1024;

	private SourceDataLine getLine(AudioFormat audioFormat) {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			res = (SourceDataLine) AudioSystem.getLine(info);
			res.open(audioFormat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void play(File file) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			play(ais);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play(URL url) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			play(ais);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play(AudioInputStream ais) {
		try {
			AudioFormat baseFormat = ais.getFormat();
			SourceDataLine line = getLine(baseFormat);
			line.start();
//			FloatControl control=(FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);   
//			System.out.println(control.getMaximum());
//			System.out.println(control.getMinimum());
//			control.setValue(-20);// newVal - the value of volume slider  
			int inBytes = 0;
			byte[] audioData = new byte[BUFFER_SIZE];
			while ((inBytes = ais.read(audioData, 0, BUFFER_SIZE)) > 0) {
				line.write(audioData, 0, inBytes);
			}
			line.drain();
			line.stop();
			line.close();
			ais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new Thread() {
			@Override
			public void run() {

				URL url = SoundPlayer.class.getResource("/general/resources/common/sound/inform_message.wav");
				SoundPlayer soundPlay = new SoundPlayer();
				soundPlay.play(url);
			}
		}.start();

//		
//		BasicPlayer player=new BasicPlayer();
//		player.play(file, 1);
	}
}
