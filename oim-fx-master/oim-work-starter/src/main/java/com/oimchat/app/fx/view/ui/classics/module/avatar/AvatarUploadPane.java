
package com.oimchat.app.fx.view.ui.classics.module.avatar;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.onlyxiahui.app.fx.OnlyPopupOver;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.app.view.fx.component.image.ImageCropperPane;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * Description <br>
 * Date 2021-03-05 10:10:08<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AvatarUploadPane extends StackPane {

	BorderPane pane = new BorderPane();
	ImageCropperPane imagePane = new ImageCropperPane();

	OnlyPopupOver textOver = new OnlyPopupOver();
	Label textOverLabel = new Label();

	HBox buttonBox = new HBox();

	Button openButton = new Button();

	private FileChooser imageFileChooser;

	public AvatarUploadPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {

		imageFileChooser = new FileChooser();
		imageFileChooser.getExtensionFilters().add(new ExtensionFilter("图片文件", "*.png", "*.jpg", "*.bmp", "*.gif"));

		imagePane.setCoverSize(350, 350);

		openButton.setText("上传头像");

		buttonBox.setPadding(new Insets(12, 10, 12, 14));
		buttonBox.setSpacing(10);

		buttonBox.getChildren().add(openButton);

		pane.setTop(buttonBox);
		pane.setCenter(imagePane);

		Image coverImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/cropper/CircleMask.png");
		imagePane.setCoverImage(coverImage);

		this.getChildren().add(pane);
	}

	private void iniEvent() {
		openButton.setOnAction(a -> {
			openImage(AvatarUploadPane.this.getScene().getWindow());
		});
	}

	public boolean verify() {
		boolean mark = imagePane.hasImage();
		if (!mark) {
			textOverLabel.setText("请选择图片!");
			textOver.show(imagePane);
		}
		return mark;
	}

	public Image getImage() {
		return imagePane.getImage();
	}

	public void openImage(Window ownerWindow) {
		File file = imageFileChooser.showOpenDialog(ownerWindow);
		if (file != null) {
			if (file.exists()) {

				Image image = null;
				try {
					String pathString = file.toURI().toURL().toString();
					image = new Image(pathString, true);
					imagePane.setImage(image);
				} catch (MalformedURLException ex) {
					Logger.getLogger(ImageLoadUtil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}
