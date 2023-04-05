package com.oimchat.app.fx.view.ui.classics.element.question;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * 
 * Description <br>
 * Date 2021-03-18 22:44:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class QuestionPane extends StackPane {

	private VBox box = new VBox();
	private ScrollPane scrollPane = new ScrollPane();

	public QuestionPane() {
		initComponent();
	}

	private void initComponent() {
		scrollPane.setBackground(Background.EMPTY);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setContent(box);
		this.getChildren().add(scrollPane);
	}

	public void setQuestionItemList(List<QuestionItem> nodeList) {
		box.getChildren().clear();
		if (null != nodeList) {
			for (Node node : nodeList) {
				box.getChildren().add(node);
			}
		}
	}
}
