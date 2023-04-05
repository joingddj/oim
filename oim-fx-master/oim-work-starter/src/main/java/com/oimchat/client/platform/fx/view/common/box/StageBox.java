
package com.oimchat.client.platform.fx.view.common.box;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.oimchat.app.fx.base.stage.BaseStage;
import com.onlyxiahui.app.view.fx.common.util.ColorUtil;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Description <br>
 * Date 2021-03-22 09:08:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class StageBox {

	private Color backgroundColor;
	private URL backgroundImageUrl;
	private URL logoImageUrl;
	private Color themeColor;

	private final Set<BaseStage> stageSet = new CopyOnWriteArraySet<>();

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public URL getBackgroundImageUrl() {
		return backgroundImageUrl;
	}

	public URL getLogoImageUrl() {
		return logoImageUrl;
	}

	public void setLogoImageUrl(URL logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}

	public void setBackgroundImageUrl(URL backgroundImageUrl) {
		this.backgroundImageUrl = backgroundImageUrl;
	}

	public Color getThemeColor() {
		return themeColor;
	}

	public void setThemeColor(Color themeColor) {
		this.themeColor = themeColor;
	}

	public Set<BaseStage> getStageSet() {
		return stageSet;
	}

	public void add(BaseStage baseFrame) {
		if (null != backgroundColor) {
			FxUtil.invoke(() -> {
				baseFrame.setBackgroundColor(backgroundColor);
			});
		}
		if (null != backgroundImageUrl && !"".equals(backgroundImageUrl.toExternalForm())) {
			FxUtil.invoke(() -> {
				baseFrame.setBackgroundImage(backgroundImageUrl);
			});
		}

		if (null != logoImageUrl && !"".equals(logoImageUrl.toExternalForm())) {
			Image image = ImageLoadUtil.getImageByUrl(logoImageUrl.toExternalForm());

			FxUtil.invoke(() -> {
				baseFrame.getIcons().clear();
				baseFrame.getIcons().add(image);
			});
		}

		if (null != themeColor) {
			FxUtil.invoke(() -> {
				Scene scene = baseFrame.getScene();
				if (null != scene) {
					Parent root = scene.getRoot();
					root.setStyle("-only-base-theme-color: #" + ColorUtil.colorToHex(themeColor));
				}
			});
		}
//			baseFrame.sceneProperty().addListener((o, ov, nv) -> {
//				if (null != nv) {
//					if (null != backgroundColor) {
//						// nv.setStyle("-fx-background-color:-fx-accent");
//					}
//				}
//			});

		stageSet.add(baseFrame);
	}

	public void update() {
		for (BaseStage baseFrame : stageSet) {
			if (null != backgroundColor) {
				FxUtil.invoke(() -> {
					baseFrame.setBackgroundColor(backgroundColor);
				});

			}
			if (null != backgroundImageUrl && !"".equals(backgroundImageUrl.toExternalForm())) {
				FxUtil.invoke(() -> {
					baseFrame.setBackgroundImage(backgroundImageUrl);
				});
			}
			if (null != logoImageUrl && !"".equals(logoImageUrl.toExternalForm())) {
				Image image = ImageLoadUtil.getImageByUrl(logoImageUrl.toExternalForm());

				FxUtil.invoke(() -> {
					baseFrame.getIcons().clear();
					baseFrame.getIcons().add(image);
				});
			}
		}
	}
}
