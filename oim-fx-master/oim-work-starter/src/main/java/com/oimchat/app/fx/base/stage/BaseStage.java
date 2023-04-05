
package com.oimchat.app.fx.base.stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.onlyxiahui.app.fx.OnlyPopup;
import com.onlyxiahui.app.fx.OnlyStage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 * Description <br>
 * Date 2020-07-02 11:40:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseStage extends OnlyStage {
	Alert information = new Alert(AlertType.INFORMATION);
	Color color = Color.rgb(255, 255, 255, 0.0D);
	private OnlyPopup popupOver = new OnlyPopup();
	private TextArea popupTextArea = new TextArea();

	public BaseStage() {
		init();
	}

	private void init() {
		this.setRadius(10);
		this.getOnlyDecoratedPane().addOnCloseAction((a) -> {
			BaseStage.this.hide();
		});
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN);
		// dropShadow.setRadius(20);
		dropShadow.setColor(new Color(0.00, 0.00, 0.00, 0.7));
		this.getRootPane().setEffect(dropShadow);
		initPrompt();
	}

	private void initPrompt() {
		information.initModality(Modality.APPLICATION_MODAL);
		information.initOwner(this);
		information.getDialogPane().setHeaderText(null);

		HBox box = new HBox();
//		popupTextArea.setPrefSize(420, 200);

		box.getChildren().add(popupTextArea);

		popupTextArea.setBorder(Border.EMPTY);
		popupTextArea.setEditable(false);
		popupTextArea.setBackground(Background.EMPTY);
		popupTextArea.focusedProperty().addListener((a, ov, nv) -> {
			popupTextArea.setBackground(Background.EMPTY);
		});
		popupOver.setContentNode(box);
	}

	public void showWaitPrompt(String text) {
		information.getDialogPane().setContentText(text);
		information.showAndWait();
	}

	public void showPopupPrompt(String text) {
		popupTextArea.setText(text);
		popupOver.show(this);
	}

	public void setBackgroundOpacity(double opacity) {
		color = Color.color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
		super.setBackgroundColor(color);
	}

	public void setBackgroundImageFilePath(String filePath) {
		try {
			filePath = filePath.replace("\\", "/");
			File f = new File(filePath);
			if (f.exists()) {
				URL url = f.toURI().toURL();
				setBackgroundImage(url);
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	public void setBackgroundImageClassPath(String classPath) {
		URL url = this.getClass().getResource(classPath);
		setBackgroundImage(url);
	}

	public void setBackgroundImage(URL url) {
		String pathString = url.toString();
		backgroundImagePane.setStyle("-fx-background-image:url(\"" + pathString + "\");");
	}
}
