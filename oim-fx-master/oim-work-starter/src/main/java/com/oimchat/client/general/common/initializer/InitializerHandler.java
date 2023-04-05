
package com.oimchat.client.general.common.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.client.general.common.task.RunExecutor;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.EnterInitializerBox;
import com.onlyxiahui.app.initializer.LaunchInitializerBox;

/**
 * Description <br>
 * Date 2021-03-11 09:42:31<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class InitializerHandler extends AbstractMaterial {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private volatile boolean initialized = false;

	public InitializerHandler(AppContext appContext) {
		super(appContext);
		RunExecutor re = appContext.getObject(RunExecutor.class);
		re.execute(() -> {
			initialize();
			initialized = true;
		});
		logger.debug("initializer");
	}

	protected void initialize() {
		enterInitializerLoad();
		launchInitializerLoad();
	}

	protected void enterInitializerLoad() {
		appContext.getObject(EnterInitializerBox.class);
	}

	protected void launchInitializerLoad() {
		LaunchInitializerBox box = appContext.getObject(LaunchInitializerBox.class);
		box.initialize();
	}

	public boolean isInitialized() {
		return initialized;
	}
}
