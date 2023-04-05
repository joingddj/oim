package com.oimchat.app.fx.base.component.combo;

import com.onlyxiahui.app.view.fx.common.event.ValueEvent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * 
 * Description <br>
 * Date 2021-02-18 10:41:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @param <T>
 * @since 1.0.0
 */
public class ItemRemoveListCell<T> extends ListCell<T> {

	private final Button removeButton;
	private ValueEvent<T> removeValueAction;
	private ObjectProperty<StringConverter<T>> converter = new SimpleObjectProperty<StringConverter<T>>(this, "converter");

	public ItemRemoveListCell(ValueEvent<T> valueAction) {
		this(valueAction, new StringConverter<T>() {
			@Override
			public String toString(Object t) {
				return t == null ? null : t.toString();
			}

			@SuppressWarnings("unchecked")
			@Override
			public T fromString(String string) {
				return (T) string;
			}
		});
	}

	public ItemRemoveListCell(ValueEvent<T> valueAction, final StringConverter<T> converter) {
		this.setRemoveValueAction(valueAction);
		this.setConverter(converter);
		removeButton = new Button("X");
		removeButton.getStyleClass().clear();
		removeButton.setMinHeight(20);
		removeButton.setMinWidth(20);
		removeButton.setOnMouseReleased(e -> {
			if (null != getRemoveValueAction()) {
				getRemoveValueAction().value(ItemRemoveListCell.this.getItem());
			}
		});

		setAlignment(Pos.CENTER_LEFT);
		setContentDisplay(ContentDisplay.LEFT);
		setGraphic(removeButton);
	}

	public final void setConverter(StringConverter<T> value) {
		converterProperty().set(value);
	}

	public final StringConverter<T> getConverter() {
		return converterProperty().get();
	}

	public final ObjectProperty<StringConverter<T>> converterProperty() {
		return converter;
	}

	public ValueEvent<T> getRemoveValueAction() {
		return removeValueAction;
	}

	public void setRemoveValueAction(ValueEvent<T> removeValueAction) {
		this.removeValueAction = removeValueAction;
	}

	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			StringConverter<T> c = getConverter();
			setGraphic(removeButton);
			setText(c != null ? c.toString(item) : (item == null ? "" : item.toString()));
		} else {
			setGraphic(null);
			setText(null);
		}
	}

	public static <T> Callback<ListView<T>, ListCell<T>> forListView(
			ValueEvent<T> valueAction,
			StringConverter<T> converter) {
		return list -> new ItemRemoveListCell<T>(valueAction, converter);
	}

	public static <T> Callback<ListView<T>, ListCell<T>> forListView(
			ValueEvent<T> valueAction) {
		return list -> new ItemRemoveListCell<T>(valueAction);
	}
}
