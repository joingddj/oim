
package com.onlyxiahui.app.basic.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.client.general.common.task.RunExecutor;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-22 09:18:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractMaterialView extends AbstractMaterial implements View {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public AbstractMaterialView(AppContext appContext) {
		super(appContext);
	}

	protected void execute(Runnable runnable) {
		RunExecutor re = appContext.getObject(RunExecutor.class);
		re.execute(runnable);
//		logger.debug("execute");
	}

	protected void executeAndWait(Runnable runnable) {
		RunExecutor re = appContext.getObject(RunExecutor.class);
		re.executeAndWait(runnable);
//		logger.debug("executeAndWait");
	}

	/**
	 * 显示提示信息
	 * 
	 * @author XiaHui
	 * @date 2017-11-24 14:22:35
	 * @param text
	 */
	public void showPrompt(String text) {

	}
}
