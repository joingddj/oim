
package com.oimchat.client.platform.common.run.initializer.launch.essential;

import java.util.ArrayList;
import java.util.List;

//import com.oimchat.client.platform.common.unit.DatabaseUnit;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.LaunchInitializer;

/**
 * Description <br>
 * Date 2021-03-09 18:24:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class DatabaseInitializer implements LaunchInitializer {

	@Override
	public void initialize(AppContext appContext) {
		// DatabaseUnit du = appContext.getObject(DatabaseUnit.class);
		// du.initialize();
	}

	@Override
	public List<Class<?>> onAfter() {
		List<Class<?>> list = new ArrayList<>();
		return list;
	}
}
