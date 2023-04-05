package com.oimchat.client.platform.fx.view.impl.classics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.oimchat.app.fx.view.ui.classics.module.theme.ThemeStage;
import com.oimchat.client.common.lib.util.file.ClassPathFileUtil;
import com.oimchat.client.platform.common.view.ThemeView;
import com.oimchat.client.platform.fx.common.config.theme.ThemeConfigAccess;
import com.oimchat.client.platform.fx.common.config.theme.data.ColorData;
import com.oimchat.client.platform.fx.common.config.theme.data.ThemeConfig;
import com.oimchat.client.platform.fx.view.common.box.StageBox;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.component.icon.IconButton;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class ThemeViewImpl extends BaseStageView<ThemeStage> implements ThemeView {

	URL backgroundImageUrl = null;

	public ThemeViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	private void initData() {

		ThemeConfigAccess acces = new ThemeConfigAccess();
		ThemeConfig theme = acces.get();
		// Color color = Color.color(theme.getRed(), theme.getGreen(), theme.getBlue(),
		// theme.getOpacity());
		ColorData backgroundColorData = theme.getBackgroundColorData();
		ColorData themeColorData = theme.getThemeColorData();
		Color backgroundColor = Color.color(backgroundColorData.getRed(), backgroundColorData.getGreen(), backgroundColorData.getBlue(), backgroundColorData.getOpacity());
		Color themeColor = Color.color(themeColorData.getRed(), themeColorData.getGreen(), themeColorData.getBlue(), themeColorData.getOpacity());

		double imageWidth = 130D;
		double imageHeight = 80D;

		List<Resource> files = ClassPathFileUtil.getResourceListByClassPath("/general/resources/common/images/wallpaper/*.jpg");

		List<IconButton> list = new ArrayList<IconButton>();

		for (Resource file : files) {

			try {
				URL url = file.getURL();
				String path = url.toExternalForm();
				Image normalImage = new Image(path, true);
				IconButton isb = new IconButton();
				isb.setNormalImage(normalImage);
				isb.setImageSize(imageWidth, imageHeight);
				isb.setOnAction(a -> {
					backgroundImageUrl = url;
					stage.setBackgroundImage(url);
				});
				list.add(isb);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FxUtil.invoke(() -> {
			stage.setImageStyleButtonList(list);
			stage.setBackgroundColor(backgroundColor);
			stage.setThemeColor(themeColor);
		});
	}

	private void clearData() {
		FxUtil.invoke(() -> {
			stage.setImageStyleButtonList(new ArrayList<>());
		});
	}

	private void save() {
		ThemeConfigAccess acces = new ThemeConfigAccess();
		ThemeConfig theme = acces.get();
		Color backgroundColor = stage.getBackgroundColor();
		Color themeColor = stage.getThemeColor();

		if (null != backgroundImageUrl && !"".equals(backgroundImageUrl.toExternalForm())) {
			theme.setBackgroundImageUrl(backgroundImageUrl.toExternalForm());
		}
		ColorData backgroundColorData = new ColorData(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getOpacity());
		ColorData themeColorData = new ColorData(themeColor.getRed(), themeColor.getGreen(), themeColor.getBlue());
		theme.setBackgroundColorData(backgroundColorData);
		theme.setThemeColorData(themeColorData);

		acces.save(theme);

		StageBox sb = appContext.getObject(StageBox.class);
		sb.setBackgroundColor(backgroundColor);
		sb.setThemeColor(themeColor);
		sb.setBackgroundImageUrl(backgroundImageUrl);
		sb.update();
	}

	@Override
	protected ThemeStage createStage() {
		return new ThemeStage();
	}

	@Override
	protected void initStage(ThemeStage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		stage.setDoneAction(a -> {
			stage.hide();
			execute(() -> {
				save();
			});
		});

		stage.showingProperty().addListener((o, ov, nv) -> {
			execute(() -> {
				if (nv) {
					initData();
				} else {
					clearData();
				}
			});
		});
	}
}
