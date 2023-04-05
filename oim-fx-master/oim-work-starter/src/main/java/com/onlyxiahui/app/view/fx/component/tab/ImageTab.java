package com.onlyxiahui.app.view.fx.component.tab;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author XiaHui
 */
public class ImageTab extends Tab {

	ImageView imageView = new ImageView();
	Button imageButton = new Button();

	private Image normalImage = null;
	private Image hoverImage = null;
	private Image selectedImage = null;

	public ImageTab() {
	}

	public ImageTab(Image normalImage, Image hoverImage, Image selectedImage) {
		this("", null, null, normalImage, hoverImage, selectedImage);
	}

	public ImageTab(String text, Image normalImage, Image hoverImage, Image selectedImage) {
		this(text, null, null, normalImage, hoverImage, selectedImage);
	}

	public ImageTab(String text, Font font, Image normalImage, Image hoverImage, Image selectedImage) {
		this(text, font, null, normalImage, hoverImage, selectedImage);
	}

	public ImageTab(String text, Font font, Paint paint, Image normalImage, Image hoverImage, Image selectedImage) {
		this.normalImage = normalImage;
		this.hoverImage = hoverImage;
		this.selectedImage = selectedImage;

		if (null != text && !"".equals(text)) {
			imageButton.setText(text);
		}
		if (null != font) {
			imageButton.setFont(font);
		}
		if (null != paint) {
			imageButton.setTextFill(paint);
		}
		initComponent();
		updateTab();
		updateSelected();
	}

	private void initComponent() {
		imageButton.setGraphic(imageView);
		setSide(Side.BOTTOM);
	}

	protected void updateTab() {

		if (!this.isSelected()) {
			if (mouseEntered) {
				imageView.setImage(hoverImage);
			} else {
				imageView.setImage(normalImage);
			}
		} else {
			imageView.setImage(selectedImage);
		}
	}

	/**
	 * @return the normalImage
	 */
	public Image getNormalImage() {
		return normalImage;
	}

	/**
	 * @param normalImage the normalImage to set
	 */
	public void setNormalImage(Image normalImage) {
		this.normalImage = normalImage;
		updateTab();
	}

	/**
	 * @return the hoverImage
	 */
	public Image getHoverImage() {
		return hoverImage;
	}

	/**
	 * @param hoverImage the hoverImage to set
	 */
	public void setHoverImage(Image hoverImage) {
		this.hoverImage = hoverImage;
		updateTab();
	}

	/**
	 * @return the selectedImage
	 */
	public Image getSelectedImage() {
		return selectedImage;
	}

	/**
	 * @param selectedImage the selectedImage to set
	 */
	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
		updateTab();
	}
}
