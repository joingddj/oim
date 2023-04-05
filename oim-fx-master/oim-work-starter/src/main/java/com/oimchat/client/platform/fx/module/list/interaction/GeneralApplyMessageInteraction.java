
package com.oimchat.client.platform.fx.module.list.interaction;

import com.oimchat.client.platform.common.view.GeneralApplyNoticeView;
import com.oimchat.client.platform.fx.work.inform.service.GeneralApplyInformationService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 09:18:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyMessageInteraction extends AbstractMaterial {

	public GeneralApplyMessageInteraction(AppContext appContext) {
		super(appContext);
	}

	public void show(int type) {
		GeneralApplyNoticeView view = appContext.getObject(GeneralApplyNoticeView.class);
		view.selectedTab(type);
		view.setVisible(true);

		GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);
		service.removePrompt(type);
	}
}
