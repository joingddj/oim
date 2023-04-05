
package com.oimchat.client.general.run.initializer.launch.essential;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.general.kernel.work.watch.NetWatch;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.LaunchInitializer;

/**
 * Description <br>
 * Date 2021-03-09 18:24:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class EssentialSystemInitializer implements LaunchInitializer {

	@Override
	public void initialize(AppContext appContext) {
		appContext.getObject(NetWatch.class);
	}

	@Override
	public List<Class<?>> onAfter() {
		List<Class<?>> list = new ArrayList<>();
		return list;
	}
}
