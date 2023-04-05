
package com.oimchat.client.platform.fx.view.impl.classics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.app.fx.base.stage.BaseStage;
import com.oimchat.client.common.sync.SyncGet;
import com.oimchat.client.platform.fx.view.common.box.StageBox;
import com.onlyxiahui.app.basic.view.AbstractMaterialView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * Description <br>
 * Date 2020-12-26 15:10:04<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseStageView<T extends BaseStage> extends AbstractMaterialView {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected T stage;

	public BaseStageView(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			T stage = getStage();
			putStage(stage);
		});
	}

	protected void putStage(BaseStage stage) {
		StageBox sb = this.appContext.getObject(StageBox.class);
		sb.add(stage);
	}

	@Override
	public void showPrompt(String text) {
		FxUtil.invoke(() -> {
			getStage().showPopupPrompt(text);
		});
	}

	@Override
	public void setVisible(boolean visible) {
		T stage = getStage();
		FxUtil.invoke(() -> {
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

	@Override
	public boolean isShowing() {
		return getStage().isShowing();
	}

	public synchronized T getStage() {
		if (null == stage) {
			stage = SyncGet.<T>syncGet((p) -> {
				FxUtil.invoke(() -> {
					p.put(createStage());
				});
			});
		}
		return stage;
	}

	protected abstract T createStage();

	protected abstract void initStage(T stage);

	protected abstract void initComponent();

	protected abstract void initEvent();
}
