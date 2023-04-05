package com.oimchat.client.platform.fx.view.impl.classics;

import com.oimchat.app.fx.view.ui.classics.module.setting.NetSettingStage;
import com.oimchat.client.platform.common.view.NetSettingView;
import com.oimchat.client.platform.kernel.work.common.server.ServerAddressConfigManager;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * @author XiaHui
 * @date 2017-11-24 21:46:45
 */
public class NetSettingViewImpl extends BaseStageView<NetSettingStage> implements NetSettingView {

	public NetSettingViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			initConfig();
		}
	}

	private void initConfig() {
		ServerAddressConfigManager m = this.appContext.getObject(ServerAddressConfigManager.class);
		FxUtil.invoke(() -> {
			getStage().setAddress(m.getAddress());
		});
	}

	@Override
	protected NetSettingStage createStage() {
		return new NetSettingStage();
	}

	@Override
	protected void initStage(NetSettingStage stage) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		ServerAddressConfigManager m = this.appContext.getObject(ServerAddressConfigManager.class);
		getStage().setDoneAction(a -> {
			stage.hide();
			String address = stage.getAddress();
			m.save(address);
		});
	}
}
