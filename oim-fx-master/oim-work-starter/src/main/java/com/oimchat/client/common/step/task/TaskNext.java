
package com.oimchat.client.common.step.task;

/**
 * Description <br>
 * Date 2021-03-31 00:51:59<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TaskNext {

	private TaskRun run;
	private TaskNext next;

	public TaskNext() {

	}

	public TaskNext set(TaskRun run) {
		this.run = run;
		return next = new TaskNext();
	}

	public void next() {
		if (null != run) {
			run.run(next);
		}
	}
}
