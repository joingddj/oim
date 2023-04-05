package com.oimchat.app.fx.view.ui.classics.element.question;

import com.onlyxiahui.app.fx.OnlyPopupOver;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author: XiaHui
 * @date: 2017年6月5日 下午4:20:59
 */
public class QuestionItem extends BorderPane {

	VBox rootBox = new VBox();
	TextArea questionTextArea = new TextArea();
	TextField answerField = new TextField();
	OnlyPopupOver questionOver = new OnlyPopupOver();
	OnlyPopupOver answerOver = new OnlyPopupOver();

	Label questionOverLabel = new Label("问题不能为空！");
	Label answerOverLabel = new Label("答案不能为空！");

	HBox box2 = new HBox();
	String questionId;

	boolean verifyQuestion = false;
	boolean verifyAnswer = true;

	public QuestionItem() {
		initComponent();
		iniEvent();
	}

	private void initComponent() {

		questionTextArea.setEditable(false);

		Label questionLabel = new Label("问题：");
		Label answerLabel = new Label("答案：");

		questionTextArea.setPrefSize(320, 50);
		answerField.setPrefSize(320, 25);

		HBox box1 = new HBox();
		box1.getChildren().add(questionLabel);
		box1.getChildren().add(questionTextArea);

		box2.getChildren().add(answerLabel);
		box2.getChildren().add(answerField);

		rootBox.getChildren().add(box1);
		rootBox.getChildren().add(box2);
		rootBox.setPadding(new Insets(5, 10, 5, 10));

		questionOver.setContentNode(questionOverLabel);
		answerOver.setContentNode(answerOverLabel);

		this.setCenter(rootBox);
	}

	private void iniEvent() {
		// TODO Auto-generated method stub

	}

	public boolean verify() {

		boolean mark = true;

		if (isVerifyAnswer()) {
			String answer = answerField.getText();
			if (null == answer || "".equals(answer)) {
				answerOver.show(answerField);
				answerField.requestFocus();
				mark = false;
			} else {
				mark = true;
			}
		}

		if (isVerifyQuestion()) {
			String answer = questionTextArea.getText();
			if (null == answer || "".equals(answer)) {
				questionOver.show(questionTextArea);
				questionTextArea.requestFocus();
				mark = false;
			} else {
				mark = true;
			}
		}
		return mark;
	}

	public void setQuestion(String question) {
		this.questionTextArea.setText(question);
	}

	public String getQuestion() {
		return this.questionTextArea.getText();
	}

	public String getAnswer() {
		return answerField.getText();
	}

	public void setAnswer(String answer) {
		answerField.setText(answer);
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public void setQuestionEditable(boolean editable) {
		questionTextArea.setEditable(editable);
	}

	public void setAnswerVisible(boolean visible) {
		box2.setVisible(visible);
	}

	public boolean isVerifyQuestion() {
		return verifyQuestion;
	}

	public void setVerifyQuestion(boolean verifyQuestion) {
		this.verifyQuestion = verifyQuestion;
	}

	public boolean isVerifyAnswer() {
		return verifyAnswer;
	}

	public void setVerifyAnswer(boolean verifyAnswer) {
		this.verifyAnswer = verifyAnswer;
	}
}
