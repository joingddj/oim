package com.oimchat.client.startup.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.client.general.common.initializer.InitializerHandler;
import com.oimchat.client.general.common.task.RunExecutor;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.initializer.StartInitializerBox;

/**
 * 
 * Description <br>
 * Date 2020-11-18 11:53:58<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class AppInitializer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final AppContext appContext = new AppContext();

	public AppInitializer() {
		start();
		initialize();
		logger.debug("startup");
	}

	protected void initialize() {
		appContext.getObject(InitializerHandler.class);
	}

	protected void start() {
		StartInitializerBox box = appContext.getObject(StartInitializerBox.class);
		box.initialize();
		RunExecutor re = appContext.getObject(RunExecutor.class);
		re.execute(() -> {
			InitializerHandler ih = appContext.getObject(InitializerHandler.class);
			logger.debug("InitializerHandler:" + ih.isInitialized());
		});

//		re.execute(() -> {
//			for (int i = 0; i < 100; i++) {
//				re.execute(() -> {
//					for (int j = 0; j < 100; j++) {
//						logger.debug("for:" + j);
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//		});
	}

	public AppContext getAppContext() {
		return appContext;
	}
}
