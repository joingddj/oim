package com.onlyxiahui.app.view.fx.component.image;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * 
 * Description <br>
 * Date 2020-03-12 17:19:52<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ImageNode extends Button {

	private static final String DEFAULT_STYLE_CLASS = "image-node";

	private final ImageView imageView = new ImageView();
	private final Rectangle clip = new Rectangle();

	private Image normalImage = null;
	private boolean gray = false;
	Button button = new Button();

	public ImageNode() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		this.setGraphic(imageView);
		this.getStyleClass().remove("button");
		this.getStyleClass().add(DEFAULT_STYLE_CLASS);
		imageView.setClip(clip);
	}

	private void iniEvent() {
	}

	public void setImage(Image image) {
		this.normalImage = image;
		updateImage();
		imageView.setImage(normalImage);
	}

	public Image getImage() {
		return imageView.getImage();
	}

	public boolean isGray() {
		return gray;
	}

	public void setGray(boolean gray) {
		this.gray = gray;
		updateImage();
	}

	private void updateImage() {
		if (gray) {
			imageView.setOpacity(0.7);
		} else {
			imageView.setOpacity(1);
		}
	}

	/**
	 * 设置头像大小<br>
	 * Date 2020-03-12 17:20:00<br>
	 * 
	 * @param value
	 * @since 1.0.0
	 */
	public void setImageSize(double value) {
		imageView.setFitHeight(value);
		imageView.setFitWidth(value);

		clip.setWidth(value);
		clip.setHeight(value);
	}

	public void setImageRadius(double value) {
		clip.setArcHeight(value);
		clip.setArcWidth(value);
	}
}
