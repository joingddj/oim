
package com.oimchat.client.general.run.initializer.launch.essential;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.LaunchInitializer;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.wofa.client.net.action.DispatcherPower;
import com.onlyxiahui.wofa.client.net.core.connect.WebSocketConnector;
import com.onlyxiahui.wofa.client.work.core.WorkContext;

/**
 * Description <br>
 * Date 2021-03-09 18:24:43<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class EssentialActionInitializer implements LaunchInitializer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void initialize(AppContext appContext) {
		DispatcherPower dc = appContext.getObject(DispatcherPower.class);
		dc.getActionDispatcher().scan("com.oimchat.client.general.kernel.work.module");

		WorkContext wc = appContext.getObject(WorkContext.class);
		wc.setConnector(new WebSocketConnector());
		wc.getNetHandler().addDataMonitorRead((d) -> {
			//String json = (d instanceof String) ? d.toString() : JsonUtil.toJson(d);
			//System.out.println();
			//System.out.println("read:" + json);
		});

		wc.getNetHandler().addDataMonitorWrite((d) -> {
			//String json = (d instanceof String) ? d.toString() : JsonUtil.toJson(d);
			//System.out.println();
			//System.out.println("write:" + json);
		});
	}

	@Override
	public List<Class<?>> onAfter() {
		Class<?> c = EssentialSenderInitializer.class;
		List<Class<?>> list = new ArrayList<>();
		list.add(c);
		return list;
	}
}
