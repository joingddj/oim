
package com.oimchat.client.platform.fx.module.image.wrap;

import com.oimchat.app.fx.view.ui.classics.module.image.ImageViewPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.oimchat.client.common.sync.SyncGet;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * Description <br>
 * Date 2021-03-25 12:17:39<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ImageViewWrap extends AbstractMaterial {

	private ClassicsStage stage;
	private ImageViewPane pane = new ImageViewPane();

	public ImageViewWrap(AppContext appContext) {
		super(appContext);
		initStage();
	}

	private void initStage() {
		FxUtil.invoke(() -> {
			ClassicsStage stage = getStage();
			stage.setWidth(1024);
			stage.setHeight(740);
			stage.setTitle("图片预览");
			stage.setCenter(pane);
		});
	}

	public synchronized ClassicsStage getStage() {
		if (null == stage) {
			stage = SyncGet.<ClassicsStage>syncGet((p) -> {
				FxUtil.invoke(() -> {
					p.put(new ClassicsStage());
				});
			});
		}
		return stage;
	}

	public void showPrompt(String text) {
		FxUtil.invoke(() -> {
			getStage().showPopupPrompt(text);
		});
	}

	public void setVisible(boolean visible) {
		FxUtil.invoke(() -> {
			ClassicsStage stage = getStage();
			if (visible) {
				if (stage.isIconified()) {
					stage.setIconified(false);
				} else {
					stage.show();
				}
				stage.toFront();
			} else {
				stage.hide();
			}
		});
	}

	public boolean isShowing() {
		return getStage().isShowing();
	}

	public void setImageHtml(String id, String html) {
		FxUtil.invoke(() -> {
			String exe = "webBridge.setImageHtml('" + id + "','" + html + "');";
			pane.getWebViewPane().executeScript(exe);
			// pane.getWebViewPane().scriptCall("window.webBridge", "setImageHtml", id,
			// html);
		});
	}
}
