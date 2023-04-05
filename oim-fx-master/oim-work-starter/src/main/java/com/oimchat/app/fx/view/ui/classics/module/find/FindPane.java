
package com.oimchat.app.fx.view.ui.classics.module.find;

import com.onlyxiahui.app.view.fx.component.icon.font.FontAwesomeSolidIconText;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconText;
import com.onlyxiahui.app.view.fx.component.tab.TabPane;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Description <br>
 * Date 2021-02-26 15:02:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FindPane extends StackPane {

	TabPane tabPane = new TabPane();
	FindUserPane findUserPanel = new FindUserPane();
	FindGroupPane findGroupPanel = new FindGroupPane();

	public FindPane() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {
		BorderPane rootBox = new BorderPane();

		HBox hBox = new HBox();
		hBox.setMinHeight(50);
		// rootBox.getChildren().add(hBox);
		// rootBox.getChildren().add(tabPanel);
		rootBox.setTop(hBox);
		rootBox.setCenter(tabPane);

		FontIconText user = new FontAwesomeSolidIconText();
		user.setFontIcon("\uf007");
		user.setIconFontSize(38.2D);
		tabPane.add("找人", user, findUserPanel);

		FontIconText group = new FontAwesomeSolidIconText();
		group.setFontIcon("\uf0c0");
		group.setIconFontSize(38.2D);
		tabPane.add("找群", group, findGroupPanel);

//		Image normalImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_contacts_normal@2x.png");
//		Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_contacts_hover@2x.png");
//		Image selectedImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_contacts_selected@2x.png");
//
//		tabPanel.add("找人", Font.font("微软雅黑", 16), Color.WHITE, normalImage, hoverImage, selectedImage, findUserPanel);
//
//		normalImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_group_normal@2x.png");
//		hoverImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_group_hover@2x.png");
//		selectedImage = ImageLoadUtil.getImageByClassPath("/general/view/theme/classics/images/find/icon_group_selected@2x.png");
//
//		tabPanel.add("找群", Font.font("微软雅黑", 16), Color.WHITE, normalImage, hoverImage, selectedImage, findGroupPanel);

		this.getChildren().add(rootBox);
	}

	private void iniEvent() {
		// TODO Auto-generated method stub

	}

	public FindUserPane getFindUserPane() {
		return findUserPanel;
	}

	public FindGroupPane getFindGroupPane() {
		return findGroupPanel;
	}

	public void selectedTab(int index) {
		tabPane.selected(index);
	}
}
