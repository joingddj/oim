package com.onlyxiahui.app.view.fx.component.screenshot;

import com.onlyxiahui.app.view.fx.common.event.ValueEvent;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenShotStage extends Stage {

	private StackPane baseStackPane = new StackPane();
	private ImageView backgroundImageView = new ImageView();

	AnchorPane anchorPane = new AnchorPane();
	AnchorPane pane = new AnchorPane();
	ContextMenu menu = new ContextMenu();

	AnchorPane topPane = new AnchorPane();
	AnchorPane leftPane = new AnchorPane();
	AnchorPane rightPane = new AnchorPane();
	AnchorPane bottomPane = new AnchorPane();
	Scene scene = new Scene(baseStackPane);
	private double initX = 0.0D;
	private double initY = 0.0D;
	boolean has = false;

	private double pX = 0.0D;
	private double pY = 0.0D;

	private ValueEvent<Image> action;
	private ScreenImageBuilder screenImageBuilder;

	public ScreenShotStage() {
		// super(StageStyle.UNDECORATED);
		super(StageStyle.TRANSPARENT);
		init();
		initMenu();
		initEvent();
	}

	private void init() {
		this.setScene(scene);
		this.setTitle("");
		this.setFullScreen(true);
		this.setFullScreenExitHint("");
		this.setAlwaysOnTop(true);
		scene.setFill(Color.TRANSPARENT);

		anchorPane.setBackground(Background.EMPTY);

		StackPane magnifierPane = new StackPane();
		/// magnifier=configure(magnifierPane);

		// magnifierPane.setBackground(Background.EMPTY);

		magnifierPane.getChildren().add(backgroundImageView);
		magnifierPane.getChildren().add(anchorPane);

		baseStackPane.getChildren().add(magnifierPane);

		pane.setStyle("-fx-border-color:#2096e6;-fx-border-width:1px;");
		anchorPane.getChildren().add(pane);

		anchorPane.getChildren().add(topPane);
		anchorPane.getChildren().add(leftPane);
		anchorPane.getChildren().add(rightPane);
		anchorPane.getChildren().add(bottomPane);

		topPane.setStyle("-fx-background-color:rgba(120, 120, 120, 0.3);");
		leftPane.setStyle("-fx-background-color:rgba(120, 120, 120, 0.3);");
		rightPane.setStyle("-fx-background-color:rgba(120, 120, 120, 0.3);");
		bottomPane.setStyle("-fx-background-color:rgba(120, 120, 120, 0.3);");

		pane.setLayoutX(initX);
		pane.setLayoutY(initY);

		scene.setOnMousePressed((MouseEvent me) -> {
			initX = me.getSceneX();
			initY = me.getSceneY();
		});

		scene.setOnMouseDragged((MouseEvent me) -> {

			double stageW = ScreenShotStage.this.getWidth();
			double stageH = ScreenShotStage.this.getHeight();

			double mx = me.getSceneX();
			double my = me.getSceneY();

			double w = mx - initX;
			double h = my - initY;

			pane.setLayoutX(w > 0 ? initX : mx);
			pane.setLayoutY(h > 0 ? initY : my);

			double width = 0;
			double height = 0;

			if (w > 0) {
				if (mx > stageW) {
					width = stageW - initX;
				} else {
					width = w;
				}
			} else {
				width = w * -1;
			}

			if (h > 0) {
				if (my > stageH) {
					height = stageH - initY;
				} else {
					height = h;
				}
			} else {
				height = h * -1;
			}

			pane.setPrefWidth(width);
			pane.setPrefHeight(height);

			layout();
		});

		scene.setOnMouseReleased(m -> {
			if (has()) {
				has = true;
				menu.show(ScreenShotStage.this, m.getSceneX(), m.getSceneY());
			}
		});

		scene.setOnKeyReleased(k -> {
			if (k.getCode() == KeyCode.ENTER) {
				save();
			}
			if (k.getCode() == KeyCode.ENTER || k.getCode() == KeyCode.ESCAPE) {
				ScreenShotStage.this.hide();
			}
		});

		pane.setOnMousePressed(m -> {
			pX = m.getScreenX() - pane.getLayoutX();
			pY = m.getScreenY() - pane.getLayoutY();
			m.consume();
		});

		pane.setOnMouseDragged((MouseEvent m) -> {

			double stageW = ScreenShotStage.this.getWidth();
			double stageH = ScreenShotStage.this.getHeight();

			double w = pane.getPrefWidth();
			double h = pane.getPrefHeight();

			double x = m.getScreenX() - pX;
			double y = m.getScreenY() - pY;

			if (x < 0) {
				x = 0;
			} else {
				if (x + w > stageW) {
					x = stageW - w;
				}
			}

			if (y < 0) {
				y = 0;
			} else {
				if (y + h > stageH) {
					y = stageH - h;
				}
			}
			pane.setLayoutX(x);
			pane.setLayoutY(y);

			layout();
			m.consume();
		});
	}

	private void initMenu() {

		Image image = ImageLoadUtil.getImageByClassPath("/com/onlyxiahui/app/view/fx/default/images/component/screenshot/tools/Exit.png");
		MenuItem menuItem = new MenuItem("取消", new ImageView(image));
		menu.getItems().add(menuItem);
		menuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ScreenShotStage.this.hide();
			}
		});

		image = ImageLoadUtil.getImageByClassPath("/com/onlyxiahui/app/view/fx/default/images/component/screenshot/tools/Finish.png");
		menuItem = new MenuItem("确定", new ImageView(image));
		menu.getItems().add(menuItem);
		menuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ScreenShotStage.this.hide();
				save();
			}
		});
	}

	private void initEvent() {
		// TODO Auto-generated method stub

	}

	private void layout() {
		double x = pane.getLayoutX();
		double y = pane.getLayoutY();

		double w = pane.getPrefWidth();
		double h = pane.getPrefHeight();

		topPane.setLayoutX(0);
		topPane.setLayoutY(0);
		topPane.setPrefWidth(this.getWidth());
		topPane.setPrefHeight(y);

		leftPane.setLayoutX(0);
		leftPane.setLayoutY(y);
		leftPane.setPrefWidth(x);
		leftPane.setPrefHeight(h);

		rightPane.setLayoutX(x + w);
		rightPane.setLayoutY(y);
		rightPane.setPrefWidth(this.getWidth() - x - w);
		rightPane.setPrefHeight(h);

		bottomPane.setLayoutX(0);
		bottomPane.setLayoutY(y + h);
		bottomPane.setPrefWidth(this.getWidth());
		bottomPane.setPrefHeight(this.getHeight() - y - h);
	}

	public boolean has() {
		double w = pane.getPrefWidth();
		double h = pane.getPrefHeight();

		if (w > 0 && h > 0) {
			has = true;
		} else {
			has = false;
		}
		return has;
	}

	public Image getImage() {
		Rectangle2D viewport = new Rectangle2D(pane.getLayoutX(), pane.getLayoutY(), pane.getWidth(), pane.getHeight());
		SnapshotParameters p = new SnapshotParameters();
		p.setViewport(viewport);
		WritableImage image = baseStackPane.snapshot(p, null);
		return image;// imageView.getImage();
	}

	public void setVisible(boolean visible) {
		if (visible) {
			pane.setStyle("-fx-border-color:#2096e6;-fx-border-width:1px;");

			if (null != screenImageBuilder) {
//				List<Screen> list = Screen.getScreens();
//				double x = 0;
//				double y = 0;
//				for (Screen s : list) {
//					Rectangle2D bounds = s.getVisualBounds();
//					if (bounds.getMinX() < x) {
//						x = bounds.getMinX();
//					}
//					if (bounds.getMinY() < y) {
//						y = bounds.getMinY();
//					}
//				}

				Image image = screenImageBuilder.build();
				backgroundImageView.setImage(image);

				Screen screen = Screen.getPrimary();

				if (null != screen) {
					// double dpi = screen.getDpi();
					double width = screen.getBounds().getWidth();
					double height = screen.getBounds().getHeight();
					backgroundImageView.setFitWidth(width);
					backgroundImageView.setFitHeight(height);
				}

				pane.setLayoutX(0);
				pane.setLayoutY(0);

				pane.setPrefWidth(0);
				pane.setPrefHeight(0);

//				this.setX(x);
//				this.setY(y);

				this.setFullScreen(true);
				this.setAlwaysOnTop(true);

				this.show();
				layout();
			}
		} else {
			this.hide();
		}
	}

	public void setScreenImageBuilder(ScreenImageBuilder screenImageBuilder) {
		this.screenImageBuilder = screenImageBuilder;
	}

	public void setOnImageAction(ValueEvent<Image> action) {
		this.action = action;
	}

	private void save() {
		if (has() && null != action) {
			pane.setStyle("-fx-border-color:null;-fx-border-width:0px;");
			Image image = getImage();
			action.value(image);
		}
	}
}
