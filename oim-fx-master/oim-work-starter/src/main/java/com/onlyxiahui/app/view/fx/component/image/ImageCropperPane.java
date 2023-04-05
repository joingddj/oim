package com.onlyxiahui.app.view.fx.component.image;

import com.onlyxiahui.app.view.fx.component.base.TouchPane;
import com.onlyxiahui.app.view.fx.component.icon.IconButton;
import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconButton;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * 
 * 图片裁剪组件 <br>
 * Date 2020-03-12 10:04:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ImageCropperPane extends StackPane {

	BorderPane baseBorderPane = new BorderPane();

	StackPane centerStackPane = new StackPane();

	// StackPane cropperStackPane = new StackPane();
	ScrollPane cropperScrollPane = new ScrollPane();
	AnchorPane cropperAnchorPane = new AnchorPane();

	ImageView imageView = new ImageView();
	TouchPane imageStackPane = new TouchPane();

	StackPane coverStackPane = new StackPane();
	ImageView coverImageView = new ImageView();

	BorderPane bottomBorderPane = new BorderPane();

	Slider slider = new Slider();
	BorderPane sliderBorderPane = new BorderPane();

	boolean newImage = false;
	Image image;
	BorderPane rotateBorderPane = new BorderPane();
	IconButton plusButton = new IconButton();
	IconButton minusButton = new IconButton();

	FontAwesomeSolidIconButton leftRotateButton = new FontAwesomeSolidIconButton();
	FontAwesomeSolidIconButton rightRotateButton = new FontAwesomeSolidIconButton();

	public ImageCropperPane() {
		initComponent();
		initEvent();
	}

	private void initComponent() {

		imageView.setPreserveRatio(true);
		imageStackPane.getChildren().add(imageView);
		cropperAnchorPane.getChildren().add(imageStackPane);

		cropperScrollPane.setBackground(Background.EMPTY);
		cropperScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		cropperScrollPane.setContent(cropperAnchorPane);
		// cropperStackPane.getChildren().add(cropperScrollPane);

		coverStackPane.getChildren().add(coverImageView);
		coverStackPane.setMouseTransparent(true);

		centerStackPane.getChildren().add(cropperScrollPane);
		centerStackPane.getChildren().add(coverStackPane);

		leftRotateButton.setFontIcon("\uf0e2");
		rightRotateButton.setFontIcon("\uf01e");

		leftRotateButton.setIconFontSize(22.2D);
		rightRotateButton.setIconFontSize(22.2D);

		// leftRotateButton.setStyle("-fx-font-size: 22.5;");
		// rightRotateButton.setStyle("-fx-font-size: 2.33em;");

//		leftRotateButton.setNormalImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_left_normal.png"));
//		leftRotateButton.setHoverImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_left_hover.png"));
//		leftRotateButton.setPressedImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_left_down.png"));
//
//		rightRotateButton.setNormalImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_right_normal.png"));
//		rightRotateButton.setHoverImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_right_hover.png"));
//		rightRotateButton.setPressedImage(ImageBox.getImageClassPath("/classics/images/cropper/bighead_editor_rotate_right_down.png"));

		rotateBorderPane.setPadding(new Insets(0, 0, 0, 20));
		rotateBorderPane.setLeft(leftRotateButton);
		rotateBorderPane.setRight(rightRotateButton);

		slider.setMin(100);

		sliderBorderPane.setCenter(slider);

		bottomBorderPane.setCenter(sliderBorderPane);
		bottomBorderPane.setRight(rotateBorderPane);

		baseBorderPane.setCenter(centerStackPane);
		baseBorderPane.setBottom(bottomBorderPane);

		this.getChildren().add(baseBorderPane);
	}

	private void initEvent() {
		cropperAnchorPane.heightProperty().addListener(h -> {
			if (newImage) {
				newImage = false;

				double min = coverStackPane.getWidth();
				double max = cropperAnchorPane.getWidth();

				double temp = max - min;

				if (temp < 100) {
					max = min + (100 - temp);
				}

				slider.setMax(max);
				slider.setMin(min - 2);
				slider.setValue(slider.getMax());
			}
		});

		slider.valueProperty().addListener(l -> {
			imageView.setFitWidth(slider.getValue());
		});

		cropperScrollPane.setFocusTraversable(false);
		imageStackPane.setLimitParentBound(true);

		cropperScrollPane.widthProperty().addListener((o, ov, nv) -> {
			imageStackPane.setLimitMaxX(nv.doubleValue());
		});
		cropperScrollPane.heightProperty().addListener((o, ov, nv) -> {
			imageStackPane.setLimitMaxY(nv.doubleValue());
		});

//		leftRotateButton.setOnMouseClicked(a -> {
//			double rotate = imageView.getRotate();
//			rotate = rotate - 90;
//			if (rotate <= -360) {
//				rotate = 0;
//			}
//			imageView.setRotate(rotate);
//		});

		leftRotateButton.setOnAction(a -> {
			double rotate = imageView.getRotate();
			rotate = rotate - 90;
			if (rotate <= -360) {
				rotate = 0;
			}
			imageView.setRotate(rotate);
		});
		rightRotateButton.setOnAction(a -> {
			double rotate = imageView.getRotate();
			rotate = rotate + 90;
			if (rotate >= 360) {
				rotate = 0;
			}
			imageView.setRotate(rotate);
		});
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
	}

	public void setCoverImage(Image coverImage) {
		coverImageView.setImage(coverImage);
	}

	public void setCoverSize(double width, double height) {
		coverImageView.setFitWidth(width);
		coverImageView.setFitHeight(height);
		centerStackPane.setPrefSize(width, height);
		centerStackPane.setMaxSize(width, height);

		baseBorderPane.setMaxWidth(width);
	}

	public void setImage(Image image) {
		this.image = image;
		if (null != image) {
			newImage = true;
			imageView.setFitWidth(image.getWidth());
		}
		imageView.setImage(image);
	}

	public boolean hasImage() {
		return this.image != null;
	}

	public Image getImage() {
		Rectangle2D viewport = new Rectangle2D(0, 0, cropperScrollPane.getWidth(), cropperScrollPane.getHeight());
		SnapshotParameters p = new SnapshotParameters();
		p.setViewport(viewport);
		WritableImage image = cropperScrollPane.snapshot(p, null);
		return image;// imageView.getImage();
	}
}
