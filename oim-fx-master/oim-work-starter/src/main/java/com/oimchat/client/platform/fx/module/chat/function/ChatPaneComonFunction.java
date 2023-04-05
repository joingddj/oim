
package com.oimchat.client.platform.fx.module.chat.function;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.oimchat.app.awt.robot.ClientRobot;
import com.oimchat.app.fx.base.component.face.FacePopup;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.common.util.HtmlContentUtil;
import com.oimchat.client.general.common.util.WebImagePathUtil;
import com.oimchat.client.platform.fx.view.common.build.IconButtonBuilder;
import com.oimchat.client.platform.kernel.work.common.env.PathEnv;
import com.oimchat.client.platform.kernel.work.module.im.box.FaceBox;
import com.oimchat.client.platform.kernel.work.module.im.manager.FaceManager;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.event.ValueEvent;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.app.view.fx.component.icon.IconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconButton;
import com.onlyxiahui.app.view.fx.component.screenshot.ScreenShotStage;
import com.onlyxiahui.common.utils.base.io.FileUtil;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;
import com.onlyxiahui.oim.face.bean.FaceCategory;
import com.onlyxiahui.oim.face.bean.FaceInfo;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * Description <br>
 * Date 2021-03-24 18:53:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatPaneComonFunction extends AbstractMaterial {

	protected FacePopup facePopup = new FacePopup();
	protected FileChooser imageFileChooser;
	protected ValueEvent<FaceInfo> faceSelectAction;
	protected ScreenShotStage sss;

	public ChatPaneComonFunction(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStore();
		});
		RunExecutor run = this.appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			initFace();
		});
	}

	private void initStore() {
		sss = new ScreenShotStage();
		imageFileChooser = new FileChooser();
		imageFileChooser.getExtensionFilters().add(new ExtensionFilter("图片文件", "*.png", "*.jpg", "*.bmp", "*.gif"));
		ClientRobot cr = new com.oimchat.app.awt.robot.ClientRobot();
		sss.setScreenImageBuilder(() -> {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage bufferedImage = cr.getScreen(0, 0, d.width, d.height);
			WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
			return writableImage;
		});
	}

	private void initFace() {
		FaceBox fb = appContext.getObject(FaceBox.class);
		List<FaceCategory> list = fb.getAllFaceCategoryList();
		for (FaceCategory fc : list) {
			if ("emoji".equals(fc.getId())) {
				set(fc.getId(), fc.getName(), fc.getFaceInfoList());
			}
		}
	}

	private void set(String key, String name, List<FaceInfo> list) {
		List<IconButton> faceList = new ArrayList<IconButton>();

		if (null != list && !list.isEmpty()) {
			for (FaceInfo data : list) {
				// String faceKey = data.getKey();
				String normalPath = data.getShowPath();
				String hoverPath = data.getRealPath();
				String text = data.getText();
				double width = data.getWidth();
				double height = data.getHeight();

				double imageWidth = data.getImageWidth();
				double imageHeight = data.getImageHeight();

				Image normalImage = ImageLoadUtil.getImageByClassPath(normalPath);
				Image hoverImage = ImageLoadUtil.getImageByClassPath(hoverPath);
				Tooltip tooltip = new Tooltip(text);

				IconButton button = new IconButton(normalImage, hoverImage, null);
				button.setTooltip(tooltip);

				if (width > 0 && height > 0) {
					button.setPrefSize(width, height);
					button.setMinSize(width, height);
				}

				if (imageWidth > 0 && imageHeight > 0) {
					button.setImageSize(imageWidth, imageHeight);
				}
				//

				button.setOnAction(a -> {
					facePopup.hide();
					if (null != faceSelectAction) {
						faceSelectAction.value(data);
					}
				});
				faceList.add(button);
			}
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				facePopup.set(key, name, faceList);
			}
		});
	}

	public void showFacePopup(Node owner, ValueEvent<FaceInfo> action) {
		faceSelectAction = (action);
		facePopup.show(owner);
	}

	public void insertFace(ChatPane cp, FaceInfo fi) {
		String tag = getFaceImageTag(fi);
		if (null != tag) {
			cp.getAreaPane().getWritePane().getMapper().insertAtCursorHtml(tag);
		}
	}

	public String getFaceImageTag(FaceInfo fi) {
		String categoryId = fi.getCategoryId();
		String key = fi.getKey();
		String value = categoryId + "," + key;
		String source = fi.getRealPath();
		String alt = fi.getText();
		Map<String, String> map = new HashMap<>();
		if (fi.getImageWidth() > 0 && fi.getImageHeight() > 0) {
			String style = "width:" + fi.getImageWidth() + "px;" + "height:" + fi.getImageHeight() + "px;";
			map.put("style", style);
		}
		String tag = HtmlContentUtil.getImageTag("", "face", value, source, alt, map);
		return tag;
	}

	public String getPicture(Node node) {
		Scene scene = node.getScene();
		Window w = (null == scene) ? null : scene.getWindow();
		String fullPath = null;
		if (null != w) {
			File file = imageFileChooser.showOpenDialog(w);
			if (file != null) {
				if (file.exists()) {
					fullPath = file.getAbsolutePath();
				}
			}
		}
		return fullPath;
	}

	public void insertWriteImage(ChatPane cp, String fullPath) {
		String path = WebImagePathUtil.pathToFileImageSource(fullPath);
		Map<String, String> map = new HashMap<>();
		map.put("style", "max-width: 80%; height: auto;");
		String tag = HtmlContentUtil.getImageTag("", "image", fullPath, path, "");
		cp.getAreaPane().getWritePane().getMapper().insertAtCursorHtml(tag);
	}

	public void saveWriteImage(ChatPane cp, Image image) {
		String fileName = DateUtil.getCurrent(DateUtil.YYYYMMDDHHMMSS) + ".png";
		PathEnv pm = appContext.getObject(PathEnv.class);
		String savePath = pm.getScreenshotSavePath();
		String fullPath = savePath + fileName;
		try {
			FileUtil.checkOrCreateFolder(fullPath);
			File file = new File(fullPath);
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			String path = WebImagePathUtil.pathToFileImageSource(fullPath);
			String tag = HtmlContentUtil.getImageTag("", "image", fullPath, path, "");
			cp.getAreaPane().getWritePane().getMapper().insertAtCursorHtml(tag);
		} catch (IOException ex) {
		}
	}

	public void setToolIcon(ChatPane cp) {
		FontIconButton faceButton = IconButtonBuilder.awesomeSolidIconButton("\uf118", "表情");

		FontIconButton cutButton = IconButtonBuilder.awesomeSolidIconButton("\uf0c4", "截图");

		FontIconButton imageButton = IconButtonBuilder.awesomeSolidIconButton("\uf03e", "发送图片");

		FxUtil.invoke(() -> {
			cp.addMiddleLeftTool(faceButton);
			cp.addMiddleLeftTool(cutButton);
			cp.addMiddleLeftTool(imageButton);
		});

		FxUtil.invoke(() -> {
			faceButton.setOnAction(a -> {
				showFacePopup(faceButton, (value) -> {
					if (null != value) {
						insertFace(cp, value);
					}
				});
			});
			cutButton.setOnAction(a -> {
				sss.setVisible(true);
				sss.setOnImageAction((image) -> {
					saveWriteImage(cp, image);
				});
			});
			imageButton.setOnAction(a -> {
				String fullPath = getPicture(cp);
				if (StringUtil.isNotBlank(fullPath)) {
					insertWriteImage(cp, fullPath);
				}
			});

			cp.getAreaPane().getWritePane().getEditHandler().setInputTextConverter((text) -> {
				FaceManager fm = appContext.getObject(FaceManager.class);
				text = fm.replaceEmojiToImageTag(text);
				// text = StringHtmlUtil.htmlEscape(text);
				return text;
			});
		});
	}
}
