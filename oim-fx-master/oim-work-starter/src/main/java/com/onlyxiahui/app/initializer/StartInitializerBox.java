
package com.onlyxiahui.app.initializer;

import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-10 09:00:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class StartInitializerBox extends BaseInitializerBox<StartInitializer> {

	public StartInitializerBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String key() {
		return StartInitializer.class.getName();
	}

	@Override
	public Class<StartInitializer> initializerBaseClass() {
		return StartInitializer.class;
	}
}
