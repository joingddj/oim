
package com.onlyxiahui.app.initializer;

import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-10 09:00:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class EnterInitializerBox extends BaseInitializerBox<EnterInitializer> {

	public EnterInitializerBox(AppContext appContext) {
		super(appContext);
	}

	@Override
	public String key() {
		return EnterInitializer.class.getName();
	}

	@Override
	public Class<EnterInitializer> initializerBaseClass() {
		Class<EnterInitializer> c = EnterInitializer.class;
		return c;
	}
}
