package com.onlyxiahui.app.view.fx.component.icon;

import javafx.geometry.Pos;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * 
 * 菜单图标按钮 <br>
 * Date 2020-03-12 17:07:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class IconMenuButton extends MenuButton {

	private static final String DEFAULT_STYLE_CLASS = "icon-menu-button";

	private ImageView imageView = new ImageView();

	private Image normalImage = null;
	private Image hoverImage = null;
	private Image pressedImage = null;
	private boolean pressed = false;
	private boolean hover = false;

	private StackPane stackPane = new StackPane();
	private ImageView redImageView = new ImageView();

	public IconMenuButton() {
		initComponent();
		initEvent();
	}

	public IconMenuButton(Image normalImage) {
		this.normalImage = normalImage;
		initComponent();
		initEvent();
	}

	public IconMenuButton(String text, Image normalImage) {
		this.normalImage = normalImage;
		setText(text);
		initComponent();
		initEvent();
	}

	public IconMenuButton(Image normalImage, Image hoverImage, Image pressedImage) {
		this.normalImage = normalImage;
		this.hoverImage = hoverImage;
		this.pressedImage = pressedImage;
		initComponent();
		initEvent();
	}

	public IconMenuButton(String text, Image normalImage, Image hoverImage, Image pressedImage) {
		this.normalImage = normalImage;
		this.hoverImage = hoverImage;
		this.pressedImage = pressedImage;
		setText(text);
		initComponent();
		initEvent();
	}

	private void initComponent() {
		this.setFocusTraversable(false);
		// this.setGraphicTextGap(1);
		// this.setPadding(new Insets(3, 6, 3, 6));
		this.getStyleClass().clear();
		this.getStyleClass().add(DEFAULT_STYLE_CLASS);
		VBox redVBox = new VBox();
		redVBox.setAlignment(Pos.TOP_RIGHT);
		redVBox.getChildren().add(redImageView);

		stackPane.getChildren().add(imageView);
		stackPane.getChildren().add(redVBox);

		this.setGraphic(stackPane);
		updateImage();
	}

	private void initEvent() {

		this.hoverProperty().addListener(h -> {
			hover = this.hoverProperty().getValue();
			if (null != hoverImage && hover) {
				imageView.setImage(hoverImage);
			} else {
				imageView.setImage(normalImage);
			}
		});

		this.pressedProperty().addListener(p -> {
			pressed = this.hoverProperty().getValue();
			if (null != pressedImage && pressed) {
				imageView.setImage(pressedImage);
			} else {
				imageView.setImage(normalImage);
			}
		});
	}

	private void updateImage() {
		if (null != pressedImage && pressed) {
			imageView.setImage(pressedImage);
		} else if (null != hoverImage && hover) {
			imageView.setImage(hoverImage);
		} else {
			imageView.setImage(normalImage);
		}
	}

	public Image getNormalImage() {
		return normalImage;
	}

	public void setNormalImage(Image normalImage) {
		this.normalImage = normalImage;
		updateImage();
	}

	public Image getHoverImage() {
		return hoverImage;
	}

	public void setHoverImage(Image hoverImage) {
		this.hoverImage = hoverImage;
		updateImage();
	}

	public Image getPressedImage() {
		return pressedImage;
	}

	public void setPressedImage(Image pressedImage) {
		this.pressedImage = pressedImage;
		updateImage();
	}
}
