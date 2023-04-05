
package com.onlyxiahui.app.initializer;

import org.junit.Test;

import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-10 09:39:17<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseInitializerBoxTest {

	@Test
	public void load() {
		AppContext appContext = new AppContext();
		EnterInitializerBox box = appContext.getObject(EnterInitializerBox.class);
		box.initialize();
	}
}
