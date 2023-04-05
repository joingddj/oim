
package com.oimchat.client.general.kernel.work.module.index.service;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.EnterInitializerBox;

/**
 * Description <br>
 * Date 2021-03-13 20:22:19<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class EnterService extends AbstractMaterial {

	public EnterService(AppContext appContext) {
		super(appContext);
	}

	public void enter() {
		EnterInitializerBox box = appContext.getObject(EnterInitializerBox.class);
		box.initialize();
	}
}
