package com.oimchat.client.general.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oimchat.client.common.event.ExecuteAction;
import com.oimchat.client.common.event.ExecuteObservable;
import com.oimchat.client.general.common.task.RunExecutor;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.wofa.client.work.core.WorkContext;

/**
 * @author XiaHui
 * @date 2017年9月20日 下午5:49:06
 */
public class SystemUnit extends AbstractMaterial {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	ExecuteObservable eabox = new ExecuteObservable();

	public SystemUnit(AppContext appContext) {
		super(appContext);
	}

	public void exit() {
		RunExecutor re = appContext.getObject(RunExecutor.class);
		re.execute(() -> {
			WorkContext netContext = appContext.getObject(WorkContext.class);
			netContext.closeConnect();
			eabox.execute();
		});
	}

	public void addExitExecuteAction(ExecuteAction ea) {
		eabox.add(ea);
	}
}
