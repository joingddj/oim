package com.onlyxiahui.app.view.fx.component.media;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * 
 * 简单视频播放面版 <br>
 * Date 2020-03-12 17:11:07<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class MediaSimplPane extends StackPane {

	private MediaPlayer mediaPlayer;
	private MediaView mediaView;

	public MediaSimplPane() {
		initPane();
	}

	private void initPane() {
		mediaView = new MediaView();
		getChildren().add(mediaView);
	}

	/**
	 * 设置播放视频文件地址<br>
	 * http地址直接使用<br>
	 * 本地视频需要用File("path").toURI().toURL().toString() <br>
	 * Date 2020-03-12 17:10:50<br>
	 * 
	 * @param url
	 * @since 1.0.0
	 */
	public void setUrl(String url) {
		mediaPlayer = new MediaPlayer(new Media(url));
		mediaPlayer.setAutoPlay(true);

		mediaPlayer.setCycleCount(-1);
		mediaView.setMediaPlayer(mediaPlayer);
	}

	/**
	 * 设置播放面板尺寸<br>
	 * Date 2020-03-12 17:10:24<br>
	 * 
	 * @param prefWidth
	 * @param prefHeight
	 * @since 1.0.0
	 */
	public void setMediaSize(double prefWidth, double prefHeight) {
		setPrefWidth(prefWidth);
		setPrefHeight(prefHeight);
		mediaView.relocate(0, 0);
		mediaView.setFitWidth(this.getWidth());
		mediaView.setFitHeight(this.getHeight());
	}

	@Override
	protected void layoutChildren() {
		mediaView.setFitWidth(this.getWidth());
		mediaView.setFitHeight(this.getHeight());
	}

	/**
	 * 停止播放<br>
	 * Date 2020-03-12 17:10:13<br>
	 * 
	 * @since 1.0.0
	 */
	public void stop() {
		if (null != mediaPlayer) {
			mediaPlayer.stop();
		}
	}

	/**
	 * 开始播放<br>
	 * Date 2020-03-12 17:10:06<br>
	 * 
	 * @since 1.0.0
	 */
	public void play() {
		if (null != mediaPlayer) {
			mediaPlayer.play();
		}
	}
}
