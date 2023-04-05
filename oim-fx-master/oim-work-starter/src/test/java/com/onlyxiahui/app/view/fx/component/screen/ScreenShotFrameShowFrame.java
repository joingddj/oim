package com.onlyxiahui.app.view.fx.component.screen;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlyxiahui.app.fx.OnlyStage;
import com.onlyxiahui.app.view.fx.component.screenshot.ScreenShotStage;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import system.ClientRobot;

/**
 *
 * @author XiaHui
 */
public class ScreenShotFrameShowFrame extends OnlyStage {

	BorderPane rootPane = new BorderPane();
	VBox componentBox = new VBox();
	ScreenShotStage ss = new ScreenShotStage();

	public ScreenShotFrameShowFrame() {
		init();
	}

	private void init() {
		this.setCenter(rootPane);
		this.setTitle("组件测试");
		this.setWidth(380);
		this.setHeight(600);
		this.setRadius(10);

		VBox topBox = new VBox();
		topBox.setPrefHeight(50);
		rootPane.setTop(topBox);
		rootPane.setCenter(componentBox);

		Button b = new Button("截屏");
		componentBox.getChildren().add(b);

		ImageView iv = new ImageView();
		ScreenShotFrameShowFrame.this.backgroundImagePane.getChildren().add(iv);

		ss.setScreenImageBuilder(() -> {
			BufferedImage bufferedImage = ClientRobot.getScreen();
			WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
			return writableImage;
		});

		ss.setOnImageAction((image) -> {
			iv.setImage(image);
		});

		b.setOnAction(a -> {
			shot();
			// ss.setVisible(true);
		});
	}

	public void shot() {
//		Map<Integer, List<Integer>> map = new HashMap<>();
//		List<Screen> list = Screen.getScreens();
//		int x = 0;
//		int y = 0;
//		for (Screen s : list) {
//
//			if (s.getX() < x) {
//				x = s.getX();
//			}
//			if (s.getY() < y) {
//				y = s.getY();
//			}
//			System.out.println(s.getX() + "/" + s.getY() + "/" + s.getWidth() + "/" + s.getHeight());
//		}
//		System.out.println(x + "-" + y);
		Map<Double, Double> xHeightMap = new HashMap<>();
		Map<Double, Double> yWidthMap = new HashMap<>();

		List<Screen> list = Screen.getScreens();
		double x = 0;
		double y = 0;
		for (Screen s : list) {
			Rectangle2D bounds = s.getVisualBounds();
			double minX = bounds.getMinX();
			double minY = bounds.getMinY();

			double width = bounds.getWidth();
			double height = bounds.getHeight();

			Double w = xHeightMap.getOrDefault(minX, 0.0D);
			Double h = yWidthMap.getOrDefault(minY, 0.0D);

			xHeightMap.put(minX, w + width);
			yWidthMap.put(minY, h + height);

			if (minX < x) {
				x = minX;
			}
			if (minY < y) {
				y = minY;
			}
		}

		double w = 0;
		double h = 0;

		for (Double width : xHeightMap.values()) {
		}
		System.out.println(x + "-" + y);
	}
}
