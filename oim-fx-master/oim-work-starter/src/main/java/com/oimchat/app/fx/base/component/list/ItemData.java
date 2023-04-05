
package com.oimchat.app.fx.base.component.list;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * Description <br>
 * Date 2020-07-03 10:14:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ItemData {

	private StringProperty name = new SimpleStringProperty(this, "name", null);
	private ObjectProperty<Image> image = new SimpleObjectProperty<>(this, "image", null);
	private StringProperty text = new SimpleStringProperty(this, "text", null);
	private StringProperty time = new SimpleStringProperty(this, "time", null);

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public Image getImage() {
		return image.get();
	}

	public void setImage(Image image) {
		this.image.set(image);
	}

	public String getText() {
		return text.get();
	}

	public void setText(String text) {
		this.text.set(text);
	}

	public String getTime() {
		return time.get();
	}

	public void setTime(String time) {
		this.time.set(time);
	}
}
