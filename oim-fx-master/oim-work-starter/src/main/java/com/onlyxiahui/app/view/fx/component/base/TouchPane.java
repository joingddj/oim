package com.onlyxiahui.app.view.fx.component.base;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.StackPane;

public class TouchPane extends StackPane {

	private double lastX, lastY, startScale, startRotate;

	private boolean limitParentBound = false;
	private DoubleProperty limitMaxX;
	private DoubleProperty limitMaxY;

	public TouchPane() {
		setOnMousePressed((MouseEvent event) -> {
			lastX = event.getX();
			lastY = event.getY();
			toFront();
			// postView.toFront();
		});

		setOnMouseDragged((MouseEvent event) -> {
			double layoutX = getLayoutX() + (event.getX() - lastX);
			double layoutY = getLayoutY() + (event.getY() - lastY);

			double maxWidth = getLimitMaxX() > -1 ? getLimitMaxX() : getParent().getLayoutBounds().getWidth();
			double maxHeight = getLimitMaxY() > -1 ? getLimitMaxY() : getParent().getLayoutBounds().getHeight();

			double width = getWidth();
			double height = getHeight();

			boolean mx = true;
			boolean my = true;

			if (isLimitParentBound()) {
				if (layoutX > 0) {
					mx = false;
				} else if (width < (maxWidth - layoutX)) {
					mx = false;
				}

				if (layoutY > 0) {
					my = false;
				} else if (height < (maxHeight - layoutY)) {
					my = false;
				}

				if (mx) {
					setLayoutX(layoutX);

				}
				if (my) {
					setLayoutY(layoutY);
				}
			} else {
				if ((layoutX >= 0) && (layoutX <= (getParent().getLayoutBounds().getWidth()))) {
					setLayoutX(layoutX);
				}

				if ((layoutY >= 0) && (layoutY <= (getParent().getLayoutBounds().getHeight()))) {
					setLayoutY(layoutY);
				}

				if ((getLayoutX() + (event.getX() - lastX) <= 0)) {
					setLayoutX(0);
				}
			}

			event.consume();
		});
		addEventHandler(ZoomEvent.ZOOM_STARTED, (ZoomEvent event) -> {
			startScale = getScaleX();
		});
		addEventHandler(ZoomEvent.ZOOM, (ZoomEvent event) -> {
			setScaleX(startScale * event.getTotalZoomFactor());
			setScaleY(startScale * event.getTotalZoomFactor());
		});
		addEventHandler(RotateEvent.ROTATION_STARTED, (RotateEvent event) -> {
			startRotate = getRotate();
		});
		addEventHandler(RotateEvent.ROTATE, (RotateEvent event) -> {
			setRotate(startRotate + event.getTotalAngle());
		});
	}

	public boolean isLimitParentBound() {
		return limitParentBound;
	}

	public void setLimitParentBound(boolean limitParentBound) {
		this.limitParentBound = limitParentBound;
	}

	public double getLimitMaxX() {
		return limitMaxXProperty().get();
	}

	public void setLimitMaxX(double limitMaxX) {
		this.limitMaxXProperty().set(limitMaxX);
	}

	public double getLimitMaxY() {
		return limitMaxYProperty().get();
	}

	public void setLimitMaxY(double limitMaxY) {
		this.limitMaxYProperty().set(limitMaxY);
	}

	public final DoubleProperty limitMaxXProperty() {
		if (limitMaxX == null) {
			limitMaxX = new SimpleDoubleProperty(this, "limitMaxX", -1.0D);
		}
		return limitMaxX;
	}

	public final DoubleProperty limitMaxYProperty() {
		if (limitMaxY == null) {
			limitMaxY = new SimpleDoubleProperty(this, "limitMaxY", -1.0D);
		}
		return limitMaxY;
	}
}
