package com.oimchat.app.fx.base.component.choose;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

/**
 * 
 * Description <br>
 * Date 2020-06-17 16:23:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public interface Choose {

	void setChooseGroup(ChooseGroup chooseGroup);

	ChooseGroup getChooseGroup();

	ObjectProperty<ChooseGroup> chooseGroupProperty();

	boolean isSelected();

	void setSelected(boolean selected);

	BooleanProperty selectedProperty();
	
	void setKey(String key);

	String getKey();
}
