
package com.oimchat.app.fx.base.component.loading;

import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Description <br>
 * Date 2020-07-06 15:21:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoadingPane extends StackPane {

	private StackPane rootPane = new StackPane();

	private VBox loadingPane = new VBox();
	private BorderPane loadingImagePane = new BorderPane();
	private ImageView loadingImageView = new ImageView();
	private Label loadingTextLabel = new Label();

	private VBox failPane = new VBox();
	private BorderPane failImagePane = new BorderPane();
	private ImageView failImageView = new ImageView();
	private Label failTextLabel = new Label();

	private VBox successPane = new VBox();
	private BorderPane successImagePane = new BorderPane();
	private ImageView successImageView = new ImageView();
	private Label successTextLabel = new Label();

	public LoadingPane() {
		initComponents();
		initData();
	}

	private void initComponents() {
		super.getChildren().add(rootPane);

		rootPane.getChildren().add(successPane);
		rootPane.getChildren().add(failPane);
		rootPane.getChildren().add(loadingPane);

		successPane.setAlignment(Pos.CENTER);
		successPane.getChildren().add(successImagePane);
		successPane.getChildren().add(successTextLabel);
		successImagePane.setCenter(successImageView);

		failPane.setAlignment(Pos.CENTER);
		failPane.getChildren().add(failImagePane);
		failPane.getChildren().add(failTextLabel);
		failImagePane.setCenter(failImageView);

		loadingPane.setAlignment(Pos.CENTER);
		loadingPane.getChildren().add(loadingImagePane);
		loadingPane.getChildren().add(loadingTextLabel);
		loadingImagePane.setCenter(loadingImageView);
	}

	private void initData() {
		this.setLoading("加载中...", ImageLoadUtil.getImageByClassPath("/general/view/common/images/loading/loading_bar_140x9.gif"));
		this.setSuccess("成功", ImageLoadUtil.getImageByClassPath("/general/view/common/images/info/33x33/success.png"));
		this.setFail("失败", ImageLoadUtil.getImageByClassPath("/general/view/common/images/info/33x33/error.png"));
		showNode();
	}

//	@Override
//	public ObservableList<Node> getChildren() {
//		return basePane.getChildren();
//	}

	public void setSuccess(String text) {
		successTextLabel.setText(text);
	}

	public void setFail(String text) {
		failTextLabel.setText(text);
	}

	public void setLoading(String text) {
		loadingTextLabel.setText(text);
	}

	public void setSuccess(String text, Image image) {
		successTextLabel.setText(text);
		successImageView.setImage(image);
	}

	public void setFail(String text, Image image) {
		failTextLabel.setText(text);
		failImageView.setImage(image);
	}

	public void setLoading(String text, Image image) {
		loadingTextLabel.setText(text);
		loadingImageView.setImage(image);
	}

	public void setSuccess(String text, Node node) {
		loadingTextLabel.setText(text);
		successImagePane.setCenter(node);
	}

	public void setFail(String text, Node node) {
		failTextLabel.setText(text);
		failImagePane.setCenter(node);
	}

	public void setLoading(String text, Node node) {
		loadingTextLabel.setText(text);
		loadingImagePane.setCenter(node);
	}

	public void showNode() {
		setNodeShow(true, this.getChildren(), rootPane);

		rootPane.setVisible(false);
		rootPane.toBack();
	}

	public void showLoding() {
		setNodeShow(false, this.getChildren(), rootPane);
		setNodeShow(false, rootPane.getChildren(), loadingPane);
	}

	public void showFail() {
		setNodeShow(false, this.getChildren(), rootPane);
		setNodeShow(false, rootPane.getChildren(), failPane);
	}

	public void showSuccess() {
		setNodeShow(false, this.getChildren(), rootPane);
		setNodeShow(false, rootPane.getChildren(), successPane);
	}

	private void setNodeShow(boolean show, ObservableList<Node> nodes, Node node) {
		for (Node n : nodes) {
			n.setVisible(show);
		}
		node.setVisible(!show);
	}
}
