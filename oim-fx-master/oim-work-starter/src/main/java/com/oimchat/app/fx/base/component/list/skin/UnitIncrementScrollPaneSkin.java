
package com.oimchat.app.fx.base.component.list.skin;

import com.sun.javafx.scene.control.skin.ScrollPaneSkin;

import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;

/**
 * Description <br>
 * Date 2021-03-05 19:38:22<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UnitIncrementScrollPaneSkin extends ScrollPaneSkin {

//	private double prevVerticalBlockIncrement = 0.2;
//	private double prevHorizontalBlockIncrement = 0.2;
//	private double prevVerticalUnitIncrement = 0.2;
//	private double prevHorizontalUnitIncrement = 0.2;

	public UnitIncrementScrollPaneSkin(final ScrollPane scrollPane) {
		super(scrollPane);
		filterScrollEvents();
	}

	@Override
	protected void layoutChildren(final double x, final double y, final double w, final double h) {
//		prevVerticalUnitIncrement = getVerticalScrollBar().getUnitIncrement();
//		prevHorizontalUnitIncrement = getHorizontalScrollBar().getUnitIncrement();
//		prevVerticalBlockIncrement = getVerticalScrollBar().getBlockIncrement();
//		prevHorizontalBlockIncrement = getHorizontalScrollBar().getBlockIncrement();

		super.layoutChildren(x, y, w, h);
//		getVerticalScrollBar().setUnitIncrement(prevVerticalUnitIncrement);
//		getHorizontalScrollBar().setUnitIncrement(prevHorizontalUnitIncrement);
//		getVerticalScrollBar().setBlockIncrement(prevVerticalBlockIncrement);
//		getHorizontalScrollBar().setBlockIncrement(prevHorizontalBlockIncrement);
		updateHorizontalSB();
		updateVerticalSB();
	}

	private void filterScrollEvents() {
		getSkinnable().addEventFilter(ScrollEvent.SCROLL, event -> {

			if (event.getDeltaX() < 0) {
				getHorizontalScrollBar().increment();
			} else if (event.getDeltaX() > 0) {
				getHorizontalScrollBar().decrement();
			}

			if (event.getDeltaY() < 0) {
				getVerticalScrollBar().increment();
			} else if (event.getDeltaY() > 0) {
				getVerticalScrollBar().decrement();
			}

			event.consume();
		});
	}

	public ScrollBar getVerticalScrollBar() {
		return vsb;
	}

	public ScrollBar getHorizontalScrollBar() {
		return hsb;
	}

	private void updateHorizontalSB() {
		hsb.setBlockIncrement(0.9 * hsb.getVisibleAmount());
		hsb.setUnitIncrement(0.1 * hsb.getVisibleAmount());
	}

	private void updateVerticalSB() {
		vsb.setBlockIncrement(0.9 * vsb.getVisibleAmount());
		vsb.setUnitIncrement(0.1 * vsb.getVisibleAmount());
	}
}
